package com.example.study.global.security;

import com.example.study.api.entity.Member;
import com.example.study.api.enums.Gender;
import com.example.study.api.enums.Role;
import com.example.study.api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("CustomOAuth2UserService invoked!"); // 로그 추가
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 사용자 정보 추출
        Map<String, Object> attributes = oAuth2User.getAttributes();
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");

        // 사용자 프로필 정보 가져오기
        String nickname = (String) properties.get("nickname");
        String email = (String) kakaoAccount.getOrDefault("email", nickname + "_" + UUID.randomUUID() + "@kakao.com");

        // 사용자 저장 또는 업데이트
        Member member = saveOrUpdateUser(email, nickname);

        // OAuth2User 반환
        Map<String, Object> modifiedAttributes = new HashMap<>(attributes);
        modifiedAttributes.put("email", email);
        return new DefaultOAuth2User(oAuth2User.getAuthorities(), modifiedAttributes, "email");
    }

    private Member saveOrUpdateUser(String email, String nickname) {
        Optional<Member> existingMember = memberRepository.findByEmail(email);

        if (existingMember.isPresent()) {
            Member member = existingMember.get();
            member.setNickname(nickname);
            Member updatedMember = memberRepository.save(member);
            System.out.println("Updated Member: " + updatedMember);
            return updatedMember;
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Member newMember = Member.builder()
                .email(email)
                .nickname(nickname)
                .password(passwordEncoder.encode("OAUTH_USER_" + UUID.randomUUID()))
                .gender(Gender.NONE)
                .birthDate(LocalDate.of(2000, 1, 1)) // 기본값 설정
                .address("소셜로그인")
                .role(Role.USER)
                .build();

        Member savedMember = memberRepository.save(newMember);
        System.out.println("Saved Member: " + savedMember);
        return savedMember;
    }
}

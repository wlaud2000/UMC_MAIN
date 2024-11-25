package com.example.study.api.entity;

import com.example.study.api.enums.Gender;
import com.example.study.api.enums.Provider;
import com.example.study.api.enums.Role;
import com.example.study.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@DynamicUpdate
@DynamicInsert
@Builder
@Getter
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "provider")
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "point", nullable = false)
    private int point;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    // 선호 카테고리
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberPrefer> preferCategories = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    public void encodePassword(String password) {
        this.password = password;
    }

}

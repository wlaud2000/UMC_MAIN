package com.example.study.entity;

import com.example.study.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "provider", nullable = false)
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

    // Enum 정의
    public enum Provider {
        KAKAO, NAVER, APPLE, GOOGLE
    }

    public enum Gender {
        MALE, FEMALE, NULL
    }
}

package com.example.study.entity;

import com.example.study.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

//    @Column(name = "d_day", nullable = false)
//    private int dDay;
//
//    @Column(name = "title", nullable = false)
//    private String title;
//
//    @Column(name = "content", nullable = false)
//    private String content;
//
//    @Column(name = "reward_point", nullable = false)
//    private int rewardPoint;
}

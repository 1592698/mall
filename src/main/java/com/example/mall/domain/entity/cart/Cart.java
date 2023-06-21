package com.example.mall.domain.entity.cart;

import com.example.mall.domain.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Table(name="cart")
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="cart_id")
    private Long id;

    @OneToOne
    @JoinColumn(name="member_id")//매핑할 외래키를 지정

    private Member member;

    @Builder
    public Cart(Member member){
        this.member = member;
    }
}

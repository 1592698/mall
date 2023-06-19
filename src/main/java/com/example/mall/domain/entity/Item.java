package com.example.mall.domain.entity;


import com.example.mall.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="item")
@Getter
@Setter
@ToString
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_id")
    private Long id; //상품 코드

    @Column(nullable = false, length = 50)
    private String itemNm; //상품 이름

    @Column(name="price", nullable = false)
    private int price; //상품 가격

    @Column(nullable = false)
    private int stock; //재고

    @Lob
    @Column(nullable = false)
    private String itemDetail; //설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; //상태


    private LocalDateTime regTime; //상품 등록 시간
}

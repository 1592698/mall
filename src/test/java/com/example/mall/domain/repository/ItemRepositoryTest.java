package com.example.mall.domain.repository;


import com.example.mall.ItemSellStatus;
import com.example.mall.domain.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
//@TestPropertySource(locations="classpath:application-test.properties")
public class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest() {
        Item item=new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStock(100);
        item.setRegTime(LocalDateTime.now());
        Item savedItem = itemRepository.save(item);
        System.out.println(savedItem.toString());

    }

    public void createItemList(){
        for(int i=1; i<=10; i++){
            Item item = new Item();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000+i);
            item.setItemDetail("설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStock(100);
            item.setRegTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
            System.out.println(savedItem.toString());
        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNmTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemNm("테스트 상품1");
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("@Query를 이용한 상품 조회 테스트")
    public void findByItemDetailTest(){
        this.createItemList();
        List<Item> itemList = this.itemRepository.findByItemDetail("설명");
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }
}

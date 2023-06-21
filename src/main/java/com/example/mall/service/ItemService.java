package com.example.mall.service;

import com.example.mall.domain.entity.item.Item;
import com.example.mall.domain.entity.item.ItemImg;
import com.example.mall.dto.ItemImgDto;
import com.example.mall.repository.ItemImgRepository;
import com.example.mall.repository.ItemRepository;
import com.example.mall.dto.ItemFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;



@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    private final ItemImgService itemImgService;

    private final ItemImgRepository itemImgRepository;

    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception{

        //상품 등록
        Item item = itemFormDto.createItem();
        itemRepository.save(item);

        //이미지 등록
        for(int i=0;i<itemImgFileList.size();i++){
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);

            if(i == 0)
                itemImg.setRepimgYn("Y");
            else
                itemImg.setRepimgYn("N");

            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }

        return item.getId();
    }

    //등록된 상품을 불러오는 메소드
    @Transactional(readOnly = true)
    //상품 데이터를 읽어오는 트랜잭션을 읽기 전용으로 설정.

    public ItemFormDto getItemDtl(Long itemId){
        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        List<ItemImgDto> itemImgDtoList = new ArrayList<>();
        for (ItemImg itemImg : itemImgList) {
            ItemImgDto itemImgDto = ItemImgDto.of(itemImg);
            itemImgDtoList.add(itemImgDto);
        }

        Item item = itemRepository.findById(itemId)
                .orElseThrow(EntityNotFoundException::new);
        ItemFormDto itemFormDto = ItemFormDto.of(item);
        itemFormDto.setItemImgDtoList(itemImgDtoList);
        return itemFormDto;
    }

//    public Long updateItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception{
//        //상품 수정
//        Item item = itemRepository.findById(itemFormDto.getId())
//                .orElseThrow(EntityNotFoundException::new);
//        item.updateItem(itemFormDto);
//        List<Long> itemImgIds = itemFormDto.getItemImgIds();
//
//        //이미지 등록
//        for(int i=0;i<itemImgFileList.size();i++){
//            itemImgService.updateItemImg(itemImgIds.get(i),
//                    itemImgFileList.get(i));
//        }
//
//        return item.getId();
//    }



}
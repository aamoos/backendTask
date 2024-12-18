package com.backendTask.task1.controller;

import com.backendTask.task1.dto.ItemsDto;
import com.backendTask.task1.dto.RestResponseDto;
import com.backendTask.task1.service.ItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemsController {

    private final ItemsService itemsService;

    /**
     * 아이템 조회
     * @return
     */
    @GetMapping("/items")
    public ResponseEntity<RestResponseDto<List<ItemsDto>>> getItems(){
        RestResponseDto<List<ItemsDto>> getItems = itemsService.getItems();
        System.out.println("Returned Data: " + getItems.getData());  // 디버깅
        return new ResponseEntity<>(getItems, getItems.getStatus());
    }

    /**
     * 아이템 등록
     */
    @PostMapping("/items")
    public ResponseEntity<RestResponseDto<ItemsDto>> createItem(@RequestBody ItemsDto itemsDto){
        RestResponseDto<ItemsDto> addItem = itemsService.createItem(itemsDto);
        return new ResponseEntity<>(addItem, addItem.getStatus());
    }

    /**
     * 특정 아이템 수정
     */
    @PutMapping("/items/{id}")
    public ResponseEntity<RestResponseDto<ItemsDto>> updateItem(@PathVariable("id") Long id, @RequestBody ItemsDto itemsDto){
        RestResponseDto<ItemsDto> updateItem = itemsService.updateItem(id, itemsDto);
        return new ResponseEntity<>(updateItem, updateItem.getStatus());
    }

    /**
     * 특정 아이템 삭제
     */
    @DeleteMapping("/items/{id}")
    public ResponseEntity<RestResponseDto<ItemsDto>> updateItem(@PathVariable("id") Long id){
        RestResponseDto<ItemsDto> deleteItem = itemsService.deleteItem(id);
        return new ResponseEntity<>(deleteItem, deleteItem.getStatus());
    }

}

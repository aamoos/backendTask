package com.backendTask.task1.service;

import com.backendTask.task1.dto.ItemsDto;
import com.backendTask.task1.dto.RestResponseDto;
import com.backendTask.task1.entity.Items;
import com.backendTask.task1.exception.CustomException;
import com.backendTask.task1.repository.ItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import static com.backendTask.task1.exception.RestResponseCode.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemsService {

    private final ItemsRepository itemsRepository;

    /**
     * 아이템 조회
     */
    @Transactional(readOnly = true)
    public RestResponseDto<List<ItemsDto>> getItems(){

        List<Items> findItems = itemsRepository.findAll();

        // List<Items>를 List<ItemsDto>로 변환
        List<ItemsDto> itemsDtoList = findItems.stream()
                .map(Items::toDto) // Items 객체를 ItemsDto로 변환
                .toList(); // List<ItemsDto>로 변환

        // 데이터가 정상적으로 있으면 성공 응답
        return RestResponseDto.<List<ItemsDto>>builder()
                .code(ITEM_LIST_FETCH_SUCCESS.getHttpStatus().value())
                .status(ITEM_LIST_FETCH_SUCCESS.getHttpStatus())
                .message(ITEM_LIST_FETCH_SUCCESS.getMessage())
                .data(itemsDtoList)
                .build();
    }

    /**
     * 아이템 등록
     */
    public RestResponseDto<ItemsDto> createItem(ItemsDto itemsDto){

       try{
           itemsRepository.save(ItemsDto.toEntity(itemsDto));

           return RestResponseDto.<ItemsDto>builder()
                   .code(ITEM_CREATE_SUCCESS.getHttpStatus().value())
                   .status(ITEM_CREATE_SUCCESS.getHttpStatus())
                   .message(ITEM_CREATE_SUCCESS.getMessage())
                   .build();
       }catch (Exception e){
           throw new CustomException(ITEM_CREATE_FAILURE);
       }

    }

    /**
     * 아이템 수정
     */
    public RestResponseDto<ItemsDto> updateItem(Long id, ItemsDto itemsDto){
        Optional<Items> findItems = itemsRepository.findById(id);
        Items items = findItems.orElseThrow(() -> new CustomException(ITEM_UPDATE_FAILURE));

        //변경감지 데이터 update
        items.changeItem(itemsDto.getName(), itemsDto.getDescription());

        return RestResponseDto.<ItemsDto>builder()
                .code(ITEM_UPDATE_SUCCESS.getHttpStatus().value())
                .status(ITEM_UPDATE_SUCCESS.getHttpStatus())
                .message(ITEM_UPDATE_SUCCESS.getMessage())
                .build();
    }

    /**
     * 아이템 삭제
     */
    public RestResponseDto<ItemsDto> deleteItem(Long id){

        //삭제할 아이템 존재하는지 확인
        Optional<Items> findItems = itemsRepository.findById(id);
        findItems.orElseThrow(() -> new CustomException(ITEM_DELETE_FAILURE));

        itemsRepository.deleteById(id);

        return RestResponseDto.<ItemsDto>builder()
                .code(ITEM_DELETE_SUCCESS.getHttpStatus().value())
                .status(ITEM_DELETE_SUCCESS.getHttpStatus())
                .message(ITEM_DELETE_SUCCESS.getMessage())
                .build();
    }

}

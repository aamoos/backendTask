package com.backendTask.task3.controller;

import com.backendTask.common.dto.RestResponseDto;
import com.backendTask.task3.dto.OrdersDto;
import com.backendTask.task3.service.OrdersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    /**
     *  로그인한 사용자 주문 목록 보기
     */
    @GetMapping("/orders/read")
    public ResponseEntity<RestResponseDto<List<OrdersDto.Response>>> readOrders(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        RestResponseDto<List<OrdersDto.Response>> readOrders = ordersService.readOrders(authorizationHeader);
        return new ResponseEntity<>(readOrders, readOrders.getStatus());
    }

    /**
     * 주문하기
     */

    @PostMapping("/orders/create")
    public ResponseEntity<RestResponseDto<OrdersDto.Response>> createOrder(
              @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader
            , @Valid @RequestBody OrdersDto.Request request
            , BindingResult bindingResult){
        RestResponseDto<OrdersDto.Response> createOrders = ordersService.createOrders(authorizationHeader, request, bindingResult);
        return new ResponseEntity<>(createOrders, createOrders.getStatus());
    }

    /**
     * 주문 수정하기
     */

    @PutMapping("/orders/{id}")
    public ResponseEntity<RestResponseDto<OrdersDto.Response>> updateItem(
              @PathVariable("id") Long id
            , @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader
            , @Valid @RequestBody OrdersDto.Request request
            , BindingResult bindingResult){
        RestResponseDto<OrdersDto.Response> updateOrders = ordersService.updateOrders(id, request, bindingResult);
        return new ResponseEntity<>(updateOrders, updateOrders.getStatus());
    }

    /**
     * 주문삭제하기
     */

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<RestResponseDto<OrdersDto.Response>> deleteOrders(@PathVariable("id") Long id){
        RestResponseDto<OrdersDto.Response> deleteOrders = ordersService.deleteOrders(id);
        return new ResponseEntity<>(deleteOrders, deleteOrders.getStatus());
    }

}

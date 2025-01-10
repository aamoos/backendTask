package com.backendTask.task3.service;

import com.backendTask.common.exception.CustomException;
import com.backendTask.common.jwt.JwtUtil;
import com.backendTask.common.dto.RestResponseDto;
import com.backendTask.task2.entity.Member;
import com.backendTask.task2.repository.MemberRepository;
import com.backendTask.task3.dto.OrdersDto;
import com.backendTask.task3.entity.Orders;
import com.backendTask.task3.repository.OrdersRepository;
import com.backendTask.common.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

import static com.backendTask.common.exception.RestResponseCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class OrdersService {

    private final MemberRepository memberRepository;
    private final OrdersRepository ordersRepository;
    private final JwtUtil jwtUtil;

    /**
     * 로그인한 사용자 주문 목록 보기
     */
    @Transactional(readOnly = true)
    public RestResponseDto<List<OrdersDto.Response>> readOrders(String authorizationHeader){

        Member member = memberRepository.findById(jwtUtil.getUserId(jwtUtil.extractTokenFromHeader(authorizationHeader))).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        List<Orders> orders = ordersRepository.findByMember_MemberId(member.getMemberId());

        List<OrdersDto.Response> response = orders.stream()
                .map(Orders::toDto) // Items 객체를 ItemsDto로 변환
                .toList();

        // 데이터가 정상적으로 있으면 성공 응답
        return RestResponseDto.<List<OrdersDto.Response>>builder()
                .code(ORDERS_READ_SUCCESS.getHttpStatus().value())
                .status(ORDERS_READ_SUCCESS.getHttpStatus())
                .message(ORDERS_READ_SUCCESS.getMessage())
                .data(response)
                .build();
    }

    /**
     * 주문하기
     */
    public RestResponseDto<OrdersDto.Response> createOrders(String authorizationHeader, OrdersDto.Request request, BindingResult bindingResult){

        // 유효성 검사 오류가 있는 경우 처리
        if (bindingResult.hasErrors()) {
            return ValidationUtil.validationCheck(bindingResult);
        }

        //요청이 정상적인 경우
        try{
            //로그인한 사용자 정보 set
            request.setMember(memberRepository.findById(jwtUtil.getUserId(jwtUtil.extractTokenFromHeader(authorizationHeader))).orElseThrow(() -> new CustomException(USER_NOT_FOUND)));

            Orders orders = OrdersDto.Request.toEntity(request);

            //주문하기 저장
            ordersRepository.save(orders);

            return RestResponseDto.<OrdersDto.Response>builder()
                    .code(ORDERS_CREATE_SUCCESS.getHttpStatus().value())
                    .status(ORDERS_CREATE_SUCCESS.getHttpStatus())
                    .message(ORDERS_CREATE_SUCCESS.getMessage())
                    .build();
        }catch (Exception e){
            throw new CustomException(ORDERS_CREATE_FAILURE);
        }
    }

    /**
     * 주문 수정하기
     */
    public RestResponseDto<OrdersDto.Response> updateOrders(Long id, OrdersDto.Request request, BindingResult bindingResult){

        // 유효성 검사 오류가 있는 경우 처리
        if (bindingResult.hasErrors()) {
            return ValidationUtil.validationCheck(bindingResult);
        }

        try{
            Optional<Orders> findOrders = ordersRepository.findById(id);
            Orders orders = findOrders.orElseThrow(() -> new CustomException(ORDERS_NOT_FOUND));

            //변경감지 데이터 update
            orders.changeOrders(request.getProductName());

            return RestResponseDto.<OrdersDto.Response>builder()
                    .code(ORDERS_UPDATE_SUCCESS.getHttpStatus().value())
                    .status(ORDERS_UPDATE_SUCCESS.getHttpStatus())
                    .message(ORDERS_UPDATE_SUCCESS.getMessage())
                    .build();
        }catch (Exception e){
            throw new CustomException(ORDERS_UPDATE_FAILURE);
        }
    }

    /**
     * 주문 삭제하기
     */
    public RestResponseDto<OrdersDto.Response> deleteOrders(Long id){

        try{
            //삭제할 아이템 존재하는지 확인
            Optional<Orders> findItems = ordersRepository.findById(id);
            findItems.orElseThrow(() -> new CustomException(ORDERS_NOT_FOUND));

            ordersRepository.deleteById(id);

            return RestResponseDto.<OrdersDto.Response>builder()
                    .code(ORDERS_DELETE_SUCCESS.getHttpStatus().value())
                    .status(ORDERS_DELETE_SUCCESS.getHttpStatus())
                    .message(ORDERS_DELETE_SUCCESS.getMessage())
                    .build();
        }catch (Exception e){
            throw new CustomException(ORDERS_DELETE_FAILURE);
        }
    }
}

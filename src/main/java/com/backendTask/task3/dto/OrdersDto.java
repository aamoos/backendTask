package com.backendTask.task3.dto;

import com.backendTask.task2.entity.Member;
import com.backendTask.task3.entity.DeliveryStatus;
import com.backendTask.task3.entity.Orders;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;


public class OrdersDto {

    @Data
    public static class Request {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ORDER_ID")
        private Long orderId;

        @ManyToOne
        @JoinColumn(name = "MEMBER_ID")
        private Member member;

        @NotBlank(message = "상품명은 필수 항목입니다.")
        private String productName;

        private LocalDateTime orderDate;
        private DeliveryStatus deliveryStatus;

        public static Orders toEntity(OrdersDto.Request request){
            return Orders.builder()
                    .member(request.getMember())
                    .productName(request.getProductName())
                    .orderDate(LocalDateTime.now())
                    .deliveryStatus(DeliveryStatus.PENDING)
                    .build();
        }
    }

    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Data
    public static class Response {
        private Long orderId;
        private String productName;
        private LocalDateTime orderDate;
        private DeliveryStatus deliveryStatus;
        private String email;
        private String name;
    }

}

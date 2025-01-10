package com.backendTask.task3.entity;

import com.backendTask.task2.entity.Member;
import com.backendTask.task3.dto.OrdersDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private String productName;

    private LocalDateTime orderDate;
    private LocalDateTime updateDate;

    @Enumerated(EnumType.STRING)  // enum 값을 문자열로 저장
    private DeliveryStatus deliveryStatus;

    public void changeOrders(String productName) {
        this.productName = productName;
        this.updateDate = LocalDateTime.now();
    }

    public static OrdersDto.Response toDto(Orders orders){
        return OrdersDto.Response.builder()
                .productName(orders.getProductName())
                .orderDate(orders.getOrderDate())
                .deliveryStatus(orders.getDeliveryStatus())
                .email(orders.getMember().getEmail())
                .name(orders.getMember().getName())
                .build();
    }

}

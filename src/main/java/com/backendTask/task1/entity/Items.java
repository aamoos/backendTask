package com.backendTask.task1.entity;

import com.backendTask.task1.dto.ItemsDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // AUTO_INCREMENT 설정
    private Long id;

    private String name;
    private String description;

    public static ItemsDto toDto(Items items){
        return ItemsDto.builder()
                .name(items.getName())
                .description(items.getDescription())
                .build();
    }

    public void changeItem(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Items{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

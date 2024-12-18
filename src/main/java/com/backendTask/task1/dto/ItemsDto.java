package com.backendTask.task1.dto;

import com.backendTask.task1.entity.Items;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemsDto {
    private String name;
    private String description;

    public static Items toEntity(ItemsDto itemsDto){
        return Items.builder()
                .name(itemsDto.getName())
                .description(itemsDto.getDescription())
                .build();
    }

}

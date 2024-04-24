package com.example.itemservice.item.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ItemDto {

    private Long id;
    private String itemName;
    private Integer quantity;
    private Integer price = 0;

}

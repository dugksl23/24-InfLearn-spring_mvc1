package com.example.itemservice.item.domain;


import com.example.itemservice.item.dto.ItemDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Item {


    @Id
    @GeneratedValue
    private Long id;

    private String itemName;
    private Integer quantity;
    private Integer price;

    public void setId(Long id) {
        this.id = id;
    }


    public static Item create(ItemDto item) {
        return Item.builder()
                .price(item.getPrice())
                .itemName(item.getItemName())
                .quantity(item.getQuantity())
                .build();
    }

    public Item update(ItemDto item) {
        return Item.builder()
                .id(item.getId())
                .price(item.getPrice())
                .itemName(item.getItemName())
                .quantity(item.getQuantity())
                .build();
    }
}

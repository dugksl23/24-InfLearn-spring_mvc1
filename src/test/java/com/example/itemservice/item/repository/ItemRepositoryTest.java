package com.example.itemservice.item.repository;

import com.example.itemservice.item.domain.Item;
import com.example.itemservice.item.dto.ItemDto;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Transactional
@Slf4j
class ItemRepositoryTest { //JUnit 5 부터는 pulic이 아니어도 괜찮다.


    private final ItemRepository itemRepository;

    @Autowired
    public ItemRepositoryTest(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {

        // given
        ItemDto item = ItemDto.builder().price(20000).quantity(30).itemName("Spring JPA").build();

        Item item1 = Item.create(item);

        // when
        Item save = itemRepository.save(item1);
        Item byId = itemRepository.findById(save.getId());

        // then
        Assertions.assertEquals(save.getId(), byId.getId());

    }

    @Test
    void findAll() {


        // given
        Item jpa2 = createItem(111, 20, "JPA2");
        Item jpa3 = createItem(111, 30, "JPA3");
//        List<Item> list = Arrays.asList(jpa3, jpa2);

        itemRepository.save(jpa2);
        itemRepository.save(jpa3);
        // when
        List<Item> all = itemRepository.findAll();
        // then
        org.assertj.core.api.Assertions.assertThat(all.size()).isEqualTo(2);
        org.assertj.core.api.Assertions.assertThat(all).contains(jpa2, jpa3);

    }

    @Test
    void update() {

        // given
        Item item = createItem(111, 20, "JPA2");
        Item save = itemRepository.save(item);
        Long id = itemRepository.findById(save.getId()).getId();
        // when
        ItemDto dto = ItemDto.builder().id(id).itemName("JPA3").quantity(11111).price(20000).build();
        itemRepository.update(dto);
        Item update = itemRepository.findById(id);

        // then
        Assertions.assertEquals(dto.getId(), update.getId());
        Assertions.assertEquals(dto.getItemName(), update.getItemName());
        Assertions.assertEquals(dto.getQuantity(), update.getQuantity());
        Assertions.assertEquals(dto.getPrice(), update.getPrice());
    }


    Item createItem(int price, int quantity, String itemName) {
        ItemDto dto = ItemDto.builder().price(price).quantity(quantity).itemName(itemName).build();

        return Item.create(dto);
    }
}
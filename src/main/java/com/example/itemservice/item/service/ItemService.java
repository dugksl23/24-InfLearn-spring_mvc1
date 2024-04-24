package com.example.itemservice.item.service;


import com.example.itemservice.item.domain.Item;
import com.example.itemservice.item.dto.ItemDto;
import com.example.itemservice.item.repository.ItemJpaRepository;
import com.example.itemservice.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemJpaRepository itemJpaRepository;
    private final ItemRepository itemRepository;

    public Item save(Item item) {
//        return itemRepository.save(item);
        return itemRepository.save(item);
    }

    public List<Item> findAll() {
//        return itemRepository.findAll();
        return itemRepository.findAll();
    }

    public Item findById(Long id) {
        return itemRepository.findById(id);
    }


    public Item updateItem(ItemDto item) {
        itemRepository.update(item);
        Item byId = itemRepository.findById(item.getId());
        return byId;
    }
}

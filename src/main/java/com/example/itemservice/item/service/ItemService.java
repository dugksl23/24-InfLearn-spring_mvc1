package com.example.itemservice.item.service;


import com.example.itemservice.item.domain.Item;
import com.example.itemservice.item.repository.ItemJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemJpaRepository itemRepository;

    public Item save(Item item) {
        return itemRepository.save(item);
    }


}

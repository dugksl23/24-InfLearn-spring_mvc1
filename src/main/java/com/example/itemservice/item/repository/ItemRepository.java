package com.example.itemservice.item.repository;

import com.example.itemservice.item.domain.Item;
import com.example.itemservice.item.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraintvalidators.RegexpURLValidator;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ItemRepository {

    private static final Map<Long, Item> store = new ConcurrentHashMap<>();
    // 동시 요청에 의한 접근일 경우에는 멀티쓰레드를 통해 접근할 수 있도록 ConcurrentHashMap을 사용해야 한다.
    // HashMap은 싱글 스레드를 지원한다.
    private static AtomicLong sequence = new AtomicLong();

    @Transactional
    public Item save(Item item) {
        long l = sequence.incrementAndGet();
        item.setId(l);
        store.put(l, item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        //List<Item> list = store.values().stream().toList();
        return new ArrayList<>(store.values());
        // map의 value는 collection이다.
        // list로 보낸 이유는, dto처럼 값을 immutable　하게 하기 위해서 연관관계와 관심사를 분리.
    }

    @Transactional
    public void update(ItemDto item) {
        Item findItem = store.get(item.getId());
        Item updateItem = findItem.update(item);

        store.put(findItem.getId(), updateItem);
    }

    public void clearStore() {
        store.clear();
    }

}

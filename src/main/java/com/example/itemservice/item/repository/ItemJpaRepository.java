package com.example.itemservice.item.repository;


import com.example.itemservice.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemJpaRepository extends JpaRepository <Item, Long> {



}

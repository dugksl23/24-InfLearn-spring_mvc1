package com.example.itemservice.web;

import com.example.itemservice.item.domain.Item;
import com.example.itemservice.item.dto.ItemDto;
import com.example.itemservice.item.service.ItemService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/basic/item")
@Slf4j
public class ItemBasicController {

    private final ItemService itemService;

    @GetMapping("/itemList")
    public String itemList(Model model) {
        List<Item> all = itemService.findAll();
        log.info("itemList size : {}", all.size());
        model.addAttribute("itemList", all);
        return "/item/itemList";

    }

    @GetMapping("/register")
    public String register(Model model) {
        return "/item/registerItem";
    }

    @GetMapping("/itemDetail/{id}")
    public String itemDetail(Model model, @PathVariable("id") Long id) {
        Item itemDetail = itemService.findById(id);
        model.addAttribute("item", itemDetail);
        return "/item/item";
    }


    /**
     * Test 용 Data input 메서드
     */
    @PostConstruct
    public void init() {
        dbInit();
    }

    private void dbInit() {
        Item jpa2 = createItem(111, 20, "JPA2");
        Item jpa3 = createItem(111, 30, "JPA3");

        itemService.save(jpa2);
        itemService.save(jpa3);
    }

    private Item createItem(int price, int quantity, String itemName) {
        ItemDto dto = ItemDto.builder().price(price).quantity(quantity).itemName(itemName).build();

        return Item.create(dto);
    }

}

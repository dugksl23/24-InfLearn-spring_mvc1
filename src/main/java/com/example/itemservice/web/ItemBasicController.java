package com.example.itemservice.web;

import com.example.itemservice.item.domain.Item;
import com.example.itemservice.item.dto.ItemDto;
import com.example.itemservice.item.service.ItemService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "item/itemList";

    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerItemForm", new ItemDto());
        return "item/registerItem";
    }

    @PostMapping("/register")
    public String registerForm(@ModelAttribute("registerItemForm") ItemDto itemDto, RedirectAttributes redirectAttributes) {
        itemService.save(itemDto);
        redirectAttributes.addAttribute("itemId", itemDto.getId());
        redirectAttributes.addAttribute("status", true);
        redirectAttributes.addAttribute("message", "Item registered successfully");
        //queryParameter 형식으로 반환된다.
        return "redirect:/basic/item/itemDetail/{itemId}";
    }

    @GetMapping("/itemDetail/{id}")
    public String itemDetail(Model model, @PathVariable("id") Long id) {
        Item itemDetail = itemService.findById(id);
        model.addAttribute("item", itemDetail);
        return "/item/item";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        log.info("update id : {}", id);
        Item item = itemService.findById(id);
        model.addAttribute("updateItemForm", item);
        return "item/updateItem";
    }

    @PostMapping("/update/{id}")
    public String updatedItem(@PathVariable("id") Long id, @ModelAttribute("updateItemForm") ItemDto itemDto) {
        log.info("updated id : {}", itemDto.getId());
        Item item1 = itemService.updateItem(itemDto);
        return "redirect:/basic/item/itemDetail/{id}";
    }


    /**
     * Test 용 Data input 메서드
     */
    @PostConstruct
    public void init() {
        dbInit();
    }

    private void dbInit() {
        ItemDto jpa2 = createItem(111, 20, "JPA2");
        ItemDto jpa3 = createItem(111, 30, "JPA3");

        itemService.save(jpa2);
        itemService.save(jpa3);
    }

    private ItemDto createItem(int price, int quantity, String itemName) {
        ItemDto dto = ItemDto.builder().price(price).quantity(quantity).itemName(itemName).build();

        return dto;
    }

}

package com.example.itemservice;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {

    @GetMapping("/")
    public String HomeController() {
        return "redirect:/basic/item/itemList";
        //redirect 는 view resolver를 호출해서 html 찾는 것이 아닌,
        // 다시금 controller로 리다이렉트 시키는 명령어이다.
    }
}

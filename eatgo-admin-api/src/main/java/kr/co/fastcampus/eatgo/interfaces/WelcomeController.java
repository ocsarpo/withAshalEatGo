package kr.co.fastcampus.eatgo.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Web에서 접근가능한 컨트롤러 명시
public class WelcomeController {

    @GetMapping("/")
    public String hello() {
        return "Hello, world!!!";
    }
}

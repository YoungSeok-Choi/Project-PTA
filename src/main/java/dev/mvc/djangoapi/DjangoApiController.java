package dev.mvc.djangoapi;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DjangoApiController {
    
    @PostMapping("/some/path")
    public void validApi() {
        System.out.println("api has been called ");
        // 하하하하하 windows 한글 주석 확인
        // 하하하하  여기서는 깨지지 않는것을 확인. 
    }
    
}


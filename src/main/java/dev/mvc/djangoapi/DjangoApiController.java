package dev.mvc.djangoapi;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DjangoApiController {
    
    @PostMapping("/some/path")
    public void validApi() {
        System.out.println("api ���������� ȣ�� ");
        
        
    }
    
}


package com.marvel.controllers.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerDocController {

    @GetMapping("/")
    public String getApiDocumentation() {
        return "redirect:swagger-ui.html";
    }

}

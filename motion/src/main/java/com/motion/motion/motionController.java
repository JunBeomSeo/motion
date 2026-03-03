package com.motion.motion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class motionController {

    @GetMapping("/")
    public String goPage() {
        return "index";
    }
    

}
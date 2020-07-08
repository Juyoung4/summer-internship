package com.juyoung.board;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {
	@RequestMapping(value="/main/loginpage")
    public String page() throws Exception {
        return "/main/loginpage";
    }
}	

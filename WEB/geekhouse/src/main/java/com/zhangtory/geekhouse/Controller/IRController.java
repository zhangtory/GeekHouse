package com.zhangtory.geekhouse.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IRController {

    @RequestMapping("/ir")
    public String irIndex() {
        return "ir";
    }

    @RequestMapping("/ir/{cmd}")
    @ResponseBody
    public String ir_controller(@PathVariable("cmd") String cmd) {
        System.out.println(cmd);
        return "success";
    }

}

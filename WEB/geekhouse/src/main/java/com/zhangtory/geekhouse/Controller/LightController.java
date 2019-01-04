package com.zhangtory.geekhouse.Controller;

import com.zhangtory.geekhouse.Service.LightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LightController {

    @Autowired
    private LightService lightService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/light/{cmd}")
    @ResponseBody
    public String light_controller(@PathVariable("cmd") String cmd) {
        this.lightService.lightCtl(cmd);
        return "success";
    }


}

package com.zhangtory.geekhouse.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IRController {

    @RequestMapping("/ir")
    public String irIndex() {
        return "ir";
    }

    /**
     * 添加计划
     * @param model
     * @return
     */
    @RequestMapping("/ir/addPlanPage")
    public String addPlanPage(Model model) {
        Map<Object, Object> opmap1 = new HashMap<>();
        Map<Object, Object> opmap2 = new HashMap<>();

        opmap1.put("value", 1);
        opmap1.put("name", "制热28度");
        opmap2.put("value", 2);
        opmap2.put("name", "关闭");

        List<Map<Object, Object>> oplist = new ArrayList<>();
        oplist.add(opmap1);
        oplist.add(opmap2);
        model.addAttribute("oplist", oplist);
        return "ir_addPlan";
    }

    /**
     * 保存计划
     * @param hour 时
     * @param minute 分钟
     * @param option 操作id
     * @return
     */
    @RequestMapping("/ir/addPlan")
    public String addPlan(int hour, int minute, int option) {
        System.out.println(hour+" "+minute+" "+option);
        return "redirect:/ir";
    }

    @RequestMapping("/ir/{cmd}")
    @ResponseBody
    public String ir_controller(@PathVariable("cmd") String cmd) {
        System.out.println(cmd);
        return "success";
    }

}

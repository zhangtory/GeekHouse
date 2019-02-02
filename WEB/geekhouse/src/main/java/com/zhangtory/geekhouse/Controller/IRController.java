package com.zhangtory.geekhouse.Controller;

import com.zhangtory.geekhouse.Service.IRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IRController {

    @Autowired
    private IRService irService;

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
        // 查询能够执行的操作，返回到页面上
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
        irService.addPlan(hour, minute, option);
        return "redirect:/ir";
    }

    /**
     * 删除计划
     * @param id
     * @return
     */
    @RequestMapping("/ir/deletePlan")
    public String deletePlan(int id) {
        irService.deletePlan(id);
        return "redirect:/ir";
    }

    @RequestMapping("/ir/{cmd}")
    @ResponseBody
    public String ir_controller(@PathVariable("cmd") String cmd) {
        System.out.println(cmd);
        return "success";
    }

}

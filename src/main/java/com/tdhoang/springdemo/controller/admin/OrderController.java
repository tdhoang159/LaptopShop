package com.tdhoang.springdemo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    @GetMapping("/admin/orders")
    public String getOrderDashBoardPage(){
        return "admin/order/view";
    }
}

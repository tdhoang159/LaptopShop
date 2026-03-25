package com.tdhoang.springdemo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
    @GetMapping("/admin/products")
    public String getProductDashBoardPage(){
        return "admin/product/view";
    }
}

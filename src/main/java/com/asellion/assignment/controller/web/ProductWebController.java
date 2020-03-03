package com.asellion.assignment.controller.web;

import com.asellion.assignment.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/products")
@RequiredArgsConstructor
public class ProductWebController {
    private final ProductService productService;

    @RequestMapping({"/", ""})
    public String getAllProducts(Model model) {
        model.addAttribute("productList", productService.getAllProducts());
        return "products";
    }
}

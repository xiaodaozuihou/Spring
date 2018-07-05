package com.spring.controller;

import com.spring.model.Product;
import com.spring.model.ProductForm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
    private static final Log logger = LogFactory.getLog(ProductController.class);

    @RequestMapping(value = "/product_input")
    public String inputProduct(){
        logger.info("inputProduct called");
        return "ProductForm";
    }

    @RequestMapping(value = "product_save")
    public String saveProduct(ProductForm productForm, Model model){
        //此处使用model是为了添加需要在视图中显示的属性
        logger.info("saveProduct called");
        Product product = new Product();
        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        product.setPrice(Float.parseFloat(productForm.getPrice()));
        model.addAttribute("product",product);
        return "ProductDetails";
    }
}

package com.spring.controller;

import com.spring.model.Product;
import com.spring.service.ProductService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    private static final Log logger = LogFactory.getLog(ProductController.class);

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/product_input")
    public String inputProduct(){
        logger.info("inputProduct called");
        return "ProductForm";
    }

   /* @RequestMapping(value = "product_save")
    public String saveProduct(ProductForm productForm, Model model){
        //此处使用model是为了添加需要在视图中显示的属性
        logger.info("saveProduct called");
        Product product = new Product();
        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        product.setPrice(Float.parseFloat(productForm.getPrice()));
        model.addAttribute("product",product);
        return "ProductDetails";
    }*/

   /*@RequestMapping(value = "/product_save", method = RequestMethod.POST)
   public String saveProduct(ProductForm productForm, RedirectAttributes redirectAttributes){
       logger.info("saveProduct called");
       Product product = new Product();
       product.setName(productForm.getName());
       product.setDescription(productForm.getDescription());
       product.setPrice(Float.parseFloat(productForm.getPrice()));

       Product savedProduct = productService.add(product);
       redirectAttributes.addFlashAttribute("message","The product was successfully added.");
       return "redirect:/product_view/" + savedProduct.getId();
   }*/

   @RequestMapping(value = "/product_save")
   public String saveProduct(HttpServletRequest servletRequest, @ModelAttribute Product product,
                             BindingResult bindingResult, Model model) {
       List<MultipartFile> files = product.getImages();
       List<String> fileNames = new ArrayList<String>();
       if (null != files && files.size() > 0) {
           for (MultipartFile multipartFile : files) {
               String fileName = multipartFile.getOriginalFilename();
               fileNames.add(fileName);
               File imageFile = new File(servletRequest.getServletContext().getRealPath("/WEB-INF/image"), fileName);
               try {
                   multipartFile.transferTo(imageFile);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }
       // save product here
       model.addAttribute("product", product);
       return "ProductDetails";
   }

    @RequestMapping(value = "/product_view/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        Product product = productService.get(id);
        model.addAttribute("product", product);
        return "ProductView";
    }
}

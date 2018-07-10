package com.spring.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Date;

public class ProductValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;
        ValidationUtils.rejectIfEmpty(errors,"name","productName.required");
        ValidationUtils.rejectIfEmpty(errors,"price","price.required");
        ValidationUtils.rejectIfEmpty(errors,"productionDate","productionDate.required");
        float price = product.getPrice();
        if (price < 0){
            errors.rejectValue("price","price.negative");
        }
        Date productionDate = product.getProductionDate();
        if (productionDate != null){
            if (productionDate.after(new Date())){
                System.out.println("salah lagi");
                errors.rejectValue("productionDate","productionDate.invalid");
            }
        }
    }
}

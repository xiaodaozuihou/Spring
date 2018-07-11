package com.spring.model;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class Product {
    private long id;
    @Size(min = 1,max = 10)
    private String name;
    private String description;
    private float price;
    private Date productionDate;
    private List<MultipartFile> images;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public List<MultipartFile> getImages() {
        return images;
    }

    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }
}

package com.example.productservice.controllers;

import com.example.productservice.dtos.DbProductDto;
import com.example.productservice.dtos.ProductTitleRequestDto;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.services.CategoryService;
import com.example.productservice.services.SelfDbProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;
    private SelfDbProductService selfDbProductService;

    public CategoryController(CategoryService categoryServiceImplementation,
                              SelfDbProductService selfDbProductService) {
        this.categoryService = categoryServiceImplementation;
        this.selfDbProductService = selfDbProductService;
    }


    @GetMapping()
    public List<String> getCategories() {
        return categoryService.getCategories();
    }


    @GetMapping("/{uuid}")
    public List<DbProductDto> getCategoryProducts(@PathVariable("uuid") String uuid) {
        Category category = categoryService.getCategoryById(uuid);
        List<Product> products = category.getProducts();

        List<DbProductDto> dbProductDtos = new ArrayList<>();
        for (Product product : products) {
            DbProductDto dbProductDto = selfDbProductService.convertProductToDbProductDto(product);
            dbProductDtos.add(dbProductDto);
        }
        return dbProductDtos;
    }

    @GetMapping("/productTitles")
    public List<String> getProductTitles(@RequestBody ProductTitleRequestDto productTitleRequestDto) {
        List<String> uuids = productTitleRequestDto.getUuids();

        return categoryService.getProductTitles(uuids);
    }
}

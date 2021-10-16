package com.grail.ecom.controller;

import com.grail.ecom.dto.ProductDto;
import com.grail.ecom.model.Category;
import com.grail.ecom.model.Product;
import com.grail.ecom.service.CategoryService;
import com.grail.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private ProductService productService;
  private static String uploadDir=System.getProperty("user.dir")+"/src/main/resources/static/productImages";

  @GetMapping("/admin")
  public String adminHome() {
    return "adminHome";
  }

  @GetMapping("/admin/categories")
  public String getCategories(Model model) {
    model.addAttribute("categories",categoryService.getAllCategory());
    return "categories";
  }

  @PostMapping("/admin/categories/add")
  public String postCategoryAdd(@ModelAttribute("category") Category category) {
    categoryService.addCategory(category);
    return "redirect:/admin/categories";
  }
  @GetMapping("/admin/categories/add")
  public String getCategoryAdd(Model model){
    model.addAttribute("category",new Category());
    return "categoriesAdd";
  }
  @GetMapping("/admin/categories/delete/{id}")
  public String deleteCategory(@PathVariable int id){
    categoryService.removeCategoryById(id);
    return "redirect:/admin/categories";
  }

  @GetMapping("/admin/categories/update/{id}")
  public String updateCategory(@PathVariable int id, Model model){
    Optional<Category> category=categoryService.findCategoryById(id);
    if(category.isPresent()){
      model.addAttribute("category",category.get());
      return "categoriesAdd";
    }else{
      return "404";
    }
  }

  //Product Section
  @GetMapping("/admin/products")
  public String getProducts(Model model) {
    model.addAttribute("products",productService.getAllProducts());
    return "products";
  }
  @GetMapping("/admin/products/add")
  public String getProductAdd(Model model) {
    model.addAttribute("productDTO",new ProductDto());
    model.addAttribute("categories",categoryService.getAllCategory());
    return "productsAdd";
  }

  @PostMapping("/admin/products/add")
  public String postProductAdd(@ModelAttribute("productDTO")ProductDto productDto,
                               @RequestParam("productImage")MultipartFile file,
                               @RequestParam("imgName")String imgName) throws IOException {
    Product product=new Product();
    product.setId(productDto.getId());
    product.setName(productDto.getName());
    product.setCategory(categoryService.findCategoryById(productDto.getCategoryId()).get());
    product.setPrice(productDto.getPrice());
    product.setWeight(productDto.getWeight());
    product.setDescription(productDto.getDescription());
    String imageUUID;
    if(!file.isEmpty()){
      imageUUID= file.getOriginalFilename();
      Path fileNameAndPath= Paths.get(uploadDir,imageUUID);
      Files.write(fileNameAndPath,file.getBytes());
    }else{
      imageUUID=imgName;
    }
    product.setImageName(imageUUID);
    productService.addProduct(product);
    return "redirect:/admin/products";
  }

  @GetMapping("/admin/product/delete/{id}")
  public String deleteProduct(@PathVariable int id){
    productService.removeProductById(id);
    return "redirect:/admin/products";
  }

  @GetMapping("/admin/product/update/{id}")
  public String updateProduct(@PathVariable long id, Model model){

    Optional< Product> product=productService.findProductById(id);
    if(product.isPresent()){

      ProductDto productDTO=new ProductDto();
      productDTO.setId(product.get().getId());
      productDTO.setName(product.get().getName());
      productDTO.setCategoryId(product.get().getCategory().getId());
      productDTO.setPrice(product.get().getPrice());
      productDTO.setWeight(product.get().getWeight());
      productDTO.setDescription(product.get().getDescription());
      productDTO.setImageName(product.get().getImageName());

      model.addAttribute("categories",categoryService.getAllCategory());
      model.addAttribute("productDTO",productDTO);
      return "productsAdd";
    }
    return "404";
  }

}

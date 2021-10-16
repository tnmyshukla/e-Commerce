package com.grail.ecom.service;

import com.grail.ecom.model.Product;
import com.grail.ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;

  public List<Product> getAllProducts(){
    return productRepository.findAll();
  }
  public void addProduct(Product product){
    productRepository.save(product);
  }
  public void removeProductById(long id){
    productRepository.deleteById(id);
  }
  public Optional<Product> findProductById(long id){
     return productRepository.findById(id);
  }
  public List<Product> getAllProductsByCategoryId(int id){
    return productRepository.findAllByCategoryId(id);
  }

}

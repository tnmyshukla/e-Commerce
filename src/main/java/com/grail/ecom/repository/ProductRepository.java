package com.grail.ecom.repository;

import com.grail.ecom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
  List<Product> findAllByCategoryId(int id);
}

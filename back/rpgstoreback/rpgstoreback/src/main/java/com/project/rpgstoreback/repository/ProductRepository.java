package com.project.rpgstoreback.repository;

import com.project.rpgstoreback.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}

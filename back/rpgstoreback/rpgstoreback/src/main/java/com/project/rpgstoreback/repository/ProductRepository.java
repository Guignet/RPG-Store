package com.project.rpgstoreback.repository;

import com.project.rpgstoreback.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
     boolean existsByTitle(String title);
}

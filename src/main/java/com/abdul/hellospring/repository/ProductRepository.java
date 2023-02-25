package com.abdul.hellospring.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.abdul.hellospring.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}

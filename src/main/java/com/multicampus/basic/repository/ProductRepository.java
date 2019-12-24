package com.multicampus.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multicampus.basic.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
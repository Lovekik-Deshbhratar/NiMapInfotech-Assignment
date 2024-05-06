package com.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.model.Product;

/*Service layer which hide the where the actual logic is, It provide security layer on the backend*/
public interface ProductService {
	Page<Product> findAll(Pageable pageable);

    Product addProduct(Product product);
    
	Product findByPid(int pid);

	void deleteByPid(int pid);
}

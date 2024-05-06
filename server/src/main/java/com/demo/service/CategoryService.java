package com.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.model.Category;
import com.demo.model.Product;

/*Service layer which hide the where the actual logic is, It provide security layer on the backend*/
public interface CategoryService {
	Page<Category> findAll(Pageable pageable);

	Category addCategory(Category category);
    
	Category findByCid(int cid);

	void deleteByCid(int cid);
}

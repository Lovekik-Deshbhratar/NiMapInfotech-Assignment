package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.CategoryDao;
import com.demo.model.Category;
import com.demo.model.Product;

/*This class provide implementation to the service class*/
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryDao cd;

	@Override
	public Category addCategory(Category category) {
		return cd.save(category);
	}

	@Override
	public Category findByCid(int cid) {
		return cd.findByCid(cid);
	}

	@Override
	public void deleteByCid(int cid) {
		cd.deleteById(cid);
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
		return cd.findAll(pageable);
	}
	
}

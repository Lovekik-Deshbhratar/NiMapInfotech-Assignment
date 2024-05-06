package com.demo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Category;
import com.demo.model.Product;

/*Dao(Data Access Object) is a layer which handle all CRUD operations*/
@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {

	Page<Category> findAll(Pageable pageable);

	Category findByCid(int cid);

	Category save(Category category);

	void deleteByCid(int cid);
}

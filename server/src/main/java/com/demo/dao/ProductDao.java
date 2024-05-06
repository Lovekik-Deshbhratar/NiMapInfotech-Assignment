package com.demo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Product;

/*Dao(Data Access Object) is a layer which handle all CRUD operations*/
@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

	Page<Product> findAll(Pageable pageable);

	Product findByPid(int pid);

	Product save(Product product);

	void deleteByPid(int pid);

}

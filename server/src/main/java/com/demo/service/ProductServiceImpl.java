package com.demo.service;

import java.util.List;
	

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.ProductDao;
import com.demo.model.Product;

/*This class provide implementation to the service class*/
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao pd;
	
	@Override
    public Page<Product> findAll(Pageable pageable) {
        return pd.findAll(pageable);
    }

	@Override
	public Product findByPid(int pid) {
		return pd.findByPid(pid);
	}

	@Override
	public void deleteByPid(int pid) {
		pd.deleteById(pid);
	}

	@Override
	public Product addProduct(Product product) {
		return pd.save(product);
	}
}

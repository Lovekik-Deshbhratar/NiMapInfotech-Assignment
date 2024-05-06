package com.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Category;
import com.demo.model.Product;
import com.demo.service.ProductService;

/*It handle all API request and response*/
@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService ps;

//	@GetMapping
//	public ResponseEntity<List<Product>> getAll() {
//		List<Product> products = ps.findAll();
//		return new ResponseEntity<>(products, HttpStatus.OK);
//	}

	// To get all record
	@GetMapping
	public ResponseEntity<List<Product>> getAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "pid") String sortBy) {
		Pageable paging = PageRequest.of(page, pageSize, Sort.by(sortBy));
		Page<Product> pagedResult = ps.findAll(paging);
		List<Product> products = pagedResult.getContent();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	// To find one by id
	@GetMapping(value = "{id}")
	public ResponseEntity<Product> findOne(@PathVariable int id) {
		Product product = ps.findByPid(id);
		if (product != null) {
			Category category = product.getCategory();
			product.setCategory(category);
			return new ResponseEntity<>(product, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

//	@PostMapping(value = "")
//	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
//		Product addedProduct = ps.addProduct(product);
//		return new ResponseEntity<Product>(addedProduct, HttpStatus.CREATED);
//	}

	// To save one or multiple record
	@PostMapping(value = "")
	public ResponseEntity<List<Product>> saveProduct(@RequestBody List<Product> products) {
		if(products.size()==0)
			 return new ResponseEntity<List<Product>>( HttpStatus.NO_CONTENT);
		
		
		List<Product> list = new ArrayList<Product>();
		for (Product product : products) {
			list.add(ps.addProduct(product));
		}
		return new ResponseEntity<List<Product>>(list, HttpStatus.CREATED);
	}

	// To update one by id
	@PutMapping(value = "{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
		Product existingProduct = ps.findByPid(id);

		if (existingProduct != null) {
			existingProduct.setPname(product.getPname());
			existingProduct.setPprice(product.getPprice());

			return new ResponseEntity<Product>(ps.addProduct(existingProduct), HttpStatus.OK);
		} else
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}

	// To delete one by id
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable int id) {
		Product existingProduct = ps.findByPid(id);

		if (existingProduct != null) {
			ps.deleteByPid(id);
			return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
		} else
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);

	}
}

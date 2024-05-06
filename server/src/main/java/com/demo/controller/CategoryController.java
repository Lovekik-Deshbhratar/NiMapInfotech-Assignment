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
import com.demo.service.CategoryService;

/*It handle all API request and response*/
@RestController
@RequestMapping("api/categories")
public class CategoryController {

	@Autowired
	private CategoryService cs;

//	@GetMapping
//	public ResponseEntity<List<Category>> getAll() {
//		List<Category> category = cs.findAll();
//		return new ResponseEntity<>(category, HttpStatus.OK);
//	}

	// To get all record
	@GetMapping
	public ResponseEntity<List<Category>> getAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "cid") String sortBy) {
		Pageable paging = PageRequest.of(page, pageSize, Sort.by(sortBy));
		Page<Category> pagedResult = cs.findAll(paging);
		List<Category> products = pagedResult.getContent();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	// To find one by id
	@GetMapping(value = "{id}")
	public Category findOne(@PathVariable int id) {
		return cs.findByCid(id);
	}

	// To save one or multiple record
	@PostMapping(value = "")
	public ResponseEntity<List<Category>> saveCategory(@RequestBody List<Category> categories) {
		if(categories.size()==0)
			 return new ResponseEntity<List<Category>>( HttpStatus.NO_CONTENT);
			
		List<Category> list = new ArrayList<Category>();
		for (Category category : categories) {
			list.add(cs.addCategory(category));
		}
		return new ResponseEntity<List<Category>>(list, HttpStatus.CREATED);
	}

	// To update one by id
	@PutMapping(value = "{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category category) {
		Category existingCategory = cs.findByCid(id);

		if (existingCategory != null) {
			existingCategory.setCname(category.getCname());

			return new ResponseEntity<Category>(cs.addCategory(existingCategory), HttpStatus.OK);
		} else
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
	}

	// To delete one by id
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Category> deleteProduct(@PathVariable int id) {
		Category existingCategory = cs.findByCid(id);

		if (existingCategory != null) {
			cs.deleteByCid(id);
			return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
		} else
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);

	}
}

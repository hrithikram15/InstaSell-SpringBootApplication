package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.exception.ProductAlreadyExistsException;
import com.product.exception.ProductNotFoundException;
import com.product.model.Product;
import com.product.service.ProductServiceImpl;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductServiceImpl productService;
	
private ResponseEntity<?> responseEntity;
	
	@PostMapping("/addProduct")
	public ResponseEntity<?> addProductHandler(@RequestBody Product product) throws ProductAlreadyExistsException{
		try {
			Product createdProduct = productService.addProduct(product);
			this.responseEntity = new ResponseEntity<>(createdProduct,HttpStatus.CREATED);
		} catch (ProductAlreadyExistsException exception) {
			throw exception;
		}
		catch(Exception e) {
			System.out.println(e);
			this.responseEntity = new ResponseEntity<>("Some internal error occured..", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return this.responseEntity;
	}
	
	@GetMapping("/getProduct/{pId}")
	public ResponseEntity<?> getProductByIdHandler(@PathVariable int pId) throws ProductNotFoundException{
		Product product = this.productService.getProductById(pId);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	@GetMapping("/getAllProducts")
	public List<Product> getAllProductsHandler(){
		return this.productService.getAllProducts();
		
	}
	
	@PutMapping("/updateProduct/{pId}")
	public ResponseEntity<?> updateProductHandler(@RequestBody Product pObj, @PathVariable int pId) throws ProductNotFoundException{
		Product product = this.productService.updateProduct(pObj, pId);
		return new ResponseEntity<>(product, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteProduct/{pId}")
	public ResponseEntity<?> deleteProductHandler(@PathVariable int pId) throws ProductNotFoundException{
		this.productService.deleteProduct(pId);
		return new ResponseEntity<>("Product Deleted Successfully", HttpStatus.OK);
	}
	

}

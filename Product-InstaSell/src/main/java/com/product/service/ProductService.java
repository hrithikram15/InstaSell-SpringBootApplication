package com.product.service;

import java.util.List;

import com.product.exception.ProductAlreadyExistsException;
import com.product.exception.ProductNotFoundException;
import com.product.model.Product;

public interface ProductService {
	public Product addProduct(Product pObj) throws ProductAlreadyExistsException;
	public Product updateProduct(Product pObj, int pId) throws ProductNotFoundException;
	public Product getProductById(int pid) throws ProductNotFoundException;
	public List<Product> getAllProducts();
	public boolean deleteProduct(int pid) throws ProductNotFoundException;

}

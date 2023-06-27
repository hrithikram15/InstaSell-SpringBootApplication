package com.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.exception.ProductAlreadyExistsException;
import com.product.exception.ProductNotFoundException;
import com.product.model.Product;
import com.product.repository.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepo productRepo;

	@Override
	public Product addProduct(Product pObj) throws ProductAlreadyExistsException {
		Optional<Product> optional = this.productRepo.findById(pObj.getProductId());
		Product addObj = null;
		
		if(optional.isPresent()) {
			System.out.println("Product Details already exists ...");
			throw new ProductAlreadyExistsException();

		}else {
			addObj = this.productRepo.save(pObj);
		}
		return addObj;
	}

	@Override
	public Product updateProduct(Product pObj, int pId) throws ProductNotFoundException {
		Optional<Product> optional = this.productRepo.findById(pId);
		Product pobj = null;
		Product updatedData = null;
		
		if(optional.isPresent()) {
			System.out.println("Can update the product ...");
			pobj = optional.get();
			
			pobj.setProductName(pObj.getProductName());
			pobj.setProductDescription(pObj.getProductDescription());
			pobj.setBrand(pObj.getBrand());
			pobj.setProductActualPrice(pObj.getProductActualPrice());
			pobj.setProductDiscountedPrice(pObj.getProductDiscountedPrice());
			
			updatedData = this.productRepo.save(pobj);

		}else {
			throw new ProductNotFoundException();
		}
		return updatedData;
	}

	@Override
	public Product getProductById(int pid) throws ProductNotFoundException {
Optional<Product> optional = this.productRepo.findById(pid);
		
		Product pobj = null;
		
		if(optional.isPresent()) {
			pobj = optional.get();
		}else {
			throw new ProductNotFoundException();
		}
				return pobj;
	}

	@Override
	public List<Product> getAllProducts() {
		return this.productRepo.findAll();

	}

	@Override
	public boolean deleteProduct(int pid) throws ProductNotFoundException {
		Optional<Product> optional = this.productRepo.findById(pid);

		boolean status = false;
		
		if(optional.isPresent()) {
        	System.out.println("Product Found and ready for Delete !!!");
        	this.productRepo.deleteById(pid);
        	status = true;

		}else {
			throw new ProductNotFoundException();
		}
		return status;
	}
	

}

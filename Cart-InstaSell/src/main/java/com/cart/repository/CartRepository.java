package com.cart.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cart.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
	
	@Query("SELECT c FROM Cart c WHERE c.userName = ?1")
	public List<Cart> findByUserName(String userName);
	
    @Query("SELECT c FROM Cart c WHERE c.userName = ?1 AND c.productId = ?2")
	public Cart findByUserNameAndProductId(String userName, Integer productId);

	
//    @Query("DELETE FROM Cart c WHERE c.userName = :userName AND c.productId = :productId")
//	public void deleteProductFromCart(String userName, Integer productId);
//    
    

	
}

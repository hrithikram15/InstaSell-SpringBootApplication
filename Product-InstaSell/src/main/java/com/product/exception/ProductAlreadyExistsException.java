package com.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.CONFLICT,reason="Product Already Exists Exception ..")
public class ProductAlreadyExistsException extends Exception{

}

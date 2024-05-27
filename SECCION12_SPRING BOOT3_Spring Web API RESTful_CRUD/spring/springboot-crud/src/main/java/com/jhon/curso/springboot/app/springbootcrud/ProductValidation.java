package com.jhon.curso.springboot.app.springbootcrud;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.jhon.curso.springboot.app.springbootcrud.entities.Product;
/* Es una clase donde se valida la entidad Producto  */
@Component
public class ProductValidation implements Validator{

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @SuppressWarnings("null")
    @Override
    public void validate(@NonNull Object target,@NonNull Errors errors) {
        Product product = (Product) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",null, "Es requerido"); /* Aqui se esta haciendo una validacion directa sin utilizar if */
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors,"description", "NotBlank.product.description");
        if (product.getDescription() == null || product.getDescription().isBlank()) {
            errors.rejectValue("description", null, "NotBlank.product.description");            
        }

        if (product.getPrice() == null) {
            errors.rejectValue("price", null,"NotNull.product.price");            
        } else if (product.getPrice() < 500) {
            errors.rejectValue("price", null,"Min.product.price");
        }
    }

}

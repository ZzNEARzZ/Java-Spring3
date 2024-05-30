package com.jhon.curso.springboot.app.springbootcrud.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = RequiredValidation.class) /*Se enlaza con la clase que es para validar */
@Retention(RetentionPolicy.RUNTIME) /*Se ejecuta en tiempo de ejecucion */
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface IsRequired {

    String message() default "es reuqrido usando anotaciones";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}

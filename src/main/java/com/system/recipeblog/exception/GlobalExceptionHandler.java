package com.system.recipeblog.exception;

import com.system.recipeblog.dto.GenericResponse;
import com.system.recipeblog.exception.ex.CategoryNotFoundException;
import com.system.recipeblog.exception.ex.IngredientNotFoundException;
import com.system.recipeblog.exception.ex.RecipeNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public GenericResponse handleCategoryNotFoundException(CategoryNotFoundException cnfe){
        return new GenericResponse(cnfe.getMessage(), false);
    }

    @ExceptionHandler(RecipeNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public GenericResponse handleRecipeNotFoundException(RecipeNotFoundException rnfe){
        return new GenericResponse(rnfe.getMessage(), false);
    }
    @ExceptionHandler(IngredientNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public GenericResponse handleIngredientNotFoundException(IngredientNotFoundException infe){
        return new GenericResponse(infe.getMessage(), false);
    }
}

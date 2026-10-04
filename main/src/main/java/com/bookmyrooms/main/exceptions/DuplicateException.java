package com.bookmyrooms.main.exceptions;

public class DuplicateException extends RuntimeException{
    public DuplicateException(String resourceName, String reference, String referenceValue) {
        super(resourceName + " with "+ reference +" = "+ referenceValue + " already exists!");
    }
}

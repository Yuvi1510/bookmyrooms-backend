package com.bookmyrooms.main.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String resource, String reference, String referenceValue) {
        super(resource + " with " + reference + " = " + referenceValue + " not found!");
    }
}

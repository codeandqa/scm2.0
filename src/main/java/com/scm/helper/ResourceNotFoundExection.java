package com.scm.helper;

public class ResourceNotFoundExection extends RuntimeException {

    public ResourceNotFoundExection(String message) {
        super(message);
    }

    public ResourceNotFoundExection() {
        super("Resource Not found in the repo");
    }
}

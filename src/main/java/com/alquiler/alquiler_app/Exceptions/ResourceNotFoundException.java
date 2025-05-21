package com.alquiler.alquiler_app.Exceptions;

public class ResourceNotFoundException extends RuntimeException {
    
  public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }

}

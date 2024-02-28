package com.betrybe.agrix.controllers.exception;

public class CropNotFoundException extends RuntimeException {
  public CropNotFoundException(String message) {
    super("Plantação não encontrada!");
  }
}


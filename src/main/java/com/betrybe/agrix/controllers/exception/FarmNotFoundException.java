package com.betrybe.agrix.controllers.exception;

public class FarmNotFoundException extends RuntimeException {
  public FarmNotFoundException(String message) {
    super("Fazenda não encontrada!");
  }
}


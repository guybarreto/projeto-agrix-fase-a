package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Farm;

public record FarmDTO (Long id, String name, Double size) {

  public FarmDTO (Farm farm) {
    this(
        farm.getId(),
        farm.getName(),
        farm.getSize()
    );
  }
  public static FarmDTO farmToFarmDTO(Farm farm) {
    return new FarmDTO(farm);
  }
}
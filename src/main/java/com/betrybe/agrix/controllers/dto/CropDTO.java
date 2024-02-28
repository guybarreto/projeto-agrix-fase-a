package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Crop;

public record CropDTO(Long id, String name, Double plantedArea, Long farmId) {

  public CropDTO(Crop crop) {
    this(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getFarm().getId()
    );
  }

  public static CropDTO cropToCropDTO(Crop crop) {
    return new CropDTO(crop);
  }
}
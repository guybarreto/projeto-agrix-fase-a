package com.betrybe.agrix.services;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.CropRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CropService {

  private final CropRepository cropRepository;
  private final FarmService farmService;

  @Autowired
  public CropService(CropRepository cropRepository, FarmService farmService) {
    this.cropRepository = cropRepository;
    this.farmService = farmService;
  }

  public Crop createCrop(Long farmId, Crop crop) {
    Farm farm = farmService.getFarmById(farmId);
    crop.setFarm(farm);
    return cropRepository.save(crop);
  }

  public List<Crop> getAllCropsByFarmId(Long farmId) {
    Farm farm = farmService.getFarmById(farmId);
    return farm.getCrops();
  }

  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }
}

package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDTO;
import com.betrybe.agrix.controllers.dto.FarmDTO;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.services.CropService;
import com.betrybe.agrix.services.FarmService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmService farmService;
  private final CropService cropService;

  @Autowired
  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  @PostMapping
  public ResponseEntity<FarmDTO> createFarm(@RequestBody Farm farm) {
    Farm newFarm = farmService.createFarm(farm);
    FarmDTO newFarmDTO = FarmDTO.farmToFarmDTO(newFarm);
    return ResponseEntity.status(HttpStatus.CREATED).body(newFarmDTO);
  }

  @GetMapping
  public ResponseEntity<List<FarmDTO>> getAllFarms() {
    List<Farm> farms = farmService.getAllFarms();

    List<FarmDTO> farmDTOList = farms.stream()
        .map(FarmDTO::farmToFarmDTO)
        .toList();

    return ResponseEntity.status(HttpStatus.OK).body(farmDTOList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<FarmDTO> getFarmById(@PathVariable Long id) {
    Farm farm = farmService.getFarmById(id);
    FarmDTO farmDTO = FarmDTO.farmToFarmDTO(farm);
    return ResponseEntity.status(HttpStatus.OK).body(farmDTO);
  }

  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDTO> createCrop(
      @PathVariable Long farmId,
      @RequestBody Crop crop
  ) {
    Crop newCrop = cropService.createCrop(farmId, crop);
    CropDTO newCropDTO = CropDTO.cropToCropDTO(newCrop);
    return ResponseEntity.status(HttpStatus.CREATED).body(newCropDTO);
  }

  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CropDTO>> getAllCropsByFarmId(@PathVariable Long farmId) {
    List<Crop> crops = cropService.getAllCropsByFarmId(farmId);

    List<CropDTO> cropDTOList = crops.stream()
        .map(CropDTO::cropToCropDTO)
        .toList();

    return ResponseEntity.status(HttpStatus.OK).body(cropDTOList);
  }
}

package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDTO;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.services.CropService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/farms/{farmId}/crops")
public class CropController {

  private final CropService cropService;

  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  @PostMapping
  public ResponseEntity<CropDTO> createCrop(
      @PathVariable Long farmId,
      @RequestBody Crop crop
  ) {
    Crop newCrop = cropService.createCrop(farmId, crop);
    CropDTO newCropDTO = CropDTO.cropToCropDTO(newCrop);
    return ResponseEntity.status(HttpStatus.CREATED).body(newCropDTO);
  }

  @GetMapping
  public ResponseEntity<List<CropDTO>> getAllCropsByFarmId(@PathVariable Long farmId) {
    List<Crop> crops = cropService.getAllCropsByFarmId(farmId);

    List<CropDTO> cropDTOList = crops.stream()
        .map(CropDTO::cropToCropDTO)
        .toList();

    return ResponseEntity.status(HttpStatus.OK).body(cropDTOList);
  }
}

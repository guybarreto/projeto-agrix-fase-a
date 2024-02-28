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
@RequestMapping("/crops")
public class CropController {

  private final CropService cropService;

  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<CropDTO> getCropById(@PathVariable Long id) {
    Crop crop = cropService.getCropById(id);
    CropDTO cropDTO = CropDTO.cropToCropDTO(crop);
    return ResponseEntity.status(HttpStatus.OK).body(cropDTO);
  }

  @GetMapping()
  public ResponseEntity<List<CropDTO>> getAllCrops() {
    List<Crop> crops = cropService.getAllCrops();

    List<CropDTO> cropDTOList = crops.stream()
        .map(CropDTO::cropToCropDTO)
        .toList();

    return ResponseEntity.status(HttpStatus.OK).body(cropDTOList);
  }
}

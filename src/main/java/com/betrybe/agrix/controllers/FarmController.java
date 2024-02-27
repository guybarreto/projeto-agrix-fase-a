package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.FarmDTO;
import com.betrybe.agrix.models.entities.Farm;
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

  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  @PostMapping
  public ResponseEntity<Farm> createFarm(@RequestBody FarmDTO farmDTO) {
    Farm newFarm = farmService.createFarm(farmDTO.toFarm());
    return ResponseEntity.status(HttpStatus.CREATED).body(newFarm);
  }

  @GetMapping
  public ResponseEntity<List<FarmDTO>> getAllFarms() {
    List<Farm> farms = farmService.getAllFarms();

    List<FarmDTO> farmDTOList = farms.stream()
        .map(farm -> new FarmDTO(farm.getId(), farm.getName(), farm.getSize()))
        .toList();

    return ResponseEntity.status(HttpStatus.OK).body(farmDTOList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<FarmDTO> getFarmById(@PathVariable Long id) {
    Farm farm = farmService.getFarmById(id);
    FarmDTO farmDTO = new FarmDTO(farm.getId(), farm.getName(), farm.getSize());
    return ResponseEntity.status(HttpStatus.OK).body(farmDTO);
  }
}

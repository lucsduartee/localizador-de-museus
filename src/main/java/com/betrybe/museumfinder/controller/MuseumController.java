package com.betrybe.museumfinder.controller;

import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import com.betrybe.museumfinder.util.ModelDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller MuseumController.
 */
@RestController
@RequestMapping("/museums")
public class MuseumController {
  MuseumServiceInterface museumService;

  @Autowired
  public MuseumController(MuseumServiceInterface museumService) {
    this.museumService = museumService;
  }

  /**
   * Creates museum.
   *
   * @param newMuseum museum data
   * @return museum created
   */
  @PostMapping
  public ResponseEntity<Museum> createMuseum(@RequestBody MuseumCreationDto newMuseum) {
    Museum museum = museumService.createMuseum(ModelDtoConverter.dtoToModel(newMuseum));

    return ResponseEntity.status(HttpStatus.CREATED).body(museum);
  }

  /**
   * Get for the closest Museum.
   *
   * @param lat latitude
   * @param lng longitude
   * @param maxDistKm maxDistKm
   * @return a closest museum
   */
  @GetMapping("/closest")
  public ResponseEntity<Museum> getClosestMuseum(
      @RequestParam String lat,
      @RequestParam String lng,
      @RequestParam(name = "max_dist_km") String maxDistKm
  ) {
    Coordinate coordinate = new Coordinate(Double.parseDouble(lat), Double.parseDouble(lng));
    Museum museum = museumService.getClosestMuseum(coordinate, Double.parseDouble(maxDistKm));

    return ResponseEntity.ok().body(museum);
  }
}

package com.betrybe.museumfinder.service;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.util.CoordinateUtil;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe MuseumService.
 */
@Service
public class MuseumService implements MuseumServiceInterface {
  @Autowired
  private MuseumFakeDatabase dbConnection;

  @Override
  public Museum getClosestMuseum(Coordinate coordinate, Double maxDistance) {
    boolean isValidCoordinate = CoordinateUtil.isCoordinateValid(coordinate);
    if (!isValidCoordinate) {
      throw new InvalidCoordinateException();
    }

    Optional<Museum> museum = dbConnection.getClosestMuseum(coordinate, maxDistance);

    if (!museum.isPresent()) {
      throw new MuseumNotFoundException();
    }

    return museum.get();
  }

  @Override
  public Museum createMuseum(Museum museum) throws InvalidCoordinateException {
    boolean isValidCoordinate = CoordinateUtil.isCoordinateValid(museum.getCoordinate());

    if (!isValidCoordinate) {
      throw new InvalidCoordinateException();
    }

    return dbConnection.saveMuseum(museum);
  }

  @Override
  public Museum getMuseum(Long id) {
    return null;
  }
}

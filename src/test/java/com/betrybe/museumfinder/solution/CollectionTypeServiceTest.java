package com.betrybe.museumfinder.solution;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.dto.CollectionTypeCount;
import com.betrybe.museumfinder.service.CollectionTypeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest
public class CollectionTypeServiceTest {
  @Autowired
  CollectionTypeService service;

  @MockBean
  MuseumFakeDatabase dbConnection;

  @Test
  @DisplayName("countByCollectionTypes - when collection types is equal one")
  void testCountByCollectionTypesAndHasOneCollectionType() {
    Mockito.when(dbConnection.countByCollectionType("math")).thenReturn(1L);

    CollectionTypeCount result = service.countByCollectionTypes("math");

    assertEquals(1, result.count());
    Mockito.verify(dbConnection, Mockito.atLeast(1))
            .countByCollectionType(eq("math"));
  }

  @Test
  @DisplayName("countByCollectionTypes - when collection types is more than one")
  void testCountByCollectionTypesAndHasMoreThanOneCollectionType() {
    Mockito.when(dbConnection.countByCollectionType("history")).thenReturn(1L);
    Mockito.when(dbConnection.countByCollectionType("math")).thenReturn(1L);

    CollectionTypeCount result = service.countByCollectionTypes("history,math");

    assertEquals(2, result.count());
    Mockito.verify(dbConnection, Mockito.atLeast(1))
            .countByCollectionType(eq("history"));
    Mockito.verify(dbConnection, Mockito.atLeast(1))
            .countByCollectionType(eq("math"));
  }
}

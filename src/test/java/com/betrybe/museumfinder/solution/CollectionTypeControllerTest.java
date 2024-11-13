package com.betrybe.museumfinder.solution;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CollectionTypeControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  MuseumFakeDatabase dbConnection;

  @Test
  @DisplayName("Returns not found status")
  void testNotFound() throws Exception {
    Mockito
        .when(dbConnection.countByCollectionType(any()))
        .thenReturn(0L);

    ResultActions result = mockMvc.perform(
        get("/collections/count/not_found")
    );

    result.andExpect(status().isNotFound());
  }

  @Test
  @DisplayName("Returns not found status")
  void happyPath() throws Exception {
    Mockito
        .when(dbConnection.countByCollectionType(any()))
        .thenReturn(2L);

    ResultActions result = mockMvc.perform(
            get("/collections/count/hist,imag")
    );

    result.andExpect(status().isOk());
    result.andExpect(jsonPath("$.collectionTypes").value(containsInAnyOrder("hist", "imag")));
    result.andExpect(jsonPath("$.count").value(4));
  }
}

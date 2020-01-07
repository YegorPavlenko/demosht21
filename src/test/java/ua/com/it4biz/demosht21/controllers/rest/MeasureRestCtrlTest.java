package ua.com.it4biz.demosht21.controllers.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.com.it4biz.demosht21.models.Measure;
import ua.com.it4biz.demosht21.repositories.MeasureRepo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class MeasureRestCtrlTest {

  @Mock
  MeasureRepo measureRepo;

  @InjectMocks
  MeasureRestCtrl measureRestCtrl;

  MockMvc mockMvc;

  List<Measure> measures;

  @BeforeEach
  void setUp() {
    measures = new ArrayList<>();
    measures.add(new Measure(new Timestamp(1571836944795L), 21.6, 45.0));
    measures.add(new Measure(new Timestamp(1571836945795L), 21.0, 45.1));
    measures.add(new Measure(new Timestamp(1571836946795L), 22.0, 45.5));
    measures.add(new Measure(new Timestamp(1571836947795L), 21.8, 45.6));
    measures.add(new Measure(new Timestamp(1571836948795L), 21.9, 44.9));

    mockMvc = MockMvcBuilders.standaloneSetup(measureRestCtrl)
//        .setControllerAdvice(new RestResponseEntityExceptionHandler())
        .build();
  }

  @Test
  void getMeasures() throws Exception {
    String startTimestamp = "1571836944795";
    String endTimestamp   = "1571836948795";
    when(measureRepo.getMeasures(new Timestamp(1571836944795L), new Timestamp(1571836948795L))).thenReturn(measures);
    mockMvc.perform(get(MeasureRestCtrl.BASE_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .param("start", startTimestamp)
        .param("end", endTimestamp))
//      .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(5)))
    ;
  }

  @Disabled
  @Test
  void addMeasures() {
  }
}
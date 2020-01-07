package ua.com.it4biz.demosht21.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class MeasureTest {

  Measure measure;

  @BeforeEach
  void setUp() {
    measure = new Measure();
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void getTimestamp() {
    measure.setTimestamp(new Timestamp(1571836945795L));
    assertEquals(new Timestamp(1571836945795L), measure.getTimestamp());
  }

  @Test
  void getTemperature() {
    measure.setTemperature(21.6);
    assertEquals(21.6, measure.getTemperature(), .0);
  }

  @Test
  void getHumidity() {
    measure.setHumidity(45.0);
    assertEquals(45.0, measure.getHumidity(), .0);
  }
}
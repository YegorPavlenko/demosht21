package ua.com.it4biz.demosht21.models;

import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

public class Measure {
  @NotEmpty
  Timestamp timestamp;
  @NotEmpty
  double temperature;
  @NotEmpty
  double humidity;

  public Measure() {
  }

  public Measure(Timestamp theTimestamp, double theTemperature, double theHumidity) {
    timestamp = theTimestamp;
    temperature = theTemperature;
    humidity = theHumidity;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp theTimestamp) {
    timestamp = theTimestamp;
  }

  public double getTemperature() {
    return temperature;
  }

  public void setTemperature(double theTemperature) {
    temperature = theTemperature;
  }

  public double getHumidity() {
    return humidity;
  }

  public void setHumidity(double theHumidity) {
    humidity = theHumidity;
  }

  @Override
  public String toString() {
    return "Measure{" +
        "timestamp=" + timestamp +
        ", temperature=" + temperature +
        ", humidity=" + humidity +
        '}';
  }
}

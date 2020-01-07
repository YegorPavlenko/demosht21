package ua.com.it4biz.demosht21.repositories;

import org.springframework.jdbc.core.RowMapper;
import ua.com.it4biz.demosht21.models.Measure;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MeasureRowMapper implements RowMapper<Measure> {
  @Override
  public Measure mapRow(ResultSet rs, int rowNum) throws SQLException {
    Measure measure = new Measure();
    measure.setTimestamp(rs.getTimestamp("time"));
    measure.setTemperature(rs.getDouble("temperature"));
    measure.setHumidity(rs.getDouble("humidity"));
    return measure;
  }
}

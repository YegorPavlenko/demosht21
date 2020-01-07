package ua.com.it4biz.demosht21.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ua.com.it4biz.demosht21.models.Measure;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class MeasureRepoImpl implements MeasureRepo {
  private final JdbcTemplate jdbcTemplate;
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  public MeasureRepoImpl(JdbcTemplate theJdbcTemplate, NamedParameterJdbcTemplate theNamedParameterJdbcTemplate) {
    jdbcTemplate = theJdbcTemplate;
    namedParameterJdbcTemplate = theNamedParameterJdbcTemplate;
  }

  @Override
  public int addMeasure(Measure theMeasure) {
    final String INSERT_MEASURE =
        "INSERT INTO conditions (time, temperature, humidity) VALUES (:timestamp, :temperature, :humidity)";

    SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(theMeasure);
    return namedParameterJdbcTemplate.update(INSERT_MEASURE, namedParameters);
  }

  @Override
  public List<Measure> getMeasures(Timestamp startTimestamp, Timestamp endTimestamp) {
    final String SELECT_MEASURES =
        "SELECT * FROM conditions WHERE time between ? AND ?";
    return jdbcTemplate.query(
        SELECT_MEASURES, new Object[] {startTimestamp, endTimestamp}, new MeasureRowMapper());
  }

  @Override
  public Measure getLast() {
    final String SELECT_LAST =
        "SELECT * FROM conditions ORDER BY time DESC LIMIT 1";
    return jdbcTemplate.queryForObject(SELECT_LAST, new MeasureRowMapper());
  }
}

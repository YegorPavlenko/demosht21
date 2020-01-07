package ua.com.it4biz.demosht21.repositories;

import ua.com.it4biz.demosht21.models.Measure;

import java.sql.Timestamp;
import java.util.List;

public interface MeasureRepo {
  public int addMeasure(Measure theMeasure);

  public List<Measure> getMeasures(Timestamp startTimestamp, Timestamp endTimestamp);

  public Measure getLast();
}

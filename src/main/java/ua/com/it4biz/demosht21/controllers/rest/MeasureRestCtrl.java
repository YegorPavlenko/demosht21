package ua.com.it4biz.demosht21.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.it4biz.demosht21.models.Measure;
import ua.com.it4biz.demosht21.repositories.MeasureRepo;

import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(MeasureRestCtrl.BASE_URL)
public class MeasureRestCtrl {

  public static final String BASE_URL = "/api/measures";

  private final MeasureRepo measureRepo;

  @Autowired
  public MeasureRestCtrl(MeasureRepo theMeasureRepo) {
    measureRepo = theMeasureRepo;
  }

  @GetMapping
  public List<Measure> getMeasures(@RequestParam("start")
//                                   @NotEmpty
                                      long startTimestamp,
                                   @RequestParam("end")
//                                   @NotEmpty
                                      long endTimestamp) {
//    return measureRepo.getMeasures(new Timestamp(1571836944794L), new Timestamp(1571836944796L));
    // TODO add parameters check
    return measureRepo.getMeasures(new Timestamp(startTimestamp), new Timestamp(endTimestamp));
  }

  @PostMapping
  public int addMeasures(@RequestBody Measure theMeasure) {
//    System.out.println(theMeasure);
    return measureRepo.addMeasure(theMeasure);
  }

}

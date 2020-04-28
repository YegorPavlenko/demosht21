package ua.com.it4biz.demosht21.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.it4biz.demosht21.models.Measure;
import ua.com.it4biz.demosht21.repositories.MeasureRepo;
import ua.com.it4biz.demosht21.services.MeasureSrv;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(MeasureRestCtrl.BASE_URL)
public class MeasureRestCtrl {

  public static final String BASE_URL = "/api/measures";

  private final MeasureRepo measureRepo;
  private final MeasureSrv measureSrv;

  @Autowired
  public MeasureRestCtrl(MeasureRepo theMeasureRepo, MeasureSrv theMeasureSrv/*, SseSrv theSseSrv*/) {
    measureRepo = theMeasureRepo;
    measureSrv = theMeasureSrv;
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
  public Mono<Void> addMeasures(@RequestBody Measure measure) {
//    System.out.println(theMeasure);
    measureSrv.addMeasure(measure);
    return Mono.empty();
  }

  @GetMapping(path = "/sse", produces =  MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<ServerSentEvent<Measure>> measureStream() {
    return measureSrv.getMeasureStream()
      .map(measure ->
        ServerSentEvent
          .builder(measure)
          .event("measure")
          .build());
  }
}

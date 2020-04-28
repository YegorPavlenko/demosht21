package ua.com.it4biz.demosht21.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.FluxProcessor;
import reactor.core.publisher.FluxSink;
import ua.com.it4biz.demosht21.models.Measure;
import ua.com.it4biz.demosht21.repositories.MeasureRepo;

@Service
public class MeasureSrv {

  final FluxProcessor<Measure, Measure> processor;
  final FluxSink<Measure> sink;

  private final MeasureRepo measureRepo;

  @Autowired
  public MeasureSrv(MeasureRepo theMeasureRepo) {
    measureRepo = theMeasureRepo;
    processor = DirectProcessor.<Measure>create().serialize();
    sink = processor.sink();
  }

  public FluxProcessor<Measure, Measure> getMeasureStream() {
    return processor;
  }

  public void addMeasure(Measure measure) {
    sink.next(measure);
    measureRepo.addMeasure(measure);
  }
}

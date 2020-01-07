package ua.com.it4biz.demosht21.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.it4biz.demosht21.repositories.MeasureRepo;

@Controller
@RequestMapping("/")
public class MeasureCtrl {

  private final MeasureRepo measureRepo;

  @Autowired
  public MeasureCtrl(MeasureRepo theMeasureRepo) {
    measureRepo = theMeasureRepo;
  }


  @GetMapping
  public String showHome(Model theModel) {
    theModel.addAttribute("lastConditions", measureRepo.getLast());
    theModel.addAttribute("theDate", java.time.LocalDateTime.now());
    return "index";
  }
}

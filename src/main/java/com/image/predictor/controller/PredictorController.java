package com.image.predictor.controller;

import clarifai2.exception.ClarifaiException;
import com.image.predictor.model.Prediction;
import com.image.predictor.service.PredictionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class PredictorController {

    Logger logger = LoggerFactory.getLogger(PredictorController.class);

    @Autowired
    private PredictionService predictionService;

    @GetMapping("/")
    public String hello(Model mav){
        logger.info("initial method accessed");
        return "welcomePage";
    }

    @GetMapping ("/predictions")
    public String predictByPicURL(Model mav, @RequestParam String pictureURL) throws Exception {

        logger.info("searching for predictions. picture url: " + pictureURL);

        predictionService.addNewImage(pictureURL); // adding to DB
        mav.addAttribute("pictureURL", pictureURL);

        List<Prediction> predictionList = predictionService.getPredictionsLoadedImg(pictureURL);
        mav.addAttribute("predictionList", predictionList);
        mav.addAttribute("imgLoadDate", predictionList.get(0).getImgId().getImgDate());
        mav.addAttribute("predictionListSize", predictionList.size());

        return "predictions";
    }

    @ExceptionHandler(value = ClarifaiException.class)
    public String handleClarifaiException(Exception ex){
        logger.error("ClarifaiException. Clarifai service can't analyze the image by link: " + ex);
        return "ClarifaiException";
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    public String handleNoSuchElementException(Exception ex){
        logger.error("NoSuchElementException. Image not found: " + ex);
        return "NoSuchElementExceptionPage";
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public String missingServletRequestParameterException(Exception ex){
        logger.error("MissingServletRequestParameterException: " + ex);
        return "MissingServletRequestParameterException";
    }

    @ExceptionHandler(value = Exception.class)
    public String unknownException(Exception ex){
        logger.error("Unknown exception: " + ex);
        return "UnknownException";
    }


}

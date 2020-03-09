package com.image.predictor.service;

import clarifai2.dto.prediction.Concept;
import com.image.predictor.model.Image;
import com.image.predictor.model.Prediction;
import com.image.predictor.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PredictionService {
    Logger logger = LoggerFactory.getLogger(PredictionService.class);

    @Autowired
    ImageRepository imageRepository;

    public void addNewImage(String imgUrl) {

        Image image = new Image();
        List<Prediction> predictionList = new ArrayList<>();

        for (Concept concept : (new ClarifyPredictionClient()).getConceptsByURL(imgUrl)) {
            Prediction prediction = new Prediction();
            prediction.setPredictionName(concept.name());
            prediction.setPredictionValue(concept.value());
            prediction.setImgId(image);
            predictionList.add(prediction);

            logger.trace("found prediction with name: " + concept.name()
                                               + " and value: " + concept.value());
        }

        if (!predictionList.isEmpty()) {
            image.setImgUrl(imgUrl);
            image.setPredictionCollection(predictionList);
            imageRepository.save(image);

            logger.info("image and predictions are added successfully");
        }
    }

    public List<Prediction> getPredictionsLoadedImg(String imgUrl) throws Exception {
        List<Prediction> predictionList = new ArrayList<>();
        for(Image img : imageRepository.findAll()){
            if (img.getImgUrl().equals(imgUrl)){
                predictionList = img.getPredictionCollection();
                logger.trace("image found in DB");
                break;
            }
        }
        return translateNames(predictionList);
    }

    public List<Prediction> translateNames (List<Prediction> predictionList) throws Exception {

        for (int i = 0; i < predictionList.size(); i++){
            predictionList.get(i)
                    .setPredictionName(
                    Translator.translate(
                    predictionList
                    .get(i).getPredictionName())
                     );
        }
        logger.info("translation is finished");
        return predictionList;
    }






}

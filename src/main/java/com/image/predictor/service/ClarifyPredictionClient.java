package com.image.predictor.service;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.prediction.Concept;
import com.image.predictor.controller.PredictorController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ClarifyPredictionClient {

    private RestTemplate restTemplate = new RestTemplate();
    Logger logger = LoggerFactory.getLogger(ClarifyPredictionClient.class);

    ClarifaiClient client = new ClarifaiBuilder("b79f1fc9a7f8420c9f4eb0473a2c3b31") // API key
            .buildSync();

    public List<Concept> getConceptsByURL(String imgUrl) {

        logger.trace("trying to get picture concepts");

        return client.getDefaultModels().generalModel()
                .predict()
                .withInputs(ClarifaiInput.forImage(imgUrl))
                .executeSync()
                .get().get(0).data(); //
    }
}

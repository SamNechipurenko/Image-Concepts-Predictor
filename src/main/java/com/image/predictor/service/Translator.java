package com.image.predictor.service;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Translator {

    static Logger logger = LoggerFactory.getLogger(Translator.class);

   public static String translate(String word) throws Exception {
        String langFrom = "en";
        String langTo = "ru";

        String url = "https://translate.googleapis.com/translate_a/single?"+
                "client=gtx&"+
                "sl=" + langFrom +
                "&tl=" + langTo +
                "&dt=t&q=" + URLEncoder.encode(word, "UTF-8");

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        logger.info("connection with google established ");

        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        BufferedReader in = new BufferedReader(
                                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) response.append(inputLine);

       logger.info("JSON response from google service received");

        if (response.equals(null) || response.equals(""))
                                                return word + " (not translated from english)";
        in.close();
        logger.info("connection with google successfully closed");
        return parseResult(response.toString());
    }

    public static String parseResult(String inputJson) throws Exception {
        /*
         * inputJson for word 'hello' translated to language Hindi from English-
         * [[["नमस्ते","hello",,,1]],,"en"]
         * We have to get 'नमस्ते ' from this json.
         */

        JSONArray jsonArray = new JSONArray(inputJson);
        JSONArray jsonArray2 = (JSONArray) jsonArray.get(0);
        JSONArray jsonArray3 = (JSONArray) jsonArray2.get(0);
        logger.info("input JSON object parsed");
        return jsonArray3.get(0).toString();
    }

}

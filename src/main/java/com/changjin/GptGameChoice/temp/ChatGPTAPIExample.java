package com.changjin.GptGameChoice.temp;

import com.changjin.GptGameChoice.config.Config;
import com.changjin.GptGameChoice.dto.GameDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ChatGPTAPIExample {

    public static List<GameDto> chatGPT(String prompt) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = Config.apiKey;
        String basicPrompt = Config.basicPrompt;
        String model = "gpt-3.5-turbo";

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");

            basicPrompt = "{\"role\": \"system\", \"content\": \"" + basicPrompt + "\"}, ";
            prompt = "{\"role\": \"user\", \"content\": \"" + prompt + "\"}";

            String body = "{\"model\": \"" + model + "\", \"messages\": [" + basicPrompt + prompt + "]}";

            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Response from ChatGPT
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            StringBuffer response = new StringBuffer();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            // calls the method to extract the message.
            return extractMessageFromJSONResponse(response.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<GameDto> extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content")+ 11;
        int end = response.indexOf("finish_reason", start)-11;
        String gameName;
        String gameReview;
        int gamePrice;
        List<String> gameTag;
        String gameDescription;

        String responseStr = response.substring(start, end);
        responseStr = responseStr.replace("\\n", "");
        responseStr = responseStr.replace("\\", "");

        List<GameDto> gameDtoList = new ArrayList<>();


        try{
            JSONObject jsonObject = new JSONObject(responseStr);
            JSONArray jsonArray = jsonObject.getJSONArray("Games");
            JSONArray tagArray;

            System.out.println("jsonArray.length() = " + jsonArray.length());

            for(int i = 0; i < 5; i++){
                jsonObject= jsonArray.getJSONObject(i);
                gameName = jsonObject.getString("Name");
                gameReview = jsonObject.getString("All Reviews");
                gamePrice = (int)jsonObject.get("Price");
                gameDescription = jsonObject.getString("Reason For Selection");
                tagArray = jsonObject.getJSONArray("Tags");
                gameTag = new ArrayList<>();
                for(int j = 0; j < tagArray.length(); j++){
                    gameTag.add(tagArray.getString(j));
                }
                gameDtoList.add(new GameDto(gameName, gameReview, gamePrice, gameTag, gameDescription));
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return gameDtoList;
    }

    public static void main(String[] args) {
        List<String> tags = new ArrayList<>();
        List<String> games1 = new ArrayList<>();
        List<String> games2 = new ArrayList<>();

        tags.add("RPG");
        games1.add("Necesse");
        games2.add("Dark Souls III");

        String p2 = "Tag: " + tags.toString() + ", Similar game: " + games1.toString() + ", Excluded game: " + games2.toString();

        List<GameDto> gameDtoList =  chatGPT(p2);

        for(int i = 0; i < 5; i++){
            System.out.println("gameDtoList.get(i).getGameName() = " + gameDtoList.get(i).getGameName());
            System.out.println("gameDtoList = " + gameDtoList.get(i).getGameReview());
            System.out.println("gameDtoList = " + gameDtoList.get(i).getGameDescription());
            System.out.println("gameDtoList = " + gameDtoList.get(i).getGameTag().toString());
            System.out.println("gameDtoList = " + gameDtoList.get(i).getGamePrice());
            System.out.println("");
        }
    }
}
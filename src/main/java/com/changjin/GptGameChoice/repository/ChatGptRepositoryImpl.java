package com.changjin.GptGameChoice.repository;

import com.changjin.GptGameChoice.config.ChatGptConfig;
import com.changjin.GptGameChoice.dto.GameDto;
import com.changjin.GptGameChoice.dto.SearchDto;
import com.changjin.GptGameChoice.dto.TagDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ChatGptRepositoryImpl implements ChatGptRepository{
    @Override
    public GameDto SearchGame(SearchDto searchDto) {
        String prompt;

        prompt = makePrompt(searchDto);

        return chatGPT(prompt);
    }

    private String makePrompt(SearchDto searchDto){
        List<TagDto> tagDtoList = searchDto.getTagList();
        List<String> tags = new ArrayList<>();
        for(TagDto tagDto : tagDtoList){
            tags.add(tagDto.getTagName());
        }
        List<String> similarGames = searchDto.getSimilarGames();
        List<String> excludeGames = searchDto.getExcludedGames();
        String tagPrompt;
        String similarGamesPrompt;
        String excludeGamesPrompt;
        if(tags.isEmpty()){
            tagPrompt = "none";
        }
        else{
            tagPrompt = tags.toString();
        }
        if(similarGames == null || similarGames.isEmpty()){
            similarGamesPrompt = "none";
        }
        else{
            similarGamesPrompt = similarGames.toString();
        }
        if(excludeGames == null || excludeGames.isEmpty()){
            excludeGamesPrompt = "none";
        }
        else{
            excludeGamesPrompt = excludeGames.toString();
        }
        return "Tag: " + tagPrompt + ", Similar game: " + similarGamesPrompt + ", Excluded game: " + excludeGamesPrompt;
    }

    private GameDto chatGPT(String prompt) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = ChatGptConfig.apiKey;
        String basicPrompt = ChatGptConfig.basicPrompt;
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

    private GameDto extractMessageFromJSONResponse(String response) {
        //
        System.out.println("ChatGpt's response = " + response);
        GameDto gameDto;
        int start = response.indexOf("content")+ 11;
        int end = response.indexOf("finish_reason", start)-11;
        String gameName;
        String gameReview;
        int gamePrice;
        List<String> gameTag;
        String gameDescription;
        String steamLink;
        String responseStr = response.substring(start, end);
        responseStr = responseStr.replace("\\n", "");
        responseStr = responseStr.replace("\\", "");

        List<GameDto> gameDtoList = new ArrayList<>();


        try{
            JSONObject jsonObject = new JSONObject(responseStr);
            JSONArray tagArray;

            gameName = jsonObject.getString("Name");
            gameReview = jsonObject.getString("All Reviews");
            gamePrice = (int)jsonObject.get("Price");
            gameDescription = jsonObject.getString("Reason For Selection");
            tagArray = jsonObject.getJSONArray("Tags");
            steamLink = jsonObject.getString("Steam link");
            gameTag = new ArrayList<>();
            for(int j = 0; j < tagArray.length(); j++){
                gameTag.add(tagArray.getString(j));
            }
            return new GameDto(gameName, gameReview, gamePrice, gameTag, gameDescription, steamLink);
        }
        catch (Exception e){
            System.out.println("ChatGptRepositoryImpl :"+e.getMessage());
        }
        return null;
    }


}
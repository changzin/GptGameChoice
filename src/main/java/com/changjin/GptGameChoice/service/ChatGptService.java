package com.changjin.GptGameChoice.service;

import com.changjin.GptGameChoice.config.Config;
import com.changjin.GptGameChoice.dto.GameDto;
import com.changjin.GptGameChoice.dto.SearchDto;
import com.changjin.GptGameChoice.repository.ChatGptRepository;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ChatGptService {

    @Autowired
    private ChatGptRepository chatGptRepository;

    public List<GameDto> getGameChoice(SearchDto searchDto){
        return chatGptRepository.SearchGame(searchDto);
    }

}

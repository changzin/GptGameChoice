package com.changjin.GptGameChoice.service;

import com.changjin.GptGameChoice.dto.GameDto;
import com.changjin.GptGameChoice.dto.SearchDto;
import com.changjin.GptGameChoice.repository.ChatGptRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChatGptService {

    @Autowired
    private ChatGptRepository chatGptRepository;

    public GameDto getGameChoice(SearchDto searchDto){
        return chatGptRepository.SearchGame(searchDto);
    }

}

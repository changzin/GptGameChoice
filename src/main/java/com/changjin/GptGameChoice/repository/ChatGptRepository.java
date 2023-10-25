package com.changjin.GptGameChoice.repository;

import com.changjin.GptGameChoice.dto.GameDto;
import com.changjin.GptGameChoice.dto.SearchDto;

import java.util.List;

public interface ChatGptRepository {
    List<GameDto> SearchGame(SearchDto searchDto);
}

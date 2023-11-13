package com.changjin.GptGameChoice.controller;

import com.changjin.GptGameChoice.dto.GameDto;
import com.changjin.GptGameChoice.dto.SearchDto;
import com.changjin.GptGameChoice.dto.TagDto;
import com.changjin.GptGameChoice.service.ChatGptService;
import com.changjin.GptGameChoice.service.TagService;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {

    @Autowired
    private TagService tagService;
    @Autowired
    private ChatGptService chatGptService;

    
    // 기본 페이지로드
    @GetMapping("")
    public String home(Model model){
        model.addAttribute("tagDtoList", tagService.getTagList());
        return "home";
    }
    @GetMapping("/search")
    public String search(Model model){
        model.addAttribute("tagDtoList", tagService.getTagList());
        return "search";
    }

    // SearchDto 게임 검색, GameDtoList 반환
    @PostMapping("/search/find")
    public ResponseEntity searchWithObject(@RequestBody SearchDto searchDto){
//        System.out.println("searchDto = " + searchDto);
        GameDto gameDto = chatGptService.getGameChoice(searchDto);
        System.out.println("gameDtoList = " + gameDto);
//        List<String> tags = new ArrayList<>();
//        List<GameDto> gameDtoList = new ArrayList<>();
//        tags.add("fun");
//        GameDto gameDto1 = new GameDto("name1", "Good1", 1000, tags, "funny1");
//        GameDto gameDto2 = new GameDto("name2", "Good2", 2000, tags, "funny2");
//        GameDto gameDto3 = new GameDto("name3", "Good3", 3000, tags, "funny3");
//        GameDto gameDto4 = new GameDto("name4", "Good4", 4000, tags, "funny4");
//        GameDto gameDto5 = new GameDto("name5", "Good5", 5000, tags, "funny5");
//        gameDtoList.add(gameDto1);
//        gameDtoList.add(gameDto2);
//        gameDtoList.add(gameDto3);
//        gameDtoList.add(gameDto4);
//        gameDtoList.add(gameDto5);

        return ResponseEntity.status(300).body(gameDto);
    }
}

package com.changjin.GptGameChoice.controller;

import com.changjin.GptGameChoice.dto.TagDto;
import com.changjin.GptGameChoice.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping(path = "/tag")
    public ResponseEntity getAllTag(){
        List<TagDto> tagDtoList = tagService.getTagList();
        return ResponseEntity.ok().body(tagDtoList);
    }
}

package com.changjin.GptGameChoice.service;

import com.changjin.GptGameChoice.domain.Tag;
import com.changjin.GptGameChoice.dto.TagDto;
import com.changjin.GptGameChoice.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    // 모든 태그들을 리스트 형태로 반환하는 함수, 초기화해서 검색 시작할 때만 사용되지 않을까..
    public List<TagDto> getTagList(){
        return tagRepository.findAllTags();
    }
}

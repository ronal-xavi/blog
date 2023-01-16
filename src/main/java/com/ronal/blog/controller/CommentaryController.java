package com.ronal.blog.controller;

import com.ronal.blog.dto.CommentaryDTO;
import com.ronal.blog.service.CommentaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/commentary")
public class CommentaryController {

    private final CommentaryService commentaryService;


    @PostMapping("/saveCommentary")
    public CommentaryDTO save (@RequestBody CommentaryDTO commentaryDTO){
        return  commentaryService.saveCommentary(commentaryDTO);
    }
}

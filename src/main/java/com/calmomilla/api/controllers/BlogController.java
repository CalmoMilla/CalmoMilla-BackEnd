package com.calmomilla.api.controllers;

import com.calmomilla.api.dto.output.blog.BuscarPostagemOutput;
import com.calmomilla.domain.model.Blog;
import com.calmomilla.domain.repository.BlogRepository;

import com.calmomilla.domain.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/blog")
public class BlogController {

    private final BlogRepository blogRepository;
    private final BlogService blogService;

    @GetMapping
    public List<BuscarPostagemOutput> buscarPostagem(){
        return  blogService.buscarTudo();
    }


}

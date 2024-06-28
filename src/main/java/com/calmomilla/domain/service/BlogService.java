package com.calmomilla.domain.service;

import com.calmomilla.api.dto.output.blog.BuscarPostagemOutput;
import com.calmomilla.domain.model.Blog;
import com.calmomilla.domain.repository.BlogRepository;
import com.calmomilla.domain.utils.ModelMapperUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final ModelMapperUtils mapperUtils;
    public List<BuscarPostagemOutput> buscarTudo(){

        List<Blog> blog = blogRepository.findAllByOrderByTituloPostagemAsc();

        return mapperUtils.mapList(blog,BuscarPostagemOutput.class);

    }

}

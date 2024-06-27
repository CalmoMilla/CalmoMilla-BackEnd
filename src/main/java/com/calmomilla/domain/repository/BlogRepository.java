package com.calmomilla.domain.repository;


import com.calmomilla.domain.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, String> {

    Blog findBlogByTituloPostagem(String titulo);

}

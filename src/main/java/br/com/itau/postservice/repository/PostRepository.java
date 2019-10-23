package br.com.itau.postservice.repository;

import br.com.itau.postservice.model.Comment;
import br.com.itau.postservice.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}

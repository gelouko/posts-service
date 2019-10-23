package br.com.itau.postservice.repository;

import br.com.itau.postservice.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comment, Long> {

}

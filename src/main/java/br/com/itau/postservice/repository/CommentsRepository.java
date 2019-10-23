package br.com.itau.postservice.repository;

import br.com.itau.postservice.model.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentsRepository extends PagingAndSortingRepository<Comment, Long> {

}

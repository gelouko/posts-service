package br.com.itau.postservice.repository;

import br.com.itau.postservice.model.Post;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostsRepository extends JpaRepository<Post, Long> {

    @Query("select p, count(c.id) as comments from Post p inner join Comment c on p.id = c"
            + ".post group by p.id order by comments desc")
    List<Post> findTopPosts(PageRequest pageRequest);
}

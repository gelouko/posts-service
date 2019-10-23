package br.com.itau.postservice.repository;

import br.com.itau.postservice.model.SaveCommentsExecution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaveCommentsExecutionRepository extends JpaRepository<SaveCommentsExecution,
        Long> {

}

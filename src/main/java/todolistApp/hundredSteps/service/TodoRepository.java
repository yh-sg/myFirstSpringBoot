package todolistApp.hundredSteps.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import todolistApp.hundredSteps.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer>{
	List<Todo> findByUser(String User);
}

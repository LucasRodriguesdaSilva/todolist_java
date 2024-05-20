package com.todolist.todo_list.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todolist.todo_list.models.Tarefas;

@Repository
public interface TarefasRepository extends JpaRepository<Tarefas, Long> {
    
    List<Tarefas> findByUsuario_Id(Long id);

}

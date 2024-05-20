package com.todolist.todo_list.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todolist.todo_list.models.Usuario;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Long> {
}

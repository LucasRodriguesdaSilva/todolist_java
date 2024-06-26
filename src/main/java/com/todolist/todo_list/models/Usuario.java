package com.todolist.todo_list.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = Usuario.TABLE_NAME)
public class Usuario {
    public interface CreateUsuarios {
    }

    public interface UpdateUsuarios {
    }

    public static final String TABLE_NAME = "usuario";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "username", length = 100, unique = true, nullable = false)
    @NotBlank(groups = CreateUsuarios.class)
    @Size(groups = CreateUsuarios.class, min = 2, max = 100)
    private String username;

    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(name = "password", length = 60, nullable = false)
    @NotBlank(groups = { CreateUsuarios.class, UpdateUsuarios.class })
    @Size(groups = { CreateUsuarios.class, UpdateUsuarios.class }, min = 8, max = 60)
    private String password;

    @OneToMany(mappedBy = "usuario")
    private List<Tarefas> tarefas = new ArrayList<Tarefas>();

    public List<Tarefas> getTarefa() {
        return this.tarefas;
    }

    public void setTarefa(List<Tarefas> tarefa) {
        this.tarefas = tarefa;
    }

    

    public Usuario() {
    }

    public Usuario(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != this)
            return false;

        if (obj == null)
            return false;

        if (!(obj instanceof Usuario))
            return false;

        Usuario usuario = (Usuario) obj;

        if (this.id == null) {
            if (usuario.id != null)
                return false;
            else if (!this.id.equals(usuario.id))
                return false;
        }

        return Objects.equals(this.id, usuario.id) 
                && Objects.equals(this.username, usuario.username)
                && Objects.equals(this.password, usuario.password);

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }

}

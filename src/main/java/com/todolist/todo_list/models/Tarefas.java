package com.todolist.todo_list.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = Tarefas.TABLE_NAME)
public class Tarefas {
    public static final String TABLE_NAME = "tarefas";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private Usuario usuario;

    @Column(name = "descricao", length = 255, nullable = false)
    @NotBlank
    @Size(min = 1, max = 255)
    private String descricao;

    public Tarefas() {
    }

    public Tarefas(Long id, Usuario usuario, String descricao) {
        this.id = id;
        this.usuario = usuario;
        this.descricao = descricao;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Tarefas id(Long id) {
        setId(id);
        return this;
    }

    public Tarefas usuario(Usuario usuario) {
        setUsuario(usuario);
        return this;
    }

    public Tarefas descricao(String descricao) {
        setDescricao(descricao);
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != this)
            return false;

        if (obj == null)
            return false;

        if (!(obj instanceof Tarefas))
            return false;

        Tarefas tarefa = (Tarefas) obj;

        if (this.id == null) {
            if (tarefa.id != null)
                return false;
            else if (!this.id.equals(tarefa.id))
                return false;
        }

        return Objects.equals(this.id, tarefa.id) 
                && Objects.equals(this.descricao, tarefa.descricao);

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;

    }

}

package com.todolist.todo_list.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todolist.todo_list.models.Usuario;
import com.todolist.todo_list.repositories.TarefasRepository;
import com.todolist.todo_list.repositories.UsuariosRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private TarefasRepository tarefasRepository;

    public Usuario findById(Long id) {
        Optional<Usuario> usuario = this.usuariosRepository.findById(id);

        return usuario.orElseThrow(() -> new RuntimeException(
                "Usuário não encontrado! id: " + id + ", Tipo: " + Usuario.class.getName()));
    }

    @Transactional
    public Usuario create(Usuario usuario) {
        usuario.setId(null);
        usuario = this.usuariosRepository.save(usuario);
        this.tarefasRepository.saveAll(usuario.getTarefa());

        return usuario;
    }

    @Transactional
    public Usuario update(Usuario usuario) {
        Usuario newUsuario = findById(usuario.getId());
        newUsuario.setPassword(usuario.getPassword());
        return this.usuariosRepository.save(newUsuario);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.usuariosRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluír pois há entidades relacionadas!");
        }
    }

}

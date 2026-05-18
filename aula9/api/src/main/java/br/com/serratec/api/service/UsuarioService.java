package br.com.serratec.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.serratec.api.exceptions.UsuarioException;
// Use a standard runtime exception instead of a custom checked exception
import br.com.serratec.api.model.Usuario;
import br.com.serratec.api.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;
    
    @Autowired
    private BCryptPasswordEncoder criptografar;

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public Usuario inserir(Usuario usuario){
        Usuario usuarioBanco = repository.findByEmail(usuario.getEmail());
        if (usuarioBanco != null) {
            throw new UsuarioException("Email já cadastrado");    
        }
        usuario.setSenha(criptografar.encode(usuario.getSenha()));
        return repository.save(usuario);
    }
}
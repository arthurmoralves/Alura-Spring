package br.com.alura.crud.config.validation.security;

import br.com.alura.crud.modelo.Usuario;
import br.com.alura.crud.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        var usuario = usuarioRepository.findByEmail(s);
        if(usuario.isPresent()){
            return usuario.get();
        } else {
            throw new UsernameNotFoundException("Dados inválidos");
        }
    }
}

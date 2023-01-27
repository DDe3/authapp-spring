package com.distribuida.authapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.distribuida.authapp.repository.IUsuarioRepository;
import com.distribuida.authapp.service.model.Usuario;
import com.distribuida.authapp.to.UsuarioTo;


@Service
public class UsuarioServiceImpl implements IUsuarioService {


    @Autowired
    private IUsuarioRepository usuarioRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String RegisterUsuario(UsuarioTo usuario) {
        String response = usuarioRepo.InsertUsuario(UsuarioFromUsuarioTo(usuario));
        return response;
    }

    @Override
    public String AuthenticateUsuario(UsuarioTo usuario) {
        Usuario toAuthenticate = usuarioRepo.SearchUsuarioByUserName(usuario.username);
        if (toAuthenticate == null) return "Usuario no existe";
        else {
            if (passwordEncoder.matches(usuario.password, toAuthenticate.getPassword())) return "Autenticado";
            else {
                return "Nombre de usuario o password incorrectos";
            }
        }
    }

    @Override
    public String UpdatePasswordFromUsuario(UsuarioTo usuario) {
        Usuario toUpdate = usuarioRepo.SearchUsuarioByUserName(usuario.username);
        if (toUpdate == null) return "El usuario " + usuario.username + " no existe";
        else {
            if (passwordEncoder.matches(usuario.password, toUpdate.getPassword())) return "El password debe ser diferente al actual";
            else {
                toUpdate.setPassword(passwordEncoder.encode(usuario.password));
                return usuarioRepo.UpdateUsuario(toUpdate);
            }
        }
    }

    private Usuario UsuarioFromUsuarioTo(UsuarioTo user) {
        Usuario usuario = new Usuario();
        usuario.setusername(user.username);
        usuario.setPassword(passwordEncoder.encode(user.password));
        return usuario;
    }

    
}

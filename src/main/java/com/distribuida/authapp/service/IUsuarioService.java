package com.distribuida.authapp.service;

import java.util.List;

import com.distribuida.authapp.to.UsuarioTo;

public interface IUsuarioService {
    
    String registerUsuario(UsuarioTo usuario);
    String authenticateUsuario(UsuarioTo usuario);
    String updatePasswordFromUsuario(UsuarioTo usuario);
    List<UsuarioTo> getAllUsuarios();
    String deleteUsuario(String username);
}

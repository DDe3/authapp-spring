package com.distribuida.authapp.service;

import com.distribuida.authapp.to.UsuarioTo;

public interface IUsuarioService {
    
    String RegisterUsuario(UsuarioTo usuario);
    String AuthenticateUsuario(UsuarioTo usuario);
    String UpdatePasswordFromUsuario(UsuarioTo usuario);
}

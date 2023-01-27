package com.distribuida.authapp.repository;

import com.distribuida.authapp.repository.model.Usuario;

public interface IUsuarioRepository {

    String InsertUsuario(Usuario user);
    String UpdateUsuario(Usuario user);
    Usuario SearchUsuarioByUserName(String userName);

}

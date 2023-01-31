package com.distribuida.authapp.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.distribuida.authapp.repository.model.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Transactional
@Repository
public class UsuarioRepositoryImpl implements IUsuarioRepository {
    
    @Autowired
	private EntityManager em;

    @Override
    public String insertUsuario(Usuario user) {
        Usuario toRegister = searchUsuarioByUserName(user.getusername());
        if (toRegister != null) return "Usuario ya registrado"; 
        else 
        {
            em.persist(user);
            return "Usuario registrado con exito!";
        }
    }

    @Override
    public String updateUsuario(Usuario user) {
        em.merge(user);
        return "Password Actualizada";
    }


    @Override
    public Usuario searchUsuarioByUserName(String userName) {
        try {
            TypedQuery<Usuario> mq = em.createQuery("SELECT c FROM Usuario c WHERE c.username=:userName",Usuario.class);
		    mq.setParameter("userName", userName);
            return mq.getSingleResult();
        } catch (NoResultException no_result) {
            return null;
        }
		
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        TypedQuery<Usuario> tq = em.createQuery("SELECT c FROM Usuario c",Usuario.class);
		return tq.getResultList();
    }

    @Override
    public String deleteUsuario(Usuario user) {
        em.remove(user);
        return "Usuario eliminado";
    }

    
}

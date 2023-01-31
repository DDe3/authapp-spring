package com.distribuida.authapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.distribuida.authapp.service.IUsuarioService;
import com.distribuida.authapp.to.UsuarioTo;
import com.google.gson.Gson;




@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/apidistribuida/v1/clientes")
public class UsuarioRestController {
    
    private static final Gson gson = new Gson();
    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerUsuario(@RequestBody UsuarioTo usuario) {
        return ResponseEntity.ok(gson.toJson(usuarioService.registerUsuario(usuario)));
    }

    @PostMapping(path = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> authenticateUsuario(@RequestBody UsuarioTo usuario) {
        return ResponseEntity.ok(gson.toJson(usuarioService.authenticateUsuario(usuario)));
    }

    @PutMapping(path = "/update-password", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updatePassword(@RequestBody UsuarioTo usuario) {
        return ResponseEntity.ok(gson.toJson(usuarioService.updatePasswordFromUsuario(usuario)));
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsuarioTo>> getAllUsuarios() {
        return ResponseEntity.ok(usuarioService.getAllUsuarios());
    }

    @PutMapping(path = "/{username}/delete")
    public ResponseEntity<String> deleteUsuario(@PathVariable("username") String username) {
        return ResponseEntity.ok(gson.toJson(usuarioService.deleteUsuario(username)));
    }
}

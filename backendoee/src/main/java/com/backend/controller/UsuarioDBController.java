package com.backend.controller;

import com.backend.modelos.Usuario;
import com.backend.services.api.UsuarioServiceApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class UsuarioDBController{
    private final String USER_SESSIONDATA_ID = "USER_SESSIONDATA_ID";
    private final String IS_LOCAL_ID = "IS_LOCAL_ID";

    final SessionController sessionController = new SessionController();
    final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ResponseEntity<Object> getAll(UsuarioServiceApi usuarioServiceApi){
        List<Usuario> obj = usuarioServiceApi.getAll();
        if (obj.isEmpty())
            return new ResponseEntity<Object>("BBDD Vacia",
                                        HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Object>(obj, HttpStatus.OK);
    }


    public ResponseEntity<Object> getById(String id, UsuarioServiceApi usuarioServiceApi){
        Usuario obj =  usuarioServiceApi.get(id);
        if (obj == null)
            return new ResponseEntity<Object>("Usuario no encontrado",
                    HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Object>(obj, HttpStatus.OK);
    }

    // Lo guarda y puede actualizarlo
    public ResponseEntity<Object> save(Usuario usuario, UsuarioServiceApi usuarioServiceApi){
        if (usuarioServiceApi.get(usuario.getId()) != null)
            return new ResponseEntity<Object>("Ya exite",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        Usuario obj = usuarioServiceApi.save(usuario);
        if (obj == null)
            return new ResponseEntity<Object>("Error al guardar",
                                            HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<Object>(obj, HttpStatus.OK);
    }

    public ResponseEntity<Object> delte(String id, UsuarioServiceApi usuarioServiceApi){
        if (usuarioServiceApi.get(id) == null)
            return  new ResponseEntity<Object>("NO existe el usuario",
                                        HttpStatus.BAD_REQUEST);

        usuarioServiceApi.delete(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    public ResponseEntity<Object> login(Usuario usuario, HttpSession session, UsuarioServiceApi usuarioServiceApi){
        Usuario obj = usuarioServiceApi.get(usuario.getId());
        if (obj == null)
            return new ResponseEntity<Object>("No existe el usuario",
                    HttpStatus.INTERNAL_SERVER_ERROR);

        if (passwordEncoder.matches(usuario.getPassword(),obj.getPassword())) {
            sessionController.saveLoggedUser(session,obj);
            obj.setPassword("");
            return new ResponseEntity<Object>(obj, HttpStatus.OK);
        }else
            return new ResponseEntity<Object>("Error de contraseña",
                    HttpStatus.BAD_REQUEST);
    }
}

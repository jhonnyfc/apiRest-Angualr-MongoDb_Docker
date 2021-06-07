package com.backend.controller;

import com.backend.modelos.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.List;

public class UsuarioLocalController {
    private final String USER_SESSIONDATA_ID = "USER_SESSIONDATA_ID";
    private final String IS_LOCAL_ID = "IS_LOCAL_ID";

    final SessionController sessionController = new SessionController();
    final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ResponseEntity<Object> getAll(HttpSession session) {
        if (sessionController.isEmpty(session)){
            return new ResponseEntity<Object>("No hay usuarios en la lista de Session",
                    HttpStatus.BAD_REQUEST);
        }else {
            List<Usuario> obj = sessionController.getALl(session);
            return new ResponseEntity<Object>(obj, HttpStatus.OK);
        }
    }


    public ResponseEntity<Object> getById(String id,HttpSession session) {
        if (sessionController.isEmpty(session)){
            return new ResponseEntity<Object>("No hay usuarios en la lista de Session",
                    HttpStatus.BAD_REQUEST);
        }else {
            List<Usuario> obj = sessionController.getALl(session);

            for (Usuario elem : obj){
                if (elem.getId().equals(id))
                    return new ResponseEntity<Object>(elem ,
                                        HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<Object>("No existe el usuario",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> save(Usuario usuario,HttpSession session) {
        if (!sessionController.isEmpty(session)){
            List<Usuario> obj = sessionController.getALl(session);

            for (Usuario elem : obj){
                if (elem.getId().equals(usuario.getId()))
                    return new ResponseEntity<Object>("El usuario ya existe",
                            HttpStatus.BAD_REQUEST);
            }
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        sessionController.saveUser(session, usuario);
        return new ResponseEntity<Object>(usuario, HttpStatus.OK);
    }

    public ResponseEntity<Object> delte(String id,HttpSession session) {
        if (sessionController.isEmpty(session)){
            return new ResponseEntity<Object>("No hay usuarios en la lista de Session",
                    HttpStatus.BAD_REQUEST);
        }else {
            List<Usuario> obj = sessionController.getALl(session);

            int i = 0;
            int j = -1;
            for (Usuario elem : obj){
                if (elem.getId().equals(id))
                    j = i;
                ++i;
            }

            if (j > -1) {
                obj.remove(j);
                sessionController.saveAll(session,obj);
                return new ResponseEntity<Object>(HttpStatus.OK);
            }

            return new ResponseEntity<Object>("No existe el usuario",
                                HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<Object> login(Usuario usuario, HttpSession session) {
        if (sessionController.isEmpty(session)){
            return new ResponseEntity<Object>("No hay usuarios en la lista de Session",
                    HttpStatus.BAD_REQUEST);
        } else {
            List<Usuario> obj = sessionController.getALl(session);

            int i = 0;
            for (Usuario elem : obj){
                if (elem.getId().equals(usuario.getId()))
                    if (passwordEncoder.matches(usuario.getPassword(),elem.getPassword())) {
                        sessionController.saveLoggedUser(session, elem);
                        return new ResponseEntity<Object>(elem, HttpStatus.OK);
                    } else {
                        return new ResponseEntity<Object>("Contraseña Erronea", HttpStatus.BAD_REQUEST);
                    }
            }
            return new ResponseEntity<Object>("No existe el usuario",
                    HttpStatus.BAD_REQUEST);
        }
    }
}

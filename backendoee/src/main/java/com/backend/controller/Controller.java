package com.backend.controller;

import com.backend.modelos.Usuario;
import com.backend.services.api.UsuarioServiceApi;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = {"http://localhost:4200","http://127.0.0.1:4200","http://frontend:4200"}, allowCredentials = "true")
//@CrossOrigin("*")
//@SessionAttributes({"USER_SESSIONDATA_ID","IS_LOCAL_ID","ARRAY_LOCAL"})
public class Controller {
    private final String USER_SESSIONDATA_ID = "USER_SESSIONDATA_ID";
    private final String IS_LOCAL_ID = "IS_LOCAL_ID";

    @Autowired
    private UsuarioServiceApi usuarioServiceApi;

    SessionController sessionController = new SessionController();
    UsuarioDBController usuarioDBController = new UsuarioDBController();
    UsuarioLocalController usuarioLocalController = new UsuarioLocalController();

    @GetMapping(value = "/all")
    public ResponseEntity<Object> getAll(HttpSession session) {
        if (sessionController.isLocalMode(session))
            return usuarioLocalController.getAll(session);
        else
            return usuarioDBController.getAll(usuarioServiceApi);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Object> getById(@PathVariable String id,HttpSession session) {
        if (sessionController.isLocalMode(session))
            return usuarioLocalController.getById(id, session);
        else
            return usuarioDBController.getById(id,usuarioServiceApi);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Object> save(@RequestBody Usuario usuario,HttpSession session) {
        if (sessionController.isLocalMode(session))
            return usuarioLocalController.save(usuario, session);
        else
            return usuarioDBController.save(usuario,usuarioServiceApi);
    }

    @GetMapping(value = "/del/{id}")
    public ResponseEntity<Object> delte(@PathVariable String id, HttpSession session) {
        if(sessionController.isLoggedUser(id,session))
            return new ResponseEntity<Object>("No puedes borrar tu cuenta por seguridad",
                HttpStatus.BAD_REQUEST);

        if (sessionController.isLocalMode(session))
            return usuarioLocalController.delte(id, session);
        else
            return usuarioDBController.delte(id,usuarioServiceApi);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestBody Usuario usuario, HttpSession session) {
        if (sessionController.isLocalMode(session))
            return usuarioLocalController.login(usuario, session);
        else
            return usuarioDBController.login(usuario, session, usuarioServiceApi);
    }

    @GetMapping(value = "/close")
    public ResponseEntity<Object> logout(SessionStatus status, HttpSession session) {
        sessionController.killSession(status, session);
        return new ResponseEntity<Object>("Session cerrad con exito",
                HttpStatus.OK);
    }

    @GetMapping(value = "/islogged")
    public ResponseEntity<Object> isLogged(HttpSession session) {
        if (sessionController.isLogged(session)){
            Usuario obj = (Usuario) session.getAttribute(USER_SESSIONDATA_ID);
            return new ResponseEntity<Object>(obj, HttpStatus.OK);
        }else
            return new ResponseEntity<Object>("No hay session previa",
                    HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/setmode")
    public ResponseEntity<Object> setMode(@RequestBody JSONObject obj, HttpSession session, SessionStatus status) {
        boolean isLocal = Boolean.parseBoolean(obj.getAsString(IS_LOCAL_ID));
        sessionController.setMode(session,isLocal);
        return new ResponseEntity<Object>("Modo local: "+sessionController.isLocalMode(session),
                HttpStatus.OK);
    }

    @GetMapping(value = "/getmode")
    public ResponseEntity<Object> getMode(HttpSession session) {
        boolean isLocal = sessionController.isLocalMode(session);
        JSONObject obj = new JSONObject();
        obj.appendField(IS_LOCAL_ID,isLocal);
        return new ResponseEntity<Object>(obj,
                HttpStatus.OK);
    }
}

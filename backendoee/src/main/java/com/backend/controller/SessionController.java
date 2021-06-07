package com.backend.controller;

import com.backend.modelos.Usuario;
import net.minidev.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class SessionController {
    private final String USER_SESSIONDATA_ID = "USER_SESSIONDATA_ID";
    private final String IS_LOCAL_ID = "IS_LOCAL_ID";
    private final String ARRAY_LOCAL = "ARRAY_LOCAL";

    public boolean isLogged(HttpSession session){
        return session.getAttribute(USER_SESSIONDATA_ID) != null;
    }

    public boolean saveLoggedUser(HttpSession session,Usuario usuario){
        session.setAttribute(USER_SESSIONDATA_ID, usuario);
        return true;
    }

    public boolean killSession(SessionStatus status, HttpSession session){
        session.invalidate();
        status.setComplete();
        return true;
    }

    public void setMode(HttpSession session,boolean isLocal){
        session.setAttribute(IS_LOCAL_ID, isLocal);
    }

    public boolean isLocalMode(HttpSession session){
        if (session.getAttribute(IS_LOCAL_ID) == null) {
            return false;
        }
        return (boolean) session.getAttribute(IS_LOCAL_ID);
    }

    public void saveUser(HttpSession session, Usuario usuario){
        if (isEmpty(session)) {
            List<Usuario> obj = new ArrayList<>();
            obj.add(usuario);
            session.setAttribute(ARRAY_LOCAL,obj);
        } else {
            List<Usuario> obj = (List<Usuario>) session.getAttribute(ARRAY_LOCAL);
            obj.add(usuario);
            session.setAttribute(ARRAY_LOCAL, obj);
        }
    }

    public boolean isEmpty(HttpSession session){
        if (session.getAttribute(ARRAY_LOCAL) == null) {
            return true;
        } else {
            List<Usuario> obj = (List<Usuario>) session.getAttribute(ARRAY_LOCAL);
            return obj.size() <= 0;
        }
    }

    public List<Usuario> getALl(HttpSession session){
        return (List<Usuario>) session.getAttribute(ARRAY_LOCAL);
    }

    public void saveAll(HttpSession session, List<Usuario> obj){
        session.setAttribute(ARRAY_LOCAL,obj);
    }

    public boolean isLoggedUser(String id, HttpSession session){
        Usuario obj = (Usuario) session.getAttribute(USER_SESSIONDATA_ID);
        if(obj == null)
            return true;

        return obj.getId().equals(id);
    }
}

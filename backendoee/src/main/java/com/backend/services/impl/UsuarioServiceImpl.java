package com.backend.services.impl;

import com.backend.api.UsuarioRepository;
import com.backend.commons.GenericServiceImpl;
import com.backend.modelos.Usuario;
import com.backend.services.api.UsuarioServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, String> implements UsuarioServiceApi {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public CrudRepository<Usuario, String> getDao() {
        return usuarioRepository ;
    }
}

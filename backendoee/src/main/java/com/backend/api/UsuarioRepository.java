package com.backend.api;

import com.backend.modelos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(value = {})
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

}

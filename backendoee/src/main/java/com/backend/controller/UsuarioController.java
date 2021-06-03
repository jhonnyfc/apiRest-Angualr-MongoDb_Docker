package com.backend.controller;

        import com.backend.modelos.ErrorResponse;
        import com.backend.modelos.Usuario;
        import com.backend.services.api.UsuarioServiceApi;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServiceApi usuarioServiceApi;

    @GetMapping(value = "/all")
    public ResponseEntity<Object> getAll(){
        List<Usuario> obj = usuarioServiceApi.getAll();
        if (obj.isEmpty())
            return new ResponseEntity<Object>(new ErrorResponse("BBDD Vacia"),
                                        HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Object>(obj, HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Object> getById(@PathVariable String id){
        Usuario obj =  usuarioServiceApi.get(id);
        if (obj == null)
            return new ResponseEntity<Object>(new ErrorResponse("Usuario no encontrado"),
                    HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Object>(obj, HttpStatus.OK);
    }

    // Lo guarda y puede actualizarlo
    @PostMapping(value = "/save")
    public ResponseEntity<Object> save(@RequestBody Usuario usuario){
        if (usuarioServiceApi.get(usuario.getEmail()) != null)
            return new ResponseEntity<Object>(new ErrorResponse("Ya exite"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        Usuario obj = usuarioServiceApi.save(usuario);
        if (obj == null)
            return new ResponseEntity<Object>(new ErrorResponse("Error al guardar"),
                                            HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<Object>(obj, HttpStatus.OK);
    }

    @GetMapping(value = "/del/{id}")
    public ResponseEntity<Object> delte(@PathVariable String id){
        if (usuarioServiceApi.get(id) == null)
            return  new ResponseEntity<Object>(new ErrorResponse("NO existe el usuario"),
                                        HttpStatus.BAD_REQUEST);

        usuarioServiceApi.delete(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}

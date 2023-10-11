package com.api.cursospringjdev.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.api.cursospringjdev.Model.Usuario;
import com.api.cursospringjdev.Repository.UsuarioRepository;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/form")
    public String form(){
        return "index2";
    }

    @GetMapping(value = "/listar")
    @ResponseBody
    public ResponseEntity<List<Usuario>> listUsuario() {

        List<Usuario> usuarios = usuarioRepository.findAll();
        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }

    @PostMapping(value = "salvar")
    @ResponseBody
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
        System.out.println(usuario);
        Usuario user = usuarioRepository.save(usuario);
        return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "delete")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long iduser) {

        usuarioRepository.deleteById(iduser);
        return new ResponseEntity<String>("Usuario deletado com sucesso", HttpStatus.OK);
    }

    @GetMapping(value = "buscarPorId")
    @ResponseBody
    public ResponseEntity<Usuario> buscarPorId(@RequestParam(name = "id") Long id) {

        Usuario usuario = usuarioRepository.findById(id).get();
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    @PutMapping(value = "atualizar")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) {

        if(usuario.getId() == null){
            return new ResponseEntity<String>("ID não foi informado", HttpStatus.OK);
        }

        Usuario user = usuarioRepository.saveAndFlush(usuario);
        return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }

    @GetMapping(value = "buscarPorNome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name) {

        List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
        return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }
}

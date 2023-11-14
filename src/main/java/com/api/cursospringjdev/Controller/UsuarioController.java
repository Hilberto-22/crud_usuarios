package com.api.cursospringjdev.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.api.cursospringjdev.Model.Usuario;
import com.api.cursospringjdev.Model.exception.UsuarioNotFoundException;
import com.api.cursospringjdev.Repository.UsuarioRepository;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/form")
	public String form() {
		return "index2";
	}

	@ResponseBody
	@GetMapping(value = "/listar")
	public ResponseEntity<List<Usuario>> listUsuario() {

		List<Usuario> usuarios = usuarioRepository.findAll();
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "salvar")
	public ResponseEntity<Usuario> salvar(@Valid @RequestBody Usuario usuario) {
		Usuario user = usuarioRepository.save(usuario);
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
	}

	@ResponseBody
	@DeleteMapping(value = "delete")
	public ResponseEntity<String> delete(@RequestParam Long iduser) {

		usuarioRepository.deleteById(iduser);
		return new ResponseEntity<String>("Usuario deletado com sucesso", HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "buscarPorId")
	public ResponseEntity<Usuario> buscarPorId(@RequestParam(name = "id") Long id) {

		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado no banco de dados"));
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@ResponseBody
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<?> atualizar(@PathVariable(name = "id") Long id, @RequestBody Usuario novoUsuario) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (usuario.isPresent()) {
			Usuario usuarioExistente = usuario.get();
			usuarioExistente.setNome(novoUsuario.getNome());
			usuarioExistente.setIdade(novoUsuario.getIdade());
			usuarioExistente.setDataNascimento(novoUsuario.getDataNascimento());
			usuarioRepository.saveAndFlush(usuarioExistente);
		}
		return ResponseEntity.ok().body("Usuario atualizado!!!");
	}

	@GetMapping(value = "buscarPorNome")
	@ResponseBody
	public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name) {

		List<Usuario> usuario = usuarioRepository.findByNomeIgnoreCaseContainingOrderByIdAsc(name.trim().toUpperCase());
		return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
	}
}

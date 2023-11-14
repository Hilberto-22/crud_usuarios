package com.api.cursospringjdev.Repository;

import java.util.List;

import com.api.cursospringjdev.Model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	List<Usuario> findByNomeIgnoreCaseContaining(String name);
}

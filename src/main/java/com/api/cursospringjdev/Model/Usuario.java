package com.api.cursospringjdev.Model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1, initialValue = 1)
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    private Long id;

	@NotEmpty(message = "Nome é obrigatório")
    private String nome;

    private Double idade;
    
    @NotNull(message = "Data de vencimento é obrigatória")
    @JsonFormat(timezone = "DEFAULT_LOCALE" )
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getIdade() {
        return idade;
    }

    public void setIdade(Double idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nome=" + nome + ", idade=" + idade + "]";
    }

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}

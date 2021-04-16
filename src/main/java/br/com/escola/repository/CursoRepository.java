package br.com.escola.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.escola.domain.Curso;


public interface CursoRepository extends JpaRepository<Curso, Long> {
	
}

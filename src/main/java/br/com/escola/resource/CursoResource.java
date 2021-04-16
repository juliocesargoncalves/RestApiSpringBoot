package br.com.escola.resource;

import java.util.List;

import javax.persistence.PostPersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.domain.Curso;
import br.com.escola.repository.CursoRepository;

@RestController
@RequestMapping("/cursos")
@CrossOrigin(origins = {"localhost:8080"}) 
public class CursoResource {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping
	public List<Curso> listarTodos(){
		return cursoRepository.findAll();
	}
	
	@PostMapping
	public Curso cadastrarCurso(@RequestBody Curso curso) {
		return cursoRepository.save(curso);
	}
	
	@PutMapping("/{codigo}")
	public Curso atualizarCurso(@PathVariable("codigo") Long codigo, @RequestBody Curso curso) {
		return cursoRepository.findById(codigo).map(
				retornoCurso ->{
					
					retornoCurso.setNome(curso.getNome());
					retornoCurso.setEmail(curso.getEmail());
					retornoCurso.setTelefone(curso.getTelefone());
					
					return cursoRepository.save(retornoCurso);
				}
			).orElse(null);
	}
	
	@GetMapping("/{codigo}")
	public Curso buscarPeloCodigo(@PathVariable("codigo") Long codigo) {
		return cursoRepository.findById(codigo).orElse(null);
	}
}

package br.com.alura.crud.repository;

import br.com.alura.crud.modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Curso findByNome(String nomeCurso);
}

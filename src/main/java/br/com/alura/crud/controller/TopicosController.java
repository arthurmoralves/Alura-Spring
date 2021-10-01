package br.com.alura.crud.controller;

import br.com.alura.crud.dto.DetalhesDoTopicoDto;
import br.com.alura.crud.dto.TopicoDto;
import br.com.alura.crud.form.AtualizacaoTopicoForm;
import br.com.alura.crud.form.TopicoForm;
import br.com.alura.crud.modelo.Topico;
import br.com.alura.crud.repository.CursoRepository;
import br.com.alura.crud.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/topicos")
public class TopicosController {

    @Autowired
    TopicoRepository topicoRepository;

    @Autowired
    CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> listarTopicos(String nomeCurso) {
        List<Topico> topicos = new ArrayList<>();
        if(nomeCurso == null){
            topicos = topicoRepository.findAll();
        } else {
            topicos = topicoRepository.findByCursoNome(nomeCurso);
        }
        return TopicoDto.converter(topicos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder componentsBuilder){
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri = componentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDoTopicoDto> detalhar(@PathVariable Long id) {
        var topico = topicoRepository.findById(id);
        if(topico.isPresent()) {
            return ResponseEntity.ok(new DetalhesDoTopicoDto(topico.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form){

        var topicoReturn = topicoRepository.findById(id);
        if(topicoReturn.isPresent()){
            var topico = form.atualizar(id, topicoRepository);
            return ResponseEntity.ok(new TopicoDto(topico));
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id){
        var topicoReturn = topicoRepository.findById(id);
        if(topicoReturn.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

package br.com.alura.crud.form;

import br.com.alura.crud.modelo.Curso;
import br.com.alura.crud.modelo.Topico;
import br.com.alura.crud.repository.CursoRepository;
import br.com.alura.crud.repository.TopicoRepository;
import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class AtualizacaoTopicoForm {

    @NotNull
    private String titulo;

    @Length(min=1)
    private String mensagem;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Topico atualizar(Long id, TopicoRepository topicoRepository){
        var topico = topicoRepository.getById(id);
        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);

        return topico;
    }

}

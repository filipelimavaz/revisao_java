package src.Entidades;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaPraRetornar {

    private long id;
    private String nome;
    private double nota;
    private List<Comentario> comentarios = new ArrayList<>();
    private int numeroDeLikes;

    public DisciplinaPraRetornar(long id, String nome, double nota, List<Comentario> comentarios, int numeroDeLikes) {
        this.id = id;
        this.nome = nome;
        this.nota = nota;
        this.comentarios = comentarios;
        this.numeroDeLikes = numeroDeLikes;
    }

    public static DisciplinaPraRetornar criaDisciplinaPraRetornar(Disciplina disciplina) {
        return new DisciplinaPraRetornar(disciplina.getId(), disciplina.getNome(), disciplina.getNota(), disciplina.getComentarios(), disciplina.getNumeroDeLikes());
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getNota() {
        return nota;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public int getNumeroDeLikes() {
        return numeroDeLikes;
    }
}

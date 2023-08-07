package src.Entidades;

import src.Excecoes.ComentarioInexistenteException;
import src.Excecoes.NaoTemPermissaoException;

import java.util.*;

public class Disciplina {

    static private long idAuxiliar;
    private long id;
    private String nome;
    private List<Double> notas = new ArrayList<>();
    private List<Comentario> comentarios = new ArrayList<>();
    private int numeroDeLikes;

    public Disciplina(String nome) {
        id = idAuxiliar++;
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double adicionaNota(double nota) {
        notas.add(nota);
        return notaMedia();
    }

    public double getNota() {
        return notaMedia();
    }

    private double notaMedia() {
        double notaMedia = 0;
        for(Double nota : notas) {
            notaMedia += nota;
        }
        if(notas.size() > 0) {
            return notaMedia/notas.size();
        } else {
            return notas.size();
        }
    }

    public int getNumeroDeLikes() {
        return numeroDeLikes;
    }

    public int incrementaLikes() {
        return ++numeroDeLikes;
    }

    public List<Comentario> getComentarios() {
        List<Comentario> comentariosValidos = new ArrayList<>();
        for(Comentario comentario : comentarios) {
            if(!comentario.isRemovido()) {
                comentariosValidos.add(comentario);
            }
        }
        return comentariosValidos;
    }

    public Comentario adicionaComentario(String comentarioEntrada, String email) {
        Comentario comentario = new Comentario(comentarioEntrada, email);
        comentarios.add(comentario);
        return comentario;
    }

    public void removeComentario(long idComentario, String email) throws NaoTemPermissaoException, ComentarioInexistenteException {
        for(Comentario comentario : comentarios) {
            if(comentario.getId() == idComentario) {
                if(comentario.getEmailUsuario().equals(email)) {
                    comentario.setRemovido(true);
                    return;
                } else {
                    throw new NaoTemPermissaoException();
                }
            } else {
                throw new ComentarioInexistenteException();
            }
        }
    }
}

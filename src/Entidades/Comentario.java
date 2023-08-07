package src.Entidades;

import java.util.Objects;

public class Comentario {

    static private long idAuxiliar;
    private long id;
    private String comentario;
    private String emailUsuario;
    private boolean removido = false;
    public Comentario(String comentario, String email) {
        id = idAuxiliar++;
        this.comentario = comentario;
        this.emailUsuario = emailUsuario;
    }

    public long getId() {
        return id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public boolean isRemovido() {
        return removido;
    }

    public void setRemovido(boolean removido) {
        this.removido = removido;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        Comentario other = (Comentario) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", comentario='" + comentario + '\'' +
                ", emailUsuario='" + emailUsuario + '\'' +
                ", removido=" + removido +
                '}';
    }
}

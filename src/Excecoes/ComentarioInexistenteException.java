package src.Excecoes;

public class ComentarioInexistenteException extends Exception{

    public ComentarioInexistenteException() {
        super("O comentário não existe");
    }
}

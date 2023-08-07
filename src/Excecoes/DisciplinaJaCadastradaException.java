package src.Excecoes;

public class DisciplinaJaCadastradaException extends Exception{

    public DisciplinaJaCadastradaException() {
        super("A disciplina já foi cadastrada");
    }
}

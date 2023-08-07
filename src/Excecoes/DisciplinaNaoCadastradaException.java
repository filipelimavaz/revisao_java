package src.Excecoes;

public class DisciplinaNaoCadastradaException extends Exception{

    public DisciplinaNaoCadastradaException() {
        super("A disciplina não foi cadastrada");
    }
}

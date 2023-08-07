package src.Excecoes;

public class AlunoNaoCadastradoException extends Exception{

    public AlunoNaoCadastradoException() {
        super("Aluno não cadastrado");
    }
}

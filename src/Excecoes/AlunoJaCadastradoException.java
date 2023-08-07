package src.Excecoes;

public class AlunoJaCadastradoException extends Exception{

    public AlunoJaCadastradoException() {
        super("Aluno já cadastrado");
    }
}

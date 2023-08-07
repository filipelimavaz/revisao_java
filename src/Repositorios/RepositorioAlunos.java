package src.Repositorios;

import src.Entidades.Aluno;
import src.Entidades.Credencial;
import src.Excecoes.AlunoJaCadastradoException;
import src.Excecoes.AlunoNaoCadastradoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioAlunos {

    private List<Aluno> alunos = new ArrayList<>();

    public Optional<Aluno> getAluno(String email) {
        for(Aluno aluno : alunos) {
            if(aluno.getEmail().equals(email)) {
                return Optional.of(aluno);
            }
        }
        return Optional.empty();
    }

    public Aluno adicionaAluno(String email, String senha, String nome) throws AlunoJaCadastradoException {
        if(alunoComMesmoEmail(email)) {
            throw new AlunoJaCadastradoException();
        } else {
            Aluno aluno = new Aluno(email, senha, nome);
            alunos.add(aluno);
            return aluno;
        }
    }

    private boolean alunoComMesmoEmail(String email) {
        for(Aluno aluno : alunos) {
            if(aluno.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public Aluno removeAluno(String email) throws AlunoNaoCadastradoException {
        if(alunoComMesmoEmail(email) == false) {
            throw new AlunoNaoCadastradoException();
        } else {
            Aluno aluno = getAluno(email).get();
            alunos.remove(aluno);
            return aluno;
        }
    }

    public String login(String email, String senha) {
        Optional<Aluno> aluno = getAluno(email);

        if(aluno.isEmpty()) {
            return Credencial.resultadoDoLogin(true);
        }

        if(aluno.get().getSenha().equals(senha)) {
            return Credencial.resultadoDoLogin(true);
        }

        return Credencial.resultadoDoLogin(false);
    }

    public int numeroDeAlunosCadastrados() {
        return alunos.size();
    }
}

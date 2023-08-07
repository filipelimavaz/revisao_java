package src.Fachada;

import src.Entidades.Aluno;
import src.Entidades.Disciplina;
import src.Entidades.DisciplinaPraRetornar;
import src.Excecoes.AlunoJaCadastradoException;
import src.Excecoes.AlunoNaoCadastradoException;
import src.Excecoes.DisciplinaJaCadastradaException;
import src.Repositorios.RepositorioAlunos;
import src.Repositorios.RepositorioDisciplinas;

import java.util.List;
import java.util.Optional;

public class UFPBSocial {
    public RepositorioAlunos alunos;
    public RepositorioDisciplinas disciplinas;

    public UFPBSocial() {
        alunos = new RepositorioAlunos();
        disciplinas = new RepositorioDisciplinas();
    }

    public Aluno adicionaAluno(String email, String senha, String nome) throws AlunoJaCadastradoException {
        return alunos.adicionaAluno(email, senha, nome);
    }

    public Aluno removeAluno(String email) throws AlunoNaoCadastradoException {
        return alunos.removeAluno(email);
    }

    public String login(String email, String senha) {
        return alunos.login(email, senha);
    }

    public DisciplinaPraRetornar adicionaDisciplina(String nome) throws DisciplinaJaCadastradaException {
        return disciplinas.adicionaDisciplina(nome);
    }

    public List<Disciplina> getTodasAsDisciplinas() {
        return disciplinas.getDisciplinas();
    }

    public int numeroDeAlunosCadastrados() {
        return alunos.numeroDeAlunosCadastrados();
    }

    public Optional<Aluno> getAluno(String email) {
        return alunos.getAluno(email);
    }
}

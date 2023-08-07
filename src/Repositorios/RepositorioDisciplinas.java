package src.Repositorios;

import src.Entidades.Disciplina;
import src.Entidades.DisciplinaPraRetornar;
import src.Excecoes.ComentarioInexistenteException;
import src.Excecoes.DisciplinaJaCadastradaException;
import src.Excecoes.DisciplinaNaoCadastradaException;
import src.Excecoes.NaoTemPermissaoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioDisciplinas {

    private List<Disciplina> disciplinas = new ArrayList<>();

    private Disciplina recuperaDisciplinaPeloNome(String nome) throws DisciplinaNaoCadastradaException {
        if(existeDisciplinaIgual(nome)) {
            return getDisciplina(nome).get();
        } else {
            throw new DisciplinaNaoCadastradaException();
        }
    }

    public DisciplinaPraRetornar adicionaDisciplina(String nome) throws DisciplinaJaCadastradaException {
        if(existeDisciplinaIgual(nome)) {
            throw new DisciplinaJaCadastradaException();
        } else {
            Disciplina disciplina = new Disciplina(nome);
            disciplinas.add(disciplina);
            return DisciplinaPraRetornar.criaDisciplinaPraRetornar(disciplina);
        }
    }

    private boolean existeDisciplinaIgual(String nome) {
        for(Disciplina disciplina : disciplinas) {
            if(disciplina.getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public DisciplinaPraRetornar atualizaNome(String nome, String novoNome) throws DisciplinaNaoCadastradaException {
        Disciplina disciplina = recuperaDisciplinaPeloNome(nome);
        disciplina.setNome(novoNome);
        return DisciplinaPraRetornar.criaDisciplinaPraRetornar(disciplina);
    }

    private Optional<Disciplina> getDisciplina(String nome) {
        for(Disciplina disciplina : disciplinas) {
            if(disciplina.getNome().equals(nome)){
                return Optional.of(disciplina);
            }
        }
        return null;
    }

    public DisciplinaPraRetornar recuperaDisciplina(String nome) {
        Optional<Disciplina> disciplina = getDisciplina(nome);
        if(disciplina.isEmpty()) {
            return null;
        } else {
            return DisciplinaPraRetornar.criaDisciplinaPraRetornar(disciplina.get());
        }
    }

    public DisciplinaPraRetornar removeComentario(String nome, long id, String email) throws DisciplinaNaoCadastradaException, ComentarioInexistenteException, NaoTemPermissaoException {
        Disciplina disciplina = recuperaDisciplinaPeloNome(nome);
        disciplina.removeComentario(id, email);
        return DisciplinaPraRetornar.criaDisciplinaPraRetornar(disciplina);
    }
}

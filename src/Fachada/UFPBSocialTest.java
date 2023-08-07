package src.Fachada;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Entidades.Aluno;
import src.Entidades.Disciplina;
import src.Entidades.DisciplinaPraRetornar;
import src.Excecoes.AlunoJaCadastradoException;
import src.Excecoes.AlunoNaoCadastradoException;
import src.Excecoes.DisciplinaJaCadastradaException;
import src.Repositorios.RepositorioAlunos;

import java.util.List;
import java.util.Optional;

public class UFPBSocialTest {
    UFPBSocial testesUFPB;

    @BeforeEach
    void setUp() throws Exception {
        testesUFPB = new UFPBSocial();
    }

    @Test
    void testAdicionaVerificaQuantidadeDeAlunos() {
        UFPBSocial ufpbSocial = new UFPBSocial();

        int numeroAlunosAntes = ufpbSocial.numeroDeAlunosCadastrados();
        System.out.println("Número de alunos antes das adições: " + numeroAlunosAntes);

        try {
            ufpbSocial.adicionaAluno("filipe@gmail.com", "senha123", "Filipe");
            ufpbSocial.adicionaAluno("michael@gmail.com", "senha456", "Michael");
        } catch (AlunoJaCadastradoException e) {
            e.printStackTrace();
        }

        int numeroAlunosDepois = ufpbSocial.numeroDeAlunosCadastrados();
        System.out.println("Número de alunos depois das adições: " + numeroAlunosDepois);
    }

    @Test
    void testAdicionaAlunoNovamente() throws AlunoJaCadastradoException, AlunoNaoCadastradoException {
        UFPBSocial ufpbSocial = new UFPBSocial();

        // Adicionar o aluno Filipe
        try {
            ufpbSocial.adicionaAluno("filipe@gmail.com", "senha123", "Filipe");
            System.out.println("Aluno Filipe adicionado com sucesso.");
        } catch (AlunoJaCadastradoException e) {
            e.printStackTrace();
        }

        // Verificar se o nome "Filipe" é igual ao aluno cadastrado
        Optional<Aluno> alunoOptional = ufpbSocial.getAluno("filipe@gmail.com");
        if (alunoOptional.isPresent()) {
            Aluno aluno = alunoOptional.get();
            if (aluno.getNome().equals("Filipe")) {
                System.out.println("O nome do aluno cadastrado é Filipe.");
            } else {
                System.out.println("Erro: O nome do aluno cadastrado não é Filipe.");
            }
        } else {
            System.out.println("Erro: Aluno Filipe não encontrado.");
        }

        // Tentar adicionar Filipe novamente
        try {
            ufpbSocial.adicionaAluno("filipe@gmail.com", "outraSenha", "Outro Filipe");
            System.out.println("Aluno Filipe adicionado novamente com sucesso.");
        } catch (AlunoJaCadastradoException e) {
            System.out.println("Erro: Aluno Filipe já está cadastrado.");
        }
    }

    @Test
    void testAdicionaRemoveAluno() {
        UFPBSocial ufpbSocial = new UFPBSocial();

        try {
            ufpbSocial.adicionaAluno("filipe@gmail.com", "senha123", "Filipe");
            ufpbSocial.adicionaAluno("michael@gmail.com", "senha456", "Michael");

            int numeroAlunosAntes = ufpbSocial.numeroDeAlunosCadastrados();
            System.out.println("Número de alunos antes da remoção: " + numeroAlunosAntes);

            ufpbSocial.removeAluno("filipe@gmail.com");

            int numeroAlunosDepois = ufpbSocial.numeroDeAlunosCadastrados();
            System.out.println("Número de alunos depois da remoção: " + numeroAlunosDepois);
        } catch (AlunoNaoCadastradoException e) {
            e.printStackTrace();
        } catch (AlunoJaCadastradoException e) {
            throw new RuntimeException(e);
        }

        // Teste de login
        String resultadoLogin = ufpbSocial.login("michael@gmail.com", "senha456");
        System.out.println("Resultado do login: " + resultadoLogin);

        // Teste de adição de disciplina
        try {
            DisciplinaPraRetornar disciplinaAdicionada = ufpbSocial.adicionaDisciplina("Desenvolvimento de Sistemas Corporativos");
            System.out.println("Disciplina adicionada: " + disciplinaAdicionada.getNome());
        } catch (DisciplinaJaCadastradaException e) {
            e.printStackTrace();
        }

        // Teste de obtenção de todas as disciplinas
        List<Disciplina> disciplinas = ufpbSocial.getTodasAsDisciplinas();
        System.out.println("Disciplinas cadastradas:");
        for (Disciplina disciplina : disciplinas) {
            System.out.println("- " + disciplina.getNome());
        }

        // Teste de obtenção de aluno por e-mail
        Optional<Aluno> aluno = ufpbSocial.getAluno("michael@gmail.com");
        if (aluno.isPresent()) {
            System.out.println("Aluno encontrado: " + aluno.get().getNome());
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    @Test
    void testeAtualizaNome() {
        UFPBSocial ufpbSocial = new UFPBSocial();

        // Adicionar o aluno Filipe
        try {
            ufpbSocial.adicionaAluno("filipe@gmail.com", "senha123", "Filipe");
            System.out.println("Aluno Filipe adicionado com sucesso.");
        } catch (AlunoJaCadastradoException e) {
            e.printStackTrace();
        }

        // Atualizar nome do aluno Filipe
        Optional<Aluno> alunoOptional = ufpbSocial.getAluno("filipe@gmail.com");
        if (alunoOptional.isPresent()) {
            Aluno aluno = alunoOptional.get();
            aluno.setNome("Filipe de Lima Vaz Monteiro");
            System.out.println("Nome do aluno atualizado com sucesso: " + aluno.getNome());
        } else {
            System.out.println("Erro: Aluno Filipe não encontrado.");
        }
    }

    @Test
    void testLogin() {
        RepositorioAlunos repositorio = new RepositorioAlunos();

        // Adicionar um aluno para o teste
        try {
            repositorio.adicionaAluno("filipe@gmail.com", "senha123", "Filipe");
        } catch (AlunoJaCadastradoException e) {
            e.printStackTrace();
        }

        // Testar login com credenciais corretas
        String resultadoLoginCorreto = repositorio.login("filipe@gmail.com", "senha123");
        System.out.println("Resultado do login com credenciais corretas: " + resultadoLoginCorreto);

        // Testar login com e-mail incorreto
        String resultadoLoginEmailIncorreto = repositorio.login("filipe2@gmail.com", "senha123");
        System.out.println("Resultado do login com e-mail incorreto: " + resultadoLoginEmailIncorreto);

        // Testar login com senha incorreta
        String resultadoLoginSenhaIncorreta = repositorio.login("filipe@gmail.com", "senha456");
        System.out.println("Resultado do login com senha incorreta: " + resultadoLoginSenhaIncorreta);
    }

    @Test
    void testAdicionaModificaNomeDeDisciplinas() {
        UFPBSocial ufpbSocial = new UFPBSocial();

        // Adicionar várias disciplinas
        try {
            ufpbSocial.adicionaDisciplina("Inteligência Artificial");
            ufpbSocial.adicionaDisciplina("Desenvolvimento de Sistemas Corporativos");
            ufpbSocial.adicionaDisciplina("Banco de Dados");
            ufpbSocial.adicionaDisciplina("Programação Orientada a Objetos");
            System.out.println("Disciplinas adicionadas com sucesso.");
        } catch (DisciplinaJaCadastradaException e) {
            e.printStackTrace();
        }

        List<Disciplina> disciplinas = ufpbSocial.getTodasAsDisciplinas();

        // Exibir os nomes antes da atualização das disciplinas
        for (Disciplina disciplina : disciplinas) {
            System.out.println(disciplina.getNome());
        }
        System.out.println("---------------------");

        // Atualizar nome das disciplinas de IA e Banco de Dados
        for (Disciplina disciplina : disciplinas) {
            if (disciplina.getNome().equals("Inteligência Artificial")) {
                disciplina.setNome("Inteligência Artificial II");
            } else if (disciplina.getNome().equals("Banco de Dados")) {
                disciplina.setNome("Banco de Dados II");
            }
        }

        System.out.println("Nomes das disciplinas atualizados com sucesso:");

        // Exibir os nomes atualizados das disciplinas
        for (Disciplina disciplina : disciplinas) {
            System.out.println(disciplina.getNome());
        }
    }
}

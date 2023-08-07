package src.Excecoes;

public class NaoTemPermissaoException extends Exception{
    public NaoTemPermissaoException() {
        super("O usuário não tem permissão para fazer essa ação");
    }
}

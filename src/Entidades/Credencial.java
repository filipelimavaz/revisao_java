package src.Entidades;

public class Credencial {

    static public String resultadoDoLogin(boolean bemSucedido) {
        if(bemSucedido) {
            return "Usuário Logado";
        } else {
            return "Usuário ou senha incorretos";
        }
    }
}

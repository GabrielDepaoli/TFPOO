package TFPOO;

public class Validador {

    // validar nome: apenas letras e espaços
    public static void validarNome(String nome) throws DadoInvalidoException {
        if (nome == null || nome.isEmpty())
            throw new DadoInvalidoException("O nome não pode ser vazio.");

        for (char c : nome.toCharArray()) {
            if (!Character.isLetter(c) && c != ' ') {
                throw new DadoInvalidoException("O nome deve conter apenas letras e espaços.");
            }
        }
    }

    // validar RG: 7 a 9 dígitos, apenas números
    public static void validarRg(String rg) throws DadoInvalidoException {
        if (rg == null || rg.length() < 7 || rg.length() > 9)
            throw new DadoInvalidoException("O RG deve ter entre 7 e 9 dígitos.");

        for (char c : rg.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new DadoInvalidoException("O RG deve conter apenas números.");
            }
        }
    }

    // validar telefone: exatamente 11 números
    public static void validarTelefone(String telefone) throws DadoInvalidoException {
        if (telefone == null || telefone.length() != 11)
            throw new DadoInvalidoException("O telefone deve ter exatamente 11 dígitos.");

        for (char c : telefone.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new DadoInvalidoException("O telefone deve conter apenas números.");
            }
        }
    }
}

package TFPOO;

public class DadoInvalidoException extends Exception { // centraliza e padroniza o tratamento de erros de entrada
    public DadoInvalidoException(String msg) {
        super(msg);
    }
}

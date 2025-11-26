package TFPOO;
// interface genérica para cadastro com métodos adicionar e listar
public interface Cadastro<T> {
    void adicionar(T obj);
    void listar();
}

package br.com.appvagasvan.old.services;
public interface GerenciadorCRUD<T> {
    T criar(T entidade);
    java.util.List<T> buscarTodos();
    T buscarPorId(int id);
    T atualizar(T entidade);
    void deletar(int id);
}
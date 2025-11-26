package br.com.appvagasvan.domain.motorista;

import br.com.appvagasvan.domain.exception.DomainException;
import java.util.Objects;

/**
 * Entidade de domínio Motorista - Aggregate Root
 * Representa o motorista responsável pelos turnos.
 */
public class Motorista {
    private final Integer id;
    private String nome;

    private Motorista(Integer id, String nome) {
        this.id = validarId(id);
        this.nome = validarNome(nome);
    }

    public static Motorista criar(Integer id, String nome) {
        return new Motorista(id, nome);
    }

    public static Motorista reconstituir(Integer id, String nome) {
        return new Motorista(id, nome);
    }

    private Integer validarId(Integer id) {
        if (id == null || id <= 0) {
            throw new DomainException("ID do motorista deve ser um número positivo.");
        }
        return id;
    }

    private String validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new DomainException("Nome do motorista não pode ser vazio.");
        }
        return nome.trim();
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Motorista motorista = (Motorista) o;
        return Objects.equals(id, motorista.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
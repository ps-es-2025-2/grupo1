package br.com.appvagasvan.domain.repository;

import br.com.appvagasvan.domain.passageiro.Passageiro;
import java.util.List;
import java.util.Optional;

/**
 * Interface de repositório para Passageiro
 * Define o contrato para persistência da entidade Passageiro
 */
public interface PassageiroRepository {
    
    /**
     * Salva ou atualiza um passageiro
     */
    Passageiro salvar(Passageiro passageiro);
    
    /**
     * Busca um passageiro por ID
     */
    Optional<Passageiro> buscarPorId(Integer id);
    
    /**
     * Busca todos os passageiros
     */
    List<Passageiro> buscarTodos();
    
    /**
     * Busca passageiros confirmados em um turno
     */
    List<Passageiro> buscarConfirmadosPorTurno(Integer turnoId);
    
    /**
     * Busca passageiros não confirmados em um turno
     */
    List<Passageiro> buscarNaoConfirmadosPorTurno(Integer turnoId);
    
    /**
     * Deleta um passageiro
     */
    void remover(Integer id);
    
    /**
     * Verifica se um passageiro existe
     */
    boolean existe(Integer id);
    
    /**
     * Gera o próximo ID disponível
     */
    Integer proximoId();

    /**
     * Define o próximo ID a ser gerado
     */
    void definirProximoId(Integer id);

    /**
     * Busca um passageiro pelo telefone
     */
    Optional<Passageiro> buscarPorTelefone(String telefone);
}
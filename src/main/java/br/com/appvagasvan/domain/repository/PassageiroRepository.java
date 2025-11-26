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
    Passageiro save(Passageiro passageiro);
    
    /**
     * Busca um passageiro por ID
     */
    Optional<Passageiro> findById(Integer id);
    
    /**
     * Busca todos os passageiros
     */
    List<Passageiro> findAll();
    
    /**
     * Busca passageiros confirmados em um turno
     */
    List<Passageiro> findConfirmadosByTurno(Integer turnoId);
    
    /**
     * Busca passageiros não confirmados em um turno
     */
    List<Passageiro> findNaoConfirmadosByTurno(Integer turnoId);
    
    /**
     * Deleta um passageiro
     */
    void delete(Integer id);
    
    /**
     * Verifica se um passageiro existe
     */
    boolean exists(Integer id);
    
    /**
     * Gera o próximo ID disponível
     */
    Integer nextId();
}
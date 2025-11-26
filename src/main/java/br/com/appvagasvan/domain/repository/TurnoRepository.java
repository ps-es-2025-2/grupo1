package br.com.appvagasvan.domain.repository;

import java.util.List;
import java.util.Optional;

import br.com.appvagasvan.domain.turno.Turno;

/**
 * Interface de repositório para Turno
 */
public interface TurnoRepository {
    
    /**
     * Salva ou atualiza um turno
     */
    Turno save(Turno turno);
    
    /**
     * Busca um turno por ID
     */
    Optional<Turno> findById(Integer id);
    
    /**
     * Busca todos os turnos de um motorista
     */
    List<Turno> findByMotorista(Integer motoristaId);
    
    /**
     * Busca todos os turnos
     */
    List<Turno> findAll();
    
    /**
     * Deleta um turno
     */
    void delete(Integer id);
    
    /**
     * Verifica se um turno existe
     */
    boolean exists(Integer id);
    
    /**
     * Gera o próximo ID disponível
     */
    Integer nextId();
}


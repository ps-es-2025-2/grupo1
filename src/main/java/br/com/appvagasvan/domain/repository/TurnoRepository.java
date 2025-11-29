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
    Turno salvar(Turno turno);
    
    /**
     * Busca um turno por ID
     */
    Optional<Turno> buscarPorId(Integer id);
    
    /**
     * Busca todos os turnos de um motorista
     */
    List<Turno> findByMotorista(Integer motoristaId);
    
    /**
     * Busca todos os turnos
     */
    List<Turno> buscarTodos();
    
    /**
     * Deleta um turno
     */
    void remover(Integer id);
    
    /**
     * Verifica se um turno existe
     */
    boolean existe(Integer id);
    
    /**
     * Gera o próximo ID disponível
     */
    Integer proximoId();
}


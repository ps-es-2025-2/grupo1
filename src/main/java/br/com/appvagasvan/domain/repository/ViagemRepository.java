package br.com.appvagasvan.domain.repository;

import java.util.List;
import java.util.Optional;

import br.com.appvagasvan.domain.viagem.Viagem;

/**
 * Interface de repositório para Viagem/Simulação
 */
public interface ViagemRepository {
    
    /**
     * Salva uma viagem/simulação
     */
    Viagem salvar(Viagem viagem);
    
    /**
     * Busca viagens por turno
     */
    List<Viagem> findByTurno(Integer turnoId);
    
    /**
     * Busca a última simulação de um turno
     */
    Optional<Viagem> findUltimaSimulacaoByTurno(Integer turnoId);
    
    /**
     * Busca simulações recentes
     */
    List<Viagem> findSimulacoesRecentes(int limite);
}
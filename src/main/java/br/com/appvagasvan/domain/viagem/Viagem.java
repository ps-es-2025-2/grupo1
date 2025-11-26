package br.com.appvagasvan.domain.viagem;

import br.com.appvagasvan.domain.events.RotaOtimizadaCalculadaEvent;
import br.com.appvagasvan.domain.exception.DomainException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Entidade de domínio Viagem - representa uma simulação ou execução real de rota
 */
public class Viagem {
    private final Integer id;
    private final Integer turnoId;
    private final Double distanciaTotalKm;
    private final Integer tempoEstimadoMinutos;
    private final List<Integer> ordemColetaPassageiros;
    private final List<Object> domainEvents;

    private Viagem(Integer id, Integer turnoId, Double distanciaTotalKm,
                   Integer tempoEstimadoMinutos, List<Integer> ordemColetaPassageiros) {
        this.id = validarId(id);
        this.turnoId = validarTurnoId(turnoId);
        this.distanciaTotalKm = validarDistancia(distanciaTotalKm);
        this.tempoEstimadoMinutos = validarTempo(tempoEstimadoMinutos);
        this.ordemColetaPassageiros = validarOrdemColeta(ordemColetaPassageiros);
        this.domainEvents = new ArrayList<>();
    }

    public static Viagem simular(Integer id, Integer turnoId, 
                                 List<Integer> passageirosConfirmados,
                                 Double distanciaKm, Integer tempoMinutos) {
        if (id == null || turnoId == null) {
            throw new DomainException("ID da viagem e turno são obrigatórios");
        }
        
        Viagem viagem = new Viagem(id, turnoId, distanciaKm, tempoMinutos, passageirosConfirmados);
        viagem.domainEvents.add(new RotaOtimizadaCalculadaEvent(turnoId));
        
        return viagem;
    }

    public static Viagem reconstituir(Integer id, Integer turnoId, 
                                      Double distanciaKm, Integer tempoMinutos,
                                      List<Integer> ordem) {
        return new Viagem(id, turnoId, distanciaKm, tempoMinutos, ordem);
    }

    // Métodos de validação
    private Integer validarId(Integer id) {
        if (id <= 0) {
            throw new DomainException("ID da viagem deve ser positivo");
        }
        return id;
    }

    private Integer validarTurnoId(Integer turnoId) {
        if (turnoId <= 0) {
            throw new DomainException("ID do turno deve ser positivo");
        }
        return turnoId;
    }

    private Double validarDistancia(Double distanciaKm) {
        if (distanciaKm < 0) {
            throw new DomainException("Distância não pode ser negativa");
        }
        if (distanciaKm > 1000) {
            throw new DomainException("Distância não pode exceder 1000 km");
        }
        return distanciaKm;
    }

    private Integer validarTempo(Integer minutos) {
        if (minutos < 0) {
            throw new DomainException("Tempo estimado não pode ser negativo");
        }
        if (minutos > 480) { // 8 horas
            throw new DomainException("Tempo estimado não pode exceder 480 minutos");
        }
        return minutos;
    }

    private List<Integer> validarOrdemColeta(List<Integer> passageiros) {
        if (passageiros == null || passageiros.isEmpty()) {
            throw new DomainException("Ordem de coleta não pode ser vazia");
        }
        return new ArrayList<>(passageiros);
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public Integer getTurnoId() {
        return turnoId;
    }

    public Double getDistanciaTotalKm() {
        return distanciaTotalKm;
    }

    public Integer getTempoEstimadoMinutos() {
        return tempoEstimadoMinutos;
    }

    public List<Integer> getPassageirosEmOrdem() {
        return Collections.unmodifiableList(ordemColetaPassageiros);
    }

    public List<Object> getDomainEvents() {
        return new ArrayList<>(domainEvents);
    }

    public void clearDomainEvents() {
        this.domainEvents.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Viagem viagem = (Viagem) o;
        return id == viagem.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
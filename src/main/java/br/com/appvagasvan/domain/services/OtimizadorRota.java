package br.com.appvagasvan.domain.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import br.com.appvagasvan.domain.passageiro.Passageiro;

/**
 * Domain Service: Otimizador de Rota
 * Responsável por calcular a melhor ordem de coleta dos passageiros
 */
public class OtimizadorRota {

    /**
     * Calcula a rota otimizada para coleta de passageiros
     * Implementação simplificada: ordena por nome (alfabética)
     * Futuramente pode integrar com API de mapas real
     */
    public ResultadoOtimizacao calcularRotaOtimizada(List<Passageiro> passageiros) {
        if (passageiros == null || passageiros.isEmpty()) {
            return new ResultadoOtimizacao(
                new ArrayList<>(),
                Double.valueOf(0),
                Integer.valueOf(0)
            );
        }

        // Ordenar passageiros (simplificado - por nome)
        List<Passageiro> passageirosOrdenados = new ArrayList<>(passageiros);
        passageirosOrdenados.sort(Comparator.comparing(p -> p.getNome()));

        // Extrair IDs na ordem
        List<Integer> ordemIds = new ArrayList<>();
        for (Passageiro p : passageirosOrdenados) {
            ordemIds.add(p.getId());
        }

        // Calcular estimativas (simplificado)
        double distanciaKm = calcularDistanciaEstimada(passageirosOrdenados.size());
        int tempoMinutos = calcularTempoEstimado(passageirosOrdenados.size());

        return new ResultadoOtimizacao(
            ordemIds,
            distanciaKm,
            tempoMinutos
        );
    }

    private double calcularDistanciaEstimada(int quantidadePassageiros) {
        // Fórmula simplificada: 5km base + 3.5km por passageiro
        return 5.0 + (quantidadePassageiros * 3.5);
    }

    private int calcularTempoEstimado(int quantidadePassageiros) {
        // Fórmula simplificada: 10min base + 5min por passageiro
        return 10 + (quantidadePassageiros * 5);
    }

    /**
     * Classe interna para encapsular resultado da otimização
     */
    public static class ResultadoOtimizacao {
        private final List<Integer> ordemPassageiros;
        private final Double distancia;
        private final Integer tempoEstimado;

        public ResultadoOtimizacao(List<Integer> ordemPassageiros,
                                   Double distancia,
                                   Integer tempoEstimado) {
            this.ordemPassageiros = ordemPassageiros;
            this.distancia = distancia;
            this.tempoEstimado = tempoEstimado;
        }

        public List<Integer> getOrdemPassageiros() {
            return ordemPassageiros;
        }

        public Double getDistancia() {
            return distancia;
        }

        public Integer getTempoEstimado() {
            return tempoEstimado;
        }
    }
}
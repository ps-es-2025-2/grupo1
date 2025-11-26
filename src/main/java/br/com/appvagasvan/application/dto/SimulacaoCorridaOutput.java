package br.com.appvagasvan.application.dto;

import java.util.List;

public class SimulacaoCorridaOutput {
    private final int turnoId;
    private final String nomeTurno;
    private final double distanciaKm;
    private final int tempoMinutos;
    private final String tempoFormatado;
    private final List<PassageiroOutput> ordemColeta;

    public SimulacaoCorridaOutput(int turnoId, String nomeTurno, double distanciaKm,
                                  int tempoMinutos, String tempoFormatado,
                                  List<PassageiroOutput> ordemColeta) {
        this.turnoId = turnoId;
        this.nomeTurno = nomeTurno;
        this.distanciaKm = distanciaKm;
        this.tempoMinutos = tempoMinutos;
        this.tempoFormatado = tempoFormatado;
        this.ordemColeta = ordemColeta;
    }

    // Getters
    public int getTurnoId() { return turnoId; }
    public String getNomeTurno() { return nomeTurno; }
    public double getDistanciaKm() { return distanciaKm; }
    public int getTempoMinutos() { return tempoMinutos; }
    public String getTempoFormatado() { return tempoFormatado; }
    public List<PassageiroOutput> getOrdemColeta() { return ordemColeta; }
}

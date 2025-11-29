package br.com.appvagasvan.application.dto;

import br.com.appvagasvan.domain.turno.TipoTurno;

import java.util.List;

public class SimulacaoCorridaOutput {
    private final int turnoId;
    private final TipoTurno tipoTurno;
    private final double distanciaKm;
    private final int tempoMinutos;
    private final String tempoFormatado;
    private final List<PassageiroOutput> ordemColeta;

    public SimulacaoCorridaOutput(int turnoId, TipoTurno tipoTurno, double distanciaKm,
                                  int tempoMinutos, String tempoFormatado,
                                  List<PassageiroOutput> ordemColeta) {
        this.turnoId = turnoId;
        this.tipoTurno = tipoTurno;
        this.distanciaKm = distanciaKm;
        this.tempoMinutos = tempoMinutos;
        this.tempoFormatado = tempoFormatado;
        this.ordemColeta = ordemColeta;
    }

    // Getters
    public int getTurnoId() { return turnoId; }
    public TipoTurno getTipoTurno() { return tipoTurno; }
    public double getDistanciaKm() { return distanciaKm; }
    public int getTempoMinutos() { return tempoMinutos; }
    public String getTempoFormatado() { return tempoFormatado; }
    public List<PassageiroOutput> getOrdemColeta() { return ordemColeta; }
}

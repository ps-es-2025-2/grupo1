package br.com.appvagasvan.application.dto;

public class RemoverTurnoInput {
    private final Integer turnoId;

    public RemoverTurnoInput(Integer turnoId) {
        this.turnoId = turnoId;
    }

    public Integer getTurnoId() {
        return turnoId;
    }
}

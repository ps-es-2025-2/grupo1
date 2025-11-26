package br.com.appvagasvan.application.dto;

public class SimularCorridaInput {
    private final int turnoId;

    public SimularCorridaInput(int turnoId) {
        this.turnoId = turnoId;
    }

    public int getTurnoId() {
        return turnoId;
    }
}
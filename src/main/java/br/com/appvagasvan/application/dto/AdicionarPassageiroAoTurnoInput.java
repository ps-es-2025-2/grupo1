package br.com.appvagasvan.application.dto;

public class AdicionarPassageiroAoTurnoInput {
    private final int passageiroId;
    private final int turnoId;

    public AdicionarPassageiroAoTurnoInput(int passageiroId, int turnoId) {
        this.passageiroId = passageiroId;
        this.turnoId = turnoId;
    }

    public int getPassageiroId() {
        return passageiroId;
    }

    public int getTurnoId() {
        return turnoId;
    }
}
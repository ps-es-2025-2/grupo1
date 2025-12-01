package br.com.appvagasvan.application.dto;

public class MoverPassageiroDeTurnoInput {
    private final int passageiroId;
    private final int turnoAntigoId;
    private final int turnoNovoId;

    public MoverPassageiroDeTurnoInput(int passageiroId, int turnoAntigoId, int turnoNovoId) {
        this.passageiroId = passageiroId;
        this.turnoAntigoId = turnoAntigoId;
        this.turnoNovoId = turnoNovoId;
    }

    public int getPassageiroId() {
        return passageiroId;
    }

    public int getTurnoAntigoId() {
        return turnoAntigoId;
    }

    public int getTurnoNovoId() {
        return turnoNovoId;
    }
}

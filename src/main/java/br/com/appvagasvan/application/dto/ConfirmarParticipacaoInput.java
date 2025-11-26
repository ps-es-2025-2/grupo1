package br.com.appvagasvan.application.dto;

/**
 * DTOs de Input
 */

// ConfirmarParticipacaoInput
public class ConfirmarParticipacaoInput {
    private final int passageiroId;
    private final int turnoId;

    public ConfirmarParticipacaoInput(int passageiroId, int turnoId) {
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
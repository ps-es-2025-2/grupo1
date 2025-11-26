package br.com.appvagasvan.domain.events;

import java.time.LocalDateTime;

public class PassageiroAdicionadoAoTurnoEvent {
    private final Integer turnoId;
    private final Integer passageiroId;
    private final LocalDateTime ocorridoEm;

    public PassageiroAdicionadoAoTurnoEvent(Integer turnoId, Integer passageiroId) {
        this.turnoId = turnoId;
        this.passageiroId = passageiroId;
        this.ocorridoEm = LocalDateTime.now();
    }

    public Integer getTurnoId() {
        return turnoId;
    }

    public Integer getPassageiroId() {
        return passageiroId;
    }

    public LocalDateTime getOcorridoEm() {
        return ocorridoEm;
    }
}
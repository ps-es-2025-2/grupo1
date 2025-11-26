package br.com.appvagasvan.domain.events;

import java.time.LocalDateTime;

public class TurnoCriadoEvent {
    private final Integer turnoId;
    private final LocalDateTime ocorridoEm;

    public TurnoCriadoEvent(Integer turnoId) {
        this.turnoId = turnoId;
        this.ocorridoEm = LocalDateTime.now();
    }

    public Integer getTurnoId() {
        return turnoId;
    }

    public LocalDateTime getOcorridoEm() {
        return ocorridoEm;
    }
}

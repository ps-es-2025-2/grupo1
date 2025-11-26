package br.com.appvagasvan.domain.events;

import java.time.LocalDateTime;

public class RotaOtimizadaCalculadaEvent {
    private final Integer turnoId;
    private final LocalDateTime ocorridoEm;

    public RotaOtimizadaCalculadaEvent(Integer turnoId) {
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

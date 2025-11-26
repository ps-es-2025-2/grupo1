package br.com.appvagasvan.domain.events;

import java.time.LocalDateTime;

/**
 * Evento: Passageiro confirmou presença
 */
public class PassageiroConfirmouPresencaEvent {
    private final Integer passageiroId;
    private final LocalDateTime ocorridoEm;

    public PassageiroConfirmouPresencaEvent(Integer passageiroId) {
        this.passageiroId = passageiroId;
        this.ocorridoEm = LocalDateTime.now();
    }

    public Integer getPassageiroId() {
        return passageiroId;
    }

    public LocalDateTime getOcorridoEm() {
        return ocorridoEm;
    }
}
package br.com.appvagasvan.domain.events;

import java.time.LocalDateTime;

public class PassageiroCancelouPresencaEvent {
    private final Integer passageiroId;
    private final LocalDateTime ocorridoEm;

    public PassageiroCancelouPresencaEvent(Integer passageiroId) {
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
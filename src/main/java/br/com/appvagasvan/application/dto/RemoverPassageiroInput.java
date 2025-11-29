package br.com.appvagasvan.application.dto;

public class RemoverPassageiroInput {
    private final Integer passageiroId;

    public RemoverPassageiroInput(Integer passageiroId) {
        this.passageiroId = passageiroId;
    }

    public Integer getPassageiroId() {
        return passageiroId;
    }
}

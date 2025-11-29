package br.com.appvagasvan.application.dto;

import java.util.List;

public class ListaTurnosOutput {

    private final List<TurnoOutput> turnos;

    public ListaTurnosOutput(List<TurnoOutput> turnos) {
        this.turnos = turnos;
    }

    public List<TurnoOutput> getTurnos() {
        return turnos;
    }
}

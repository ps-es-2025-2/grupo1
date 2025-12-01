package br.com.appvagasvan.application.dto;

import br.com.appvagasvan.domain.turno.Horario;
import br.com.appvagasvan.domain.turno.TipoTurno;

public class TurnoOutput {
    private final int id;
    private final TipoTurno tipoTurno;
    private final Horario horario;
    private final int vagasDisponiveis;
    private final int passageirosCount;

    public TurnoOutput(int id, TipoTurno tipoTurno, Horario horario, int vagasDisponiveis, int passageirosCount) {
        this.id = id;
        this.tipoTurno = tipoTurno;
        this.horario = horario;
        this.vagasDisponiveis = vagasDisponiveis;
        this.passageirosCount = passageirosCount;
    }

    // Getters
    public int getId() { return id; }
    public TipoTurno getTipoTurno() { return tipoTurno; }
    public Horario getHorario() { return horario; }
    public int getVagasDisponiveis() { return vagasDisponiveis; }
    public int getPassageirosCount() { return passageirosCount; }
}
package br.com.appvagasvan.application.dto;

import java.util.List;

public class ListaPassageirosConfirmadosOutput {
    private final int turnoId;
    private final String nomeTurno;
    private final List<PassageiroOutput> passageirosConfirmados;
    private final int totalConfirmados;

    public ListaPassageirosConfirmadosOutput(int turnoId, String nomeTurno,
                                             List<PassageiroOutput> passageirosConfirmados) {
        this.turnoId = turnoId;
        this.nomeTurno = nomeTurno;
        this.passageirosConfirmados = passageirosConfirmados;
        this.totalConfirmados = passageirosConfirmados.size();
    }

    // Getters
    public int getTurnoId() { return turnoId; }
    public String getNomeTurno() { return nomeTurno; }
    public List<PassageiroOutput> getPassageirosConfirmados() { return passageirosConfirmados; }
    public int getTotalConfirmados() { return totalConfirmados; }
}
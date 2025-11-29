package br.com.appvagasvan.application.dto;

import java.util.List;

public class ListaPassageirosOutput {
    
    private final List<PassageiroOutput> passageiros;

    public ListaPassageirosOutput(List<PassageiroOutput> passageiros) {
        this.passageiros = passageiros;
    }

    public List<PassageiroOutput> getPassageiros() {
        return passageiros;
    }
}

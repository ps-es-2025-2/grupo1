package br.com.appvagasvan.model.test;
import br.com.appvagasvan.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TesteModels {
    public static void main(String[] args) {
        Passageiro p1 = new Passageiro(1, "Gabriel F.", "Rua Verde, 123", true);
        Passageiro p2 = new Passageiro(2, "Gabriel R.", "Rua Azul, 456", false);
        Passageiro p3 = new Passageiro(3, "Laura", "Rua Laranja, 789", true);
        Passageiro p4 = new Passageiro(4, "Leia", "Rua Vermelha, 135", false);
        Passageiro p5 = new Passageiro(5, "Tallya", "Rua Amarela, 579", false);

        Turno turnoManha = new Turno(1, "Turno da Manhã", "08:00", 10, "07:30");

        turnoManha.getPassageirosAssociados().addAll(p1, p2, p3, p4, p5);

        turnoManha.getPassageirosConfirmados().add(p2);
        turnoManha.getPassageirosConfirmados().add(p4);

        Motorista motorista = new Motorista(1, "Marcos", "Terminal Central");
        motorista.getTurnosGerenciados().add(turnoManha);

        ObservableList<Passageiro> ordemOtimizada = FXCollections.observableArrayList(p4, p2);
        SimulacaoCorrida simulacao = new SimulacaoCorrida(turnoManha, 12.5, 25, ordemOtimizada);

        System.out.println("\n=== SIMULAÇÃO CORRIDA ===");
        System.out.println("Turno: " + simulacao.getTurno().getNome());
        System.out.println("Distância total: " + simulacao.getDistanciaTotalKm() + " km");
        System.out.println("Tempo estimado: " + simulacao.getTempoEstimadoMinutos() + " min");
        System.out.println("Ordem otimizada de coleta:");
        for (Passageiro p : simulacao.getOrdemColetaOtimizada()) {
            System.out.println("- " + p.getNome());
        }
    }
}

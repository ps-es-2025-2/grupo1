import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.stream.Collectors;

public class GerenciadorVans {

    private static final GerenciadorVans instance = new GerenciadorVans();

    private final ObservableList<Passageiro> passageiros;
    private final ObservableList<Turno> turnos;
    private final Motorista motoristaLogado;

    private int nextPassageiroId = 1;
    private int nextTurnoId = 1;

    private GerenciadorVans() {
        this.passageiros = FXCollections.observableArrayList();
        this.turnos = FXCollections.observableArrayList();
        this.motoristaLogado = new Motorista(1001, "Marcos", "Rua do Ponto Inicial, 100");
    }

    public static GerenciadorVans getInstance() {
        return instance;
    }

    public ObservableList<Passageiro> getPassageiros() {
        return passageiros;
    }

    public ObservableList<Turno> getTurnos() {
        return turnos;
    }
    
    public Motorista getMotoristaLogado() {
        return motoristaLogado;
    }

    public Passageiro criarPassageiro(String nome, String enderecoColeta, Turno turnoInicial) {
        Passageiro novoPassageiro = new Passageiro(nextPassageiroId++, nome, enderecoColeta, false);
        passageiros.add(novoPassageiro);

        if (turnoInicial != null) {
            turnoInicial.getPassageirosAssociados().add(novoPassageiro);
        }
        return novoPassageiro;
    }

    public Passageiro atualizarPassageiro(Passageiro passageiro) {
        Passageiro existente = passageiros.stream()
            .filter(p -> p.getId() == passageiro.getId())
            .findFirst()
            .orElse(null);

        if (existente != null) {
            existente.setNome(passageiro.getNome());
            existente.setEnderecoColeta(passageiro.getEnderecoColeta());
        }
        return existente;
    }

    public void deletarPassageiro(Passageiro passageiro) {
        for (Turno turno : turnos) {
            turno.getPassageirosAssociados().removeIf(p -> p.getId() == passageiro.getId());
            turno.getPassageirosConfirmados().removeIf(p -> p.getId() == passageiro.getId());
        }
        passageiros.remove(passageiro);
    }
    
    public Turno criarTurno(String nome, String horario, int capacidade, String horarioLembrete) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade de vagas deve ser positiva.");
        }
        Turno novoTurno = new Turno(nextTurnoId++, nome, horario, capacidade, horarioLembrete);
        turnos.add(novoTurno);
        motoristaLogado.getTurnosGerenciados().add(novoTurno);
        return novoTurno;
    }

    public Turno atualizarTurno(Turno turno) {
        if (turno.getCapacidadeVagas() <= 0) {
            throw new IllegalArgumentException("A capacidade de vagas deve ser positiva.");
        }
        
        Turno existente = turnos.stream()
            .filter(t -> t.getId() == turno.getId())
            .findFirst()
            .orElse(null);
            
        if (existente != null) {
            existente.setNome(turno.getNome());
            existente.setHorario(turno.getHorario());
            existente.setCapacidadeVagas(turno.getCapacidadeVagas());
            existente.setHorarioLembrete(turno.getHorarioLembrete());
        }
        return existente;
    }

    public void deletarTurno(Turno turno) {
        if (!turno.getPassageirosAssociados().isEmpty()) {
            throw new IllegalStateException("Não é possível deletar o turno, ele ainda possui passageiros associados.");
        }
        turnos.remove(turno);
        motoristaLogado.getTurnosGerenciados().remove(turno);
    }

    public void confirmarParticipacao(Passageiro passageiro, Turno turno) {
        if (!turno.getPassageirosAssociados().contains(passageiro)) {
            throw new IllegalArgumentException("Passageiro não está associado a este turno.");
        }
        
        if (!turno.getPassageirosConfirmados().contains(passageiro)) {
            turno.getPassageirosConfirmados().add(passageiro);
            passageiro.setIsConfirmado(true);
        }
    }
    
    public void cancelarConfirmacao(Passageiro passageiro, Turno turno) {
        if (turno.getPassageirosConfirmados().contains(passageiro)) {
            turno.getPassageirosConfirmados().remove(passageiro);
            passageiro.setIsConfirmado(false);
        }
    }
    
    public void adicionarPassageiroAoTurno(Passageiro passageiro, Turno turno) {
        if (!turno.getPassageirosAssociados().contains(passageiro)) {
            turno.getPassageirosAssociados().add(passageiro);
        }
    }

    public void removerPassageiroDoTurno(Passageiro passageiro, Turno turno) {
        turno.getPassageirosAssociados().remove(passageiro);
        turno.getPassageirosConfirmados().remove(passageiro);
        passageiro.setIsConfirmado(false);
    }

    public SimulacaoCorrida simularCorrida(Turno turno) {
        ObservableList<Passageiro> confirmados = turno.getPassageirosConfirmados();
        
        if (confirmados.isEmpty()) {
            return new SimulacaoCorrida(turno, 0.0, 0, FXCollections.observableArrayList());
        }

        ObservableList<Passageiro> ordemOtimizada = confirmados.stream()
            .sorted(java.util.Comparator.comparing(Passageiro::getNome))
            .collect(Collectors.toCollection(FXCollections::observableArrayList));
            
        double distanciaKm = ordemOtimizada.size() * 3.5 + 5.0;
        int tempoMinutos = (int) (ordemOtimizada.size() * 5.0 + 10.0);

        return new SimulacaoCorrida(turno, distanciaKm, tempoMinutos, ordemOtimizada);
    }
    
    public ObservableList<Passageiro> getPassageirosNaoConfirmados(Turno turno) {
        return turno.getPassageirosAssociados().stream()
            .filter(p -> !p.isConfirmado())
            .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
}
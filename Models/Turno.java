import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Turno {
    private final IntegerProperty id;
    private final StringProperty nome;
    private final StringProperty horario;
    private final IntegerProperty capacidadeVagas;
    private final StringProperty horarioLembrete;
    
    private final ObservableList<Passageiro> passageirosAssociados;
    private final ObservableList<Passageiro> passageirosConfirmados;

    public Turno(int id, String nome, String horario, int capacidade, String horarioLembrete) {
        this.id = new SimpleIntegerProperty(id);
        this.nome = new SimpleStringProperty(nome);
        this.horario = new SimpleStringProperty(horario);
        this.capacidadeVagas = new SimpleIntegerProperty(capacidade);
        this.horarioLembrete = new SimpleStringProperty(horarioLembrete);
        this.passageirosAssociados = FXCollections.observableArrayList();
        this.passageirosConfirmados = FXCollections.observableArrayList();
    }

    public int getId() { return id.get(); }
    public String getNome() { return nome.get(); }
    public String getHorario() { return horario.get(); }
    public int getCapacidadeVagas() { return capacidadeVagas.get(); }
    public String getHorarioLembrete() { return horarioLembrete.get(); }
    public ObservableList<Passageiro> getPassageirosAssociados() { return passageirosAssociados; }
    public ObservableList<Passageiro> getPassageirosConfirmados() { return passageirosConfirmados; }

    public void setId(int id) { this.id.set(id); }
    public void setNome(String nome) { this.nome.set(nome); }
    public void setHorario(String horario) { this.horario.set(horario); }
    public void setCapacidadeVagas(int capacidade) { this.capacidadeVagas.set(capacidade); }
    public void setHorarioLembrete(String horarioLembrete) { this.horarioLembrete.set(horarioLembrete); }

    public IntegerProperty idProperty() { return id; }
    public StringProperty nomeProperty() { return nome; }
    public StringProperty horarioProperty() { return horario; }
    public IntegerProperty capacidadeVagasProperty() { return capacidadeVagas; }
    public StringProperty horarioLembreteProperty() { return horarioLembrete; }
}

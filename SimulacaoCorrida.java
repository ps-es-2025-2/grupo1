import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SimulacaoCorrida {
    private final ObjectProperty<Turno> turno;
    private final DoubleProperty distanciaTotalKm;
    private final IntegerProperty tempoEstimadoMinutos;
    private final ObservableList<Passageiro> ordemColetaOtimizada;

    public SimulacaoCorrida(Turno turno, double distancia, int tempo, ObservableList<Passageiro> ordemOtimizada) {
        this.turno = new SimpleObjectProperty<>(turno);
        this.distanciaTotalKm = new SimpleDoubleProperty(distancia);
        this.tempoEstimadoMinutos = new SimpleIntegerProperty(tempo);
        this.ordemColetaOtimizada = FXCollections.observableArrayList(ordemOtimizada);
    }

    public Turno getTurno() { return turno.get(); }
    public double getDistanciaTotalKm() { return distanciaTotalKm.get(); }
    public int getTempoEstimadoMinutos() { return tempoEstimadoMinutos.get(); }
    public ObservableList<Passageiro> getOrdemColetaOtimizada() { return ordemColetaOtimizada; }

    public void setTurno(Turno turno) { this.turno.set(turno); }
    public void setDistanciaTotalKm(double distanciaTotalKm) { this.distanciaTotalKm.set(distanciaTotalKm); }
    public void setTempoEstimadoMinutos(int tempoEstimadoMinutos) { this.tempoEstimadoMinutos.set(tempoEstimadoMinutos); }

    public ObjectProperty<Turno> turnoProperty() { return turno; }
    public DoubleProperty distanciaTotalKmProperty() { return distanciaTotalKm; }
    public IntegerProperty tempoEstimadoMinutosProperty() { return tempoEstimadoMinutos; }
}

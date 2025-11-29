package br.com.appvagasvan.presentation.viewmodel;

import br.com.appvagasvan.domain.turno.Horario;
import br.com.appvagasvan.domain.turno.TipoTurno;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class TurnoViewModel {
    private final SimpleIntegerProperty id;
    private final SimpleObjectProperty<Horario> horario;
    private final SimpleObjectProperty<TipoTurno> tipoTurno;
    private final SimpleIntegerProperty vagasDisponiveis;

    public TurnoViewModel(int id, Horario horario, TipoTurno tipoTurno, int vagasDisponiveis) {
        this.id = new SimpleIntegerProperty(id);
        this.horario = new SimpleObjectProperty<>(horario);
        this.tipoTurno = new SimpleObjectProperty<>(tipoTurno);
        this.vagasDisponiveis = new SimpleIntegerProperty(vagasDisponiveis);
    }

    public int getId() {
        return id.get();
    }

    public Horario getHorario() {
        return horario.get();
    }

    public TipoTurno getTipoTurno() {
        return tipoTurno.get();
    }

    public int getVagasDisponiveis() {
        return vagasDisponiveis.get();
    }
}
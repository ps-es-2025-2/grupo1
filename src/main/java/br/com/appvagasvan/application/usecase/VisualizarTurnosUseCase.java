package br.com.appvagasvan.application.usecase;

import br.com.appvagasvan.application.dto.ListaTurnosOutput;
import br.com.appvagasvan.application.dto.TurnoOutput;
import br.com.appvagasvan.domain.repository.TurnoRepository;
import br.com.appvagasvan.domain.turno.Turno;

import java.util.stream.Collectors;
import java.util.List;

public class VisualizarTurnosUseCase {

    private final TurnoRepository turnoRepository;

    public VisualizarTurnosUseCase(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public ListaTurnosOutput execute() {
        List<Turno> turnos = turnoRepository.buscarTodos();

        List<TurnoOutput> turnoOutputs = turnos.stream()
                .map(t -> new TurnoOutput(
                        t.getId(),
                        t.getTipoTurno(),
                        t.getHorario(),
                        t.getVagasDisponiveis(),
                        t.getPassageirosAssociados().size() // Adicionando a contagem de passageiros
                ))
                .collect(Collectors.toList());

        return new ListaTurnosOutput(turnoOutputs);
    }
}

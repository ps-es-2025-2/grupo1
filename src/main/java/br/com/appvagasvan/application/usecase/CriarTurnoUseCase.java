package br.com.appvagasvan.application.usecase;

import br.com.appvagasvan.application.dto.CriarTurnoInput;
import br.com.appvagasvan.application.dto.TurnoOutput;
import br.com.appvagasvan.domain.exception.EntidadeNaoEncontradaException;
import br.com.appvagasvan.domain.motorista.Motorista;
import br.com.appvagasvan.domain.repository.MotoristaRepository;
import br.com.appvagasvan.domain.repository.TurnoRepository;
import br.com.appvagasvan.domain.turno.Horario;
import br.com.appvagasvan.domain.turno.HorarioLembrete;
import br.com.appvagasvan.domain.turno.Turno;

public class CriarTurnoUseCase {
    private final TurnoRepository turnoRepository;
    private final MotoristaRepository motoristaRepository;

    public CriarTurnoUseCase(TurnoRepository turnoRepository, MotoristaRepository motoristaRepository) {
        this.turnoRepository = turnoRepository;
        this.motoristaRepository = motoristaRepository;
    }

    public TurnoOutput execute(CriarTurnoInput input) {
        // Gerar novo ID
        Integer id = turnoRepository.nextId();

        Motorista motorista = motoristaRepository.findById(input.getMotoristaId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Motorista com ID " + input.getMotoristaId() + " não encontrado."));

        Horario horario = Horario.of(input.getHorario());
        HorarioLembrete lembrete = input.getHorarioLembrete() != null
                ? HorarioLembrete.of(input.getHorarioLembrete())
                : null;

        // Criar turno (aplica validações de domínio)
        Turno turno = Turno.criar(id, motorista, input.getNome(), horario, input.getCapacidade(), lembrete);

        // Persistir
        turno = turnoRepository.save(turno);

        // Converter para DTO de saída
        return new TurnoOutput(
                turno.getId(),
                turno.getNome(),
                turno.getHorario().getHoraFormatada(),
                turno.getCapacidade(),
                turno.getVagasDisponiveis(),
                turno.getHorarioLembrete() != null ? turno.getHorarioLembrete().getHoraFormatada() : null,
                turno.getPassageirosAssociados().size(),
                turno.getPassageirosConfirmados().size());
    }
}
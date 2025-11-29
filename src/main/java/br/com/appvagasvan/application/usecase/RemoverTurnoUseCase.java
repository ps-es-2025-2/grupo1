package br.com.appvagasvan.application.usecase;

import br.com.appvagasvan.application.dto.RemoverTurnoInput;
import br.com.appvagasvan.domain.repository.TurnoRepository;
import br.com.appvagasvan.domain.exception.EntidadeNaoEncontradaException;

public class RemoverTurnoUseCase {

    private final TurnoRepository turnoRepository;

    public RemoverTurnoUseCase(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public void execute(RemoverTurnoInput input) {
        if (!turnoRepository.existe(input.getTurnoId())) {
            throw new EntidadeNaoEncontradaException("Turno com ID " + input.getTurnoId() + " não encontrado.");
        }
        turnoRepository.remover(input.getTurnoId());
    }
}

package br.com.appvagasvan.application.usecase;

import br.com.appvagasvan.application.dto.CancelarParticipacaoInput;
import br.com.appvagasvan.domain.exception.EntidadeNaoEncontradaException;
import br.com.appvagasvan.domain.passageiro.Passageiro;
import br.com.appvagasvan.domain.repository.PassageiroRepository;
import br.com.appvagasvan.domain.repository.TurnoRepository;
import br.com.appvagasvan.domain.turno.Turno;

public class CancelarParticipacaoUseCase {
    private final PassageiroRepository passageiroRepository;
    private final TurnoRepository turnoRepository;

    public CancelarParticipacaoUseCase(PassageiroRepository passageiroRepository,
                                       TurnoRepository turnoRepository) {
        this.passageiroRepository = passageiroRepository;
        this.turnoRepository = turnoRepository;
    }

    public void execute(CancelarParticipacaoInput input) {
        Integer passageiroId = input.getPassageiroId();
        Passageiro passageiro = passageiroRepository.findById(passageiroId)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                "Passageiro não encontrado: " + input.getPassageiroId()));

        Integer turnoId = input.getTurnoId();
        Turno turno = turnoRepository.findById(turnoId)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                "Turno não encontrado: " + input.getTurnoId()));

        turno.cancelarConfirmacao(passageiroId);
        passageiro.cancelarPresenca();

        turnoRepository.save(turno);
        passageiroRepository.save(passageiro);
    }
}

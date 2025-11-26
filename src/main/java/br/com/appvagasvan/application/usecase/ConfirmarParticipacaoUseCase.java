package br.com.appvagasvan.application.usecase;

import br.com.appvagasvan.application.dto.ConfirmarParticipacaoInput;
import br.com.appvagasvan.domain.exception.EntidadeNaoEncontradaException;
import br.com.appvagasvan.domain.passageiro.Passageiro;
import br.com.appvagasvan.domain.repository.PassageiroRepository;
import br.com.appvagasvan.domain.repository.TurnoRepository;
import br.com.appvagasvan.domain.turno.Turno;

public class ConfirmarParticipacaoUseCase {
    private final PassageiroRepository passageiroRepository;
    private final TurnoRepository turnoRepository;

    public ConfirmarParticipacaoUseCase(PassageiroRepository passageiroRepository,
                                        TurnoRepository turnoRepository) {
        this.passageiroRepository = passageiroRepository;
        this.turnoRepository = turnoRepository;
    }

    public void execute(ConfirmarParticipacaoInput input) {
        // Buscar passageiro
        Passageiro passageiro = passageiroRepository.findById(input.getPassageiroId())
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                "Passageiro não encontrado: " + input.getPassageiroId()));

        // Buscar turno
        Turno turno = turnoRepository.findById(input.getTurnoId())
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                "Turno não encontrado: " + input.getTurnoId()));

        // Confirmar participação (aplica regras de negócio)
        turno.confirmarParticipacao(input.getPassageiroId());
        passageiro.confirmarPresenca();

        // Persistir mudanças
        turnoRepository.save(turno);
        passageiroRepository.save(passageiro);

        // Eventos seriam publicados aqui em uma implementação completa
    }
}
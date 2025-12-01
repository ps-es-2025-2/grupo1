package br.com.appvagasvan.application.usecase;

import br.com.appvagasvan.application.dto.MoverPassageiroDeTurnoInput;
import br.com.appvagasvan.domain.exception.EntidadeNaoEncontradaException;
import br.com.appvagasvan.domain.repository.PassageiroRepository;
import br.com.appvagasvan.domain.repository.TurnoRepository;
import br.com.appvagasvan.domain.turno.Turno;

public class MoverPassageiroDeTurnoUseCase {
    private final TurnoRepository turnoRepository;
    private final PassageiroRepository passageiroRepository;

    public MoverPassageiroDeTurnoUseCase(TurnoRepository turnoRepository,
                                         PassageiroRepository passageiroRepository) {
        this.turnoRepository = turnoRepository;
        this.passageiroRepository = passageiroRepository;
    }

    public void execute(MoverPassageiroDeTurnoInput input) {
        if (!passageiroRepository.existe(input.getPassageiroId())) {
            throw new EntidadeNaoEncontradaException(
                "Passageiro não encontrado: " + input.getPassageiroId());
        }

        Turno turnoAntigo = turnoRepository.buscarPorId(input.getTurnoAntigoId())
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                "Turno antigo não encontrado: " + input.getTurnoAntigoId()));

        Turno turnoNovo = turnoRepository.buscarPorId(input.getTurnoNovoId())
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                "Turno novo não encontrado: " + input.getTurnoNovoId()));

        turnoAntigo.removerPassageiro(input.getPassageiroId());
        turnoNovo.adicionarPassageiro(input.getPassageiroId());
        turnoNovo.confirmarParticipacao(input.getPassageiroId());

        turnoRepository.salvar(turnoAntigo);
        turnoRepository.salvar(turnoNovo);
    }
}

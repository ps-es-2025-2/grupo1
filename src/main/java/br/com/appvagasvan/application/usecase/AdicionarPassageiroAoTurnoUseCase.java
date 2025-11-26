package br.com.appvagasvan.application.usecase;

import br.com.appvagasvan.application.dto.AdicionarPassageiroAoTurnoInput;
import br.com.appvagasvan.domain.exception.EntidadeNaoEncontradaException;
import br.com.appvagasvan.domain.repository.PassageiroRepository;
import br.com.appvagasvan.domain.repository.TurnoRepository;
import br.com.appvagasvan.domain.turno.Turno;

public class AdicionarPassageiroAoTurnoUseCase {
    private final TurnoRepository turnoRepository;
    private final PassageiroRepository passageiroRepository;

    public AdicionarPassageiroAoTurnoUseCase(TurnoRepository turnoRepository,
                                              PassageiroRepository passageiroRepository) {
        this.turnoRepository = turnoRepository;
        this.passageiroRepository = passageiroRepository;
    }

    public void execute(AdicionarPassageiroAoTurnoInput input) {
        // Verificar se passageiro existe
        if (!passageiroRepository.exists(input.getPassageiroId())) {
            throw new EntidadeNaoEncontradaException(
                "Passageiro não encontrado: " + input.getPassageiroId());
        }

        // Buscar turno
        Turno turno = turnoRepository.findById(input.getTurnoId())
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                "Turno não encontrado: " + input.getTurnoId()));

        // Adicionar passageiro (aplica regras de negócio)
        turno.adicionarPassageiro(input.getPassageiroId());

        // Persistir
        turnoRepository.save(turno);
    }
}

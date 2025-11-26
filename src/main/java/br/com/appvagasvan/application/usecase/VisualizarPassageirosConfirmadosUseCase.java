package br.com.appvagasvan.application.usecase;

import java.util.ArrayList;
import java.util.List;

import br.com.appvagasvan.application.dto.ListaPassageirosConfirmadosOutput;
import br.com.appvagasvan.application.dto.PassageiroOutput;
import br.com.appvagasvan.domain.exception.EntidadeNaoEncontradaException;
import br.com.appvagasvan.domain.passageiro.Passageiro;
import br.com.appvagasvan.domain.repository.PassageiroRepository;
import br.com.appvagasvan.domain.repository.TurnoRepository;
import br.com.appvagasvan.domain.turno.Turno;

public class VisualizarPassageirosConfirmadosUseCase {
    private final TurnoRepository turnoRepository;
    private final PassageiroRepository passageiroRepository;

    public VisualizarPassageirosConfirmadosUseCase(TurnoRepository turnoRepository,
                                                    PassageiroRepository passageiroRepository) {
        this.turnoRepository = turnoRepository;
        this.passageiroRepository = passageiroRepository;
    }

    public ListaPassageirosConfirmadosOutput execute(int turnoId) {
        Turno turno = turnoRepository.findById(turnoId)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                "Turno não encontrado: " + turnoId));

        // Buscar passageiros confirmados
        List<PassageiroOutput> passageirosOutput = new ArrayList<>();
        for (Integer passageiroId : turno.getPassageirosConfirmados()) {
            Passageiro passageiro = passageiroRepository.findById(passageiroId)
                .orElse(null);
            
            if (passageiro != null) {
                passageirosOutput.add(new PassageiroOutput(
                    passageiro.getId(),
                    passageiro.getNome(),
                    passageiro.getEnderecoColeta().getEnderecoCompleto(),
                    passageiro.getTelefone() == null ? "" : passageiro.getTelefone(),
                    passageiro.isConfirmado()
                ));
            }
        }

        return new ListaPassageirosConfirmadosOutput(
            turno.getId(),
            turno.getNome(),
            passageirosOutput
        );
    }
}

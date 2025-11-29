package br.com.appvagasvan.application.usecase;

import br.com.appvagasvan.application.dto.SimularCorridaInput;
import br.com.appvagasvan.domain.exception.DomainException;
import br.com.appvagasvan.domain.exception.EntidadeNaoEncontradaException;
import br.com.appvagasvan.domain.passageiro.Passageiro;
import br.com.appvagasvan.domain.repository.PassageiroRepository;
import br.com.appvagasvan.domain.repository.TurnoRepository;
import br.com.appvagasvan.domain.repository.ViagemRepository;
import br.com.appvagasvan.domain.services.OtimizadorRota;
import br.com.appvagasvan.domain.turno.Turno;
import br.com.appvagasvan.domain.viagem.Viagem;
import br.com.appvagasvan.application.dto.SimulacaoCorridaOutput;
import br.com.appvagasvan.application.dto.PassageiroOutput;

import java.util.ArrayList;
import java.util.List;

/**
 * Use Case: Simular Corrida
 * CU6 da documentação
 */
public class SimularCorridaUseCase {
    private final TurnoRepository turnoRepository;
    private final PassageiroRepository passageiroRepository;
    private final ViagemRepository viagemRepository;
    private final OtimizadorRota otimizadorRota;

    public SimularCorridaUseCase(TurnoRepository turnoRepository,
                                 PassageiroRepository passageiroRepository,
                                 ViagemRepository viagemRepository,
                                 OtimizadorRota otimizadorRota) {
        this.turnoRepository = turnoRepository;
        this.passageiroRepository = passageiroRepository;
        this.viagemRepository = viagemRepository;
        this.otimizadorRota = otimizadorRota;
    }

    public SimulacaoCorridaOutput execute(SimularCorridaInput input) {
        // Buscar turno
        Turno turno = turnoRepository.buscarPorId(input.getTurnoId())
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                "Turno não encontrado: " + input.getTurnoId()));

        // Verificar se há passageiros confirmados
        List<Integer> confirmados = turno.getPassageirosConfirmados();
        if (confirmados.isEmpty()) {
            throw new DomainException(
                "Não é possível simular corrida sem passageiros confirmados");
        }

        // Buscar dados completos dos passageiros
        List<Passageiro> passageiros = new ArrayList<>();
        for (Integer id : confirmados) {
            Passageiro p = passageiroRepository.buscarPorId(id)
                .orElse(null);
            if (p != null) {
                passageiros.add(p);
            }
        }

        // Otimizar rota (delega para domain service)
        var resultado = otimizadorRota.calcularRotaOtimizada(passageiros);
        
        // Criar entidade Viagem
        Integer viagemId = 1; // Seria gerado pelo repositório
        Double distancia = resultado.getDistancia();
        Integer tempo = resultado.getTempoEstimado();
        List<Integer> ordemOtimizada = resultado.getOrdemPassageiros();

        Viagem viagem = Viagem.simular(viagemId, input.getTurnoId(), ordemOtimizada, distancia, tempo);
        
        // Salvar simulação
        viagemRepository.salvar(viagem);

        // Montar output com passageiros na ordem otimizada
        List<PassageiroOutput> passageirosOutput = new ArrayList<>();
        for (Integer id : ordemOtimizada) {
            Passageiro p = passageiroRepository.buscarPorId(id).orElse(null);
            if (p != null) {
                passageirosOutput.add(new PassageiroOutput(
                    p.getId(),
                    p.getNome(),
                    p.getEnderecoColeta().toString(),
                    p.getTelefone()
                ));
            }
        }

        return new SimulacaoCorridaOutput(
            turno.getId(),
            turno.getTipoTurno(),
            distancia,
            tempo,
            tempo + " min",
            passageirosOutput
        );
    }
}
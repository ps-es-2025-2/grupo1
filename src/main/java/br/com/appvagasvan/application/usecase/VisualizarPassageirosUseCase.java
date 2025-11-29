package br.com.appvagasvan.application.usecase;

import br.com.appvagasvan.application.dto.ListaPassageirosOutput;
import br.com.appvagasvan.application.dto.PassageiroOutput;
import br.com.appvagasvan.domain.passageiro.Passageiro;
import br.com.appvagasvan.domain.repository.PassageiroRepository;

import java.util.stream.Collectors;
import java.util.List;

public class VisualizarPassageirosUseCase {

    private final PassageiroRepository passageiroRepository;

    public VisualizarPassageirosUseCase(PassageiroRepository passageiroRepository) {
        this.passageiroRepository = passageiroRepository;
    }

    public ListaPassageirosOutput execute() {
        List<Passageiro> passageiros = passageiroRepository.buscarTodos();
        
        List<PassageiroOutput> passageiroOutputs = passageiros.stream()
                .map(p -> new PassageiroOutput(
                        p.getId(),
                        p.getNome(),
                        p.getTelefone(),
                        p.getEnderecoColeta().toString()))
                .collect(Collectors.toList());

        return new ListaPassageirosOutput(passageiroOutputs);
    }
}

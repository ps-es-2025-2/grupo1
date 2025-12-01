package br.com.appvagasvan.application.usecase;

import br.com.appvagasvan.application.dto.CriarPassageiroInput;
import br.com.appvagasvan.application.dto.PassageiroOutput;
import br.com.appvagasvan.domain.exception.DomainException;
import br.com.appvagasvan.domain.passageiro.Endereco;
import br.com.appvagasvan.domain.passageiro.Passageiro;
import br.com.appvagasvan.domain.repository.PassageiroRepository;

public class CriarPassageiroUseCase {

    private final PassageiroRepository passageiroRepository;

    public CriarPassageiroUseCase(PassageiroRepository passageiroRepository) {
        this.passageiroRepository = passageiroRepository;
    }

    public PassageiroOutput execute(CriarPassageiroInput input) {

        if (input.getTelefone() != null && !input.getTelefone().trim().isEmpty()) {
            passageiroRepository.buscarPorTelefone(input.getTelefone())
                    .ifPresent(p -> {
                        throw new DomainException("Já existe um passageiro com este telefone.");
                    });
        }

        Endereco endereco = Endereco.simples(input.getEndereco());

        Passageiro passageiro = Passageiro.criar(
                passageiroRepository.proximoId(),
                input.getNome(),
                endereco,
                input.getTelefone(),
                null
        );

        passageiroRepository.salvar(passageiro);

        return new PassageiroOutput(
                passageiro.getId(),
                passageiro.getNome(),
                passageiro.getEnderecoColeta().toString(),
                passageiro.getTelefone()
        );
    }
}

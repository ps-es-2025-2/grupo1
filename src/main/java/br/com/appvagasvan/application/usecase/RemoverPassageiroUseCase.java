package br.com.appvagasvan.application.usecase;

import br.com.appvagasvan.application.dto.RemoverPassageiroInput;
import br.com.appvagasvan.domain.repository.PassageiroRepository;
import br.com.appvagasvan.domain.exception.EntidadeNaoEncontradaException;

public class RemoverPassageiroUseCase {

    private final PassageiroRepository passageiroRepository;

    public RemoverPassageiroUseCase(PassageiroRepository passageiroRepository) {
        this.passageiroRepository = passageiroRepository;
    }

    public void execute(RemoverPassageiroInput input) {
        if (!passageiroRepository.existe(input.getPassageiroId())) {
            throw new EntidadeNaoEncontradaException("Passageiro com ID " + input.getPassageiroId() + " não encontrado.");
        }
        passageiroRepository.remover(input.getPassageiroId());
    }
}

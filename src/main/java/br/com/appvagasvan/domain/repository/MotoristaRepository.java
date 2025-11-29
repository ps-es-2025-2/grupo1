package br.com.appvagasvan.domain.repository;

import br.com.appvagasvan.domain.motorista.Motorista;

import java.util.Optional;

public interface MotoristaRepository {
    Motorista salvar(Motorista motorista);
    Optional<Motorista> buscarPorId(Integer id);
}

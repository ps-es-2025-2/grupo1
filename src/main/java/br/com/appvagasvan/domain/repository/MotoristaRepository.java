package br.com.appvagasvan.domain.repository;

import br.com.appvagasvan.domain.motorista.Motorista;

import java.util.Optional;

public interface MotoristaRepository {
    Motorista save(Motorista motorista);
    Optional<Motorista> findById(Integer id);
}

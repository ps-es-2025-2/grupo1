package br.com.appvagasvan.infrastructure.repository;

import br.com.appvagasvan.domain.motorista.Motorista;
import br.com.appvagasvan.domain.repository.MotoristaRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MotoristaRepositoryImpl implements MotoristaRepository {

    private final Map<Integer, Motorista> motoristas = new HashMap<>();

    @Override
    public Motorista save(Motorista motorista) {
        motoristas.put(motorista.getId(), motorista);
        return motorista;
    }

    @Override
    public Optional<Motorista> findById(Integer id) {
        return Optional.ofNullable(motoristas.get(id));
    }
}

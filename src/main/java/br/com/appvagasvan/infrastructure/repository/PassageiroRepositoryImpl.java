package br.com.appvagasvan.infrastructure.repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import br.com.appvagasvan.domain.passageiro.Passageiro;
import br.com.appvagasvan.domain.repository.PassageiroRepository;

public class PassageiroRepositoryImpl implements PassageiroRepository {
    private final Map<Integer, Passageiro> passageiros;
    private final AtomicInteger idGenerator;

    public PassageiroRepositoryImpl() {
        this.passageiros = new ConcurrentHashMap<>();
        this.idGenerator = new AtomicInteger(1);
    }

    @Override
    public Passageiro save(Passageiro passageiro) {
        passageiros.put(passageiro.getId(), passageiro);
        return passageiro;
    }

    @Override
    public Optional<Passageiro> findById(Integer id) {
        return Optional.ofNullable(passageiros.get(id));
    }

    @Override
    public List<Passageiro> findAll() {
        return new ArrayList<>(passageiros.values());
    }

    @Override
    public List<Passageiro> findConfirmadosByTurno(Integer turnoId) {
        // Esta implementação precisa de lógica adicional ou relação com TurnoRepository
        // Por simplicidade, retorna lista vazia
        return new ArrayList<>();
    }

    @Override
    public List<Passageiro> findNaoConfirmadosByTurno(Integer turnoId) {
        return new ArrayList<>();
    }

    @Override
    public void delete(Integer id) {
        passageiros.remove(id);
    }

    @Override
    public boolean exists(Integer id) {
        return passageiros.containsKey(id);
    }

    @Override
    public Integer nextId() {
        return idGenerator.getAndIncrement();
    }
}
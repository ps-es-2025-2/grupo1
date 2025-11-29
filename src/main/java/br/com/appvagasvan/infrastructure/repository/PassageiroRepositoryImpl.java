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
    public Passageiro salvar(Passageiro passageiro) {
        passageiros.put(passageiro.getId(), passageiro);
        return passageiro;
    }

    @Override
    public Optional<Passageiro> buscarPorId(Integer id) {
        return Optional.ofNullable(passageiros.get(id));
    }

    @Override
    public List<Passageiro> buscarTodos() {
        return new ArrayList<>(passageiros.values());
    }

    @Override
    public List<Passageiro> buscarConfirmadosPorTurno(Integer turnoId) {
        // Esta implementação precisa de lógica adicional ou relação com TurnoRepository
        // Por simplicidade, retorna lista vazia
        return new ArrayList<>();
    }

    @Override
    public List<Passageiro> buscarNaoConfirmadosPorTurno(Integer turnoId) {
        return new ArrayList<>();
    }

    @Override
    public void remover(Integer id) {
        passageiros.remove(id);
    }

    @Override
    public boolean existe(Integer id) {
        return passageiros.containsKey(id);
    }

    @Override
    public Integer proximoId() {
        return idGenerator.getAndIncrement();
    }
}
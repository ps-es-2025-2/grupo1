package br.com.appvagasvan.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import br.com.appvagasvan.domain.repository.TurnoRepository;
import br.com.appvagasvan.domain.turno.Turno;

public class TurnoRepositoryImpl implements TurnoRepository {
    private final Map<Integer, Turno> turnos;
    private final Map<Integer, Integer> turnoMotorista; // Relacionamento
    private final AtomicInteger idGenerator;

    public TurnoRepositoryImpl() {
        this.turnos = new ConcurrentHashMap<>();
        this.turnoMotorista = new ConcurrentHashMap<>();
        this.idGenerator = new AtomicInteger(1);
    }

    @Override
    public Turno save(Turno turno) {
        turnos.put(turno.getId(), turno);
        return turno;
    }

    public Turno save(Turno turno, Integer motoristaId) {
        turnos.put(turno.getId(), turno);
        turnoMotorista.put(turno.getId(), motoristaId);
        return turno;
    }

    @Override
    public Optional<Turno> findById(Integer id) {
        return Optional.ofNullable(turnos.get(id));
    }

    @Override
    public List<Turno> findByMotorista(Integer motoristaId) {
        return turnoMotorista.entrySet().stream()
            .filter(entry -> entry.getValue().equals(motoristaId))
            .map(entry -> turnos.get(entry.getKey()))
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    @Override
    public List<Turno> findAll() {
        return new ArrayList<>(turnos.values());
    }

    @Override
    public void delete(Integer id) {
        turnos.remove(id);
        turnoMotorista.remove(id);
    }

    @Override
    public boolean exists(Integer id) {
        return turnos.containsKey(id);
    }

    @Override
    public Integer nextId() {
        return idGenerator.getAndIncrement();
    }
}
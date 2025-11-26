package br.com.appvagasvan.infrastructure.repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import br.com.appvagasvan.domain.repository.ViagemRepository;
import br.com.appvagasvan.domain.viagem.Viagem;

public class ViagemRepositoryImpl implements ViagemRepository {
    private final Map<Integer, Viagem> viagens;
    private final Map<Integer, List<Viagem>> viagensPorTurno;

    public ViagemRepositoryImpl() {
        this.viagens = new ConcurrentHashMap<>();
        this.viagensPorTurno = new ConcurrentHashMap<>();
    }

    @Override
    public Viagem save(Viagem viagem) {
        viagens.put(viagem.getId(), viagem);
        
        viagensPorTurno.computeIfAbsent(viagem.getTurnoId(), k -> new ArrayList<>())
            .add(viagem);
        
        return viagem;
    }

    @Override
    public List<Viagem> findByTurno(Integer turnoId) {
        return viagensPorTurno.getOrDefault(turnoId, new ArrayList<>());
    }

    @Override
    public Optional<Viagem> findUltimaSimulacaoByTurno(Integer turnoId) {
        List<Viagem> viagensDoTurno = findByTurno(turnoId);
        if (viagensDoTurno.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(viagensDoTurno.get(viagensDoTurno.size() - 1));
    }

    @Override
    public List<Viagem> findSimulacoesRecentes(int limite) {
        return viagens.values().stream()
            .sorted(Comparator.comparing(v -> v.getId(), Comparator.reverseOrder()))
            .limit(limite)
            .collect(Collectors.toList());
    }
}

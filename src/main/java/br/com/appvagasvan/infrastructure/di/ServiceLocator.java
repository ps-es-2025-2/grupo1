package br.com.appvagasvan.infrastructure.di;

import br.com.appvagasvan.application.usecase.AdicionarPassageiroAoTurnoUseCase;
import br.com.appvagasvan.application.usecase.CancelarParticipacaoUseCase;
import br.com.appvagasvan.application.usecase.ConfirmarParticipacaoUseCase;
import br.com.appvagasvan.application.usecase.CriarPassageiroUseCase;
import br.com.appvagasvan.application.usecase.CriarTurnoUseCase;
import br.com.appvagasvan.application.usecase.RemoverPassageiroUseCase;
import br.com.appvagasvan.application.usecase.RemoverTurnoUseCase;
import br.com.appvagasvan.application.usecase.SimularCorridaUseCase;
import br.com.appvagasvan.application.usecase.VisualizarPassageirosConfirmadosUseCase;
import br.com.appvagasvan.application.usecase.VisualizarPassageirosUseCase;
import br.com.appvagasvan.application.usecase.VisualizarTurnosUseCase;
import br.com.appvagasvan.domain.repository.MotoristaRepository;
import br.com.appvagasvan.domain.repository.PassageiroRepository;
import br.com.appvagasvan.domain.repository.TurnoRepository;
import br.com.appvagasvan.domain.repository.ViagemRepository;
import br.com.appvagasvan.domain.services.OtimizadorRota;
import br.com.appvagasvan.infrastructure.repository.MotoristaRepositoryImpl;
import br.com.appvagasvan.infrastructure.repository.PassageiroRepositoryImpl;
import br.com.appvagasvan.infrastructure.repository.TurnoRepositoryImpl;
import br.com.appvagasvan.infrastructure.repository.ViagemRepositoryImpl;

/**
 * Service Locator simplificado para injeção de dependências
 * Em uma aplicação maior, seria substituído por Spring/CDI/Guice
 */
public class ServiceLocator {
    
    private static ServiceLocator instance;
    
    // Repositórios
    private final PassageiroRepository passageiroRepository;
    private final TurnoRepository turnoRepository;
    private final ViagemRepository viagemRepository;
    private final MotoristaRepository motoristaRepository;
    
    // Domain Services
    private final OtimizadorRota otimizadorRota;
    
    // Use Cases
    private final ConfirmarParticipacaoUseCase confirmarParticipacaoUseCase;
    private final CancelarParticipacaoUseCase cancelarParticipacaoUseCase;
    private final CriarTurnoUseCase criarTurnoUseCase;
    private final VisualizarPassageirosConfirmadosUseCase visualizarPassageirosConfirmadosUseCase;
    private final AdicionarPassageiroAoTurnoUseCase adicionarPassageiroAoTurnoUseCase;
    private final SimularCorridaUseCase simularCorridaUseCase;
    private final CriarPassageiroUseCase criarPassageiroUseCase;
    private final VisualizarPassageirosUseCase visualizarPassageirosUseCase;
    private final RemoverPassageiroUseCase removerPassageiroUseCase;
    private final VisualizarTurnosUseCase visualizarTurnosUseCase;
    private final RemoverTurnoUseCase removerTurnoUseCase;

    private ServiceLocator() {
        // Inicializar repositórios
        this.passageiroRepository = new PassageiroRepositoryImpl();
        this.turnoRepository = new TurnoRepositoryImpl();
        this.viagemRepository = new ViagemRepositoryImpl();
        this.motoristaRepository = new MotoristaRepositoryImpl();
        
        // Inicializar domain services
        this.otimizadorRota = new OtimizadorRota();
        
        // Inicializar use cases com suas dependências
        this.confirmarParticipacaoUseCase = new ConfirmarParticipacaoUseCase(
            passageiroRepository, turnoRepository
        );
        
        this.cancelarParticipacaoUseCase = new CancelarParticipacaoUseCase(
            passageiroRepository, turnoRepository
        );
        
        this.criarTurnoUseCase = new CriarTurnoUseCase(
            turnoRepository, motoristaRepository
        );
        
        this.visualizarPassageirosConfirmadosUseCase = 
            new VisualizarPassageirosConfirmadosUseCase(
                turnoRepository, passageiroRepository
            );
        
        this.adicionarPassageiroAoTurnoUseCase = new AdicionarPassageiroAoTurnoUseCase(
            turnoRepository, passageiroRepository
        );
        
        this.simularCorridaUseCase = new SimularCorridaUseCase(
            turnoRepository, passageiroRepository, viagemRepository, otimizadorRota
        );

        this.criarPassageiroUseCase = new CriarPassageiroUseCase(passageiroRepository);
        this.visualizarPassageirosUseCase = new VisualizarPassageirosUseCase(passageiroRepository);
        this.removerPassageiroUseCase = new RemoverPassageiroUseCase(passageiroRepository);
        this.visualizarTurnosUseCase = new VisualizarTurnosUseCase(turnoRepository);
        this.removerTurnoUseCase = new RemoverTurnoUseCase(turnoRepository);
    }

    public static synchronized ServiceLocator getInstance() {
        if (instance == null) {
            instance = new ServiceLocator();
        }
        return instance;
    }

    // Getters para repositórios
    public PassageiroRepository getPassageiroRepository() {
        return passageiroRepository;
    }

    public TurnoRepository getTurnoRepository() {
        return turnoRepository;
    }

    public ViagemRepository getViagemRepository() {
        return viagemRepository;
    }

    public MotoristaRepository getMotoristaRepository() {
        return motoristaRepository;
    }

    // Getters para domain services
    public OtimizadorRota getOtimizadorRota() {
        return otimizadorRota;
    }

    // Getters para use cases
    public ConfirmarParticipacaoUseCase getConfirmarParticipacaoUseCase() {
        return confirmarParticipacaoUseCase;
    }

    public CancelarParticipacaoUseCase getCancelarParticipacaoUseCase() {
        return cancelarParticipacaoUseCase;
    }

    public CriarTurnoUseCase getCriarTurnoUseCase() {
        return criarTurnoUseCase;
    }

    public VisualizarPassageirosConfirmadosUseCase getVisualizarPassageirosConfirmadosUseCase() {
        return visualizarPassageirosConfirmadosUseCase;
    }

    public AdicionarPassageiroAoTurnoUseCase getAdicionarPassageiroAoTurnoUseCase() {
        return adicionarPassageiroAoTurnoUseCase;
    }

    public SimularCorridaUseCase getSimularCorridaUseCase() {
        return simularCorridaUseCase;
    }

    public CriarPassageiroUseCase getCriarPassageiroUseCase() {
        return criarPassageiroUseCase;
    }

    public VisualizarPassageirosUseCase getVisualizarPassageirosUseCase() {
        return visualizarPassageirosUseCase;
    }

    public RemoverPassageiroUseCase getRemoverPassageiroUseCase() {
        return removerPassageiroUseCase;
    }

    public VisualizarTurnosUseCase getVisualizarTurnosUseCase() {
        return visualizarTurnosUseCase;
    }

    public RemoverTurnoUseCase getRemoverTurnoUseCase() {
        return removerTurnoUseCase;
    }
    
    /**
     * Limpa a instância (útil para testes)
     */
    public static void reset() {
        instance = null;
    }
}
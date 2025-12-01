package br.com.appvagasvan.infrastructure.di;

import br.com.appvagasvan.application.usecase.AdicionarPassageiroAoTurnoUseCase;
import br.com.appvagasvan.application.usecase.CancelarParticipacaoUseCase;
import br.com.appvagasvan.application.usecase.ConfirmarParticipacaoUseCase;
import br.com.appvagasvan.application.usecase.CriarPassageiroUseCase;
import br.com.appvagasvan.application.usecase.CriarTurnoUseCase;
import br.com.appvagasvan.application.usecase.MoverPassageiroDeTurnoUseCase;
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
    private static final PassageiroRepository passageiroRepository;
    private static final TurnoRepository turnoRepository;
    private static final ViagemRepository viagemRepository;
    private static final MotoristaRepository motoristaRepository;
    
    // Domain Services
    private static final OtimizadorRota otimizadorRota;
    
    // Use Cases
    private static final ConfirmarParticipacaoUseCase confirmarParticipacaoUseCase;
    private static final CancelarParticipacaoUseCase cancelarParticipacaoUseCase;
    private static final CriarTurnoUseCase criarTurnoUseCase;
    private static final VisualizarPassageirosConfirmadosUseCase visualizarPassageirosConfirmadosUseCase;
    private static final AdicionarPassageiroAoTurnoUseCase adicionarPassageiroAoTurnoUseCase;
    private static final SimularCorridaUseCase simularCorridaUseCase;
    private static final CriarPassageiroUseCase criarPassageiroUseCase;
    private static final VisualizarPassageirosUseCase visualizarPassageirosUseCase;
    private static final RemoverPassageiroUseCase removerPassageiroUseCase;
    private static final VisualizarTurnosUseCase visualizarTurnosUseCase;
    private static final RemoverTurnoUseCase removerTurnoUseCase;
    private static final MoverPassageiroDeTurnoUseCase moverPassageiroDeTurnoUseCase;

    static {
        // Inicializar repositórios
        passageiroRepository = new PassageiroRepositoryImpl();
        turnoRepository = new TurnoRepositoryImpl();
        viagemRepository = new ViagemRepositoryImpl();
        motoristaRepository = new MotoristaRepositoryImpl();
        
        // Inicializar domain services
        otimizadorRota = new OtimizadorRota();
        
        // Inicializar use cases com suas dependências
        confirmarParticipacaoUseCase = new ConfirmarParticipacaoUseCase(
            passageiroRepository, turnoRepository
        );
        
        cancelarParticipacaoUseCase = new CancelarParticipacaoUseCase(
            passageiroRepository, turnoRepository
        );
        
        criarTurnoUseCase = new CriarTurnoUseCase(
            turnoRepository, motoristaRepository
        );
        
        visualizarPassageirosConfirmadosUseCase = 
            new VisualizarPassageirosConfirmadosUseCase(
                turnoRepository, passageiroRepository
            );
        
        adicionarPassageiroAoTurnoUseCase = new AdicionarPassageiroAoTurnoUseCase(
            turnoRepository, passageiroRepository
        );
        
        simularCorridaUseCase = new SimularCorridaUseCase(
            turnoRepository, passageiroRepository, viagemRepository, otimizadorRota
        );

        criarPassageiroUseCase = new CriarPassageiroUseCase(passageiroRepository);
        visualizarPassageirosUseCase = new VisualizarPassageirosUseCase(passageiroRepository);
        removerPassageiroUseCase = new RemoverPassageiroUseCase(passageiroRepository);
        visualizarTurnosUseCase = new VisualizarTurnosUseCase(turnoRepository);
        removerTurnoUseCase = new RemoverTurnoUseCase(turnoRepository);
        moverPassageiroDeTurnoUseCase = new MoverPassageiroDeTurnoUseCase(turnoRepository, passageiroRepository);
    }

    private ServiceLocator() {
        // Construtor privado para evitar instanciação
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

    public MoverPassageiroDeTurnoUseCase getMoverPassageiroDeTurnoUseCase() {
        return moverPassageiroDeTurnoUseCase;
    }
    
    /**
     * Limpa a instância (útil para testes)
     */
    public static void reset() {
        instance = null;
    }
}
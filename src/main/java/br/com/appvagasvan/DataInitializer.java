package br.com.appvagasvan;

import br.com.appvagasvan.domain.motorista.Motorista;
import br.com.appvagasvan.domain.passageiro.Endereco;
import br.com.appvagasvan.domain.passageiro.Passageiro;
import br.com.appvagasvan.domain.repository.MotoristaRepository;
import br.com.appvagasvan.domain.repository.PassageiroRepository;
import br.com.appvagasvan.domain.repository.TurnoRepository;
import br.com.appvagasvan.domain.turno.Horario;
import br.com.appvagasvan.domain.turno.HorarioLembrete;
import br.com.appvagasvan.domain.turno.TipoTurno;
import br.com.appvagasvan.domain.turno.Turno;
import br.com.appvagasvan.infrastructure.di.ServiceLocator;

/**
 * Classe utilitária para inicializar dados de teste
 */
public class DataInitializer {

    public static void initialize() {
        ServiceLocator locator = ServiceLocator.getInstance();
        
        PassageiroRepository passageiroRepo = locator.getPassageiroRepository();
        TurnoRepository turnoRepo = locator.getTurnoRepository();
        MotoristaRepository motoristaRepo = locator.getMotoristaRepository();

        Motorista motorista = Motorista.criar(1, "João da Silva");
        motoristaRepo.salvar(motorista);

        // Criar turnos
        Turno turnoManha = Turno.criar(
            1,
            motorista,
            TipoTurno.MANHA,
            Horario.of("08:00"),
            20,
            HorarioLembrete.of("07:30")
        );

        Turno turnoNoite = Turno.criar(
            2,
            motorista,
            TipoTurno.NOITE,
            Horario.of("18:00"),
            15,
            HorarioLembrete.of("17:30")
        );

        Turno turnoTarde = Turno.criar(
            3,
            motorista,
            TipoTurno.TARDE,
            Horario.of("14:00"),
            10,
            HorarioLembrete.of("13:30")
        );

        turnoRepo.salvar(turnoManha);
        turnoRepo.salvar(turnoNoite);
        turnoRepo.salvar(turnoTarde);

        // Criar passageiros
        Passageiro p1 = criarPassageiro(1, "Laura Martins", "Rua Verde, 123", "62999887766");
        Passageiro p2 = criarPassageiro(2, "Gabriel Rodrigues", "Rua Azul, 456", "62988776655");
        Passageiro p3 = criarPassageiro(3, "Tallya Jesus", "Rua Laranja, 789", "62977665544");
        Passageiro p4 = criarPassageiro(4, "Gabriel Freitas", "Rua Vermelha, 135", "62966554433");
        Passageiro p5 = criarPassageiro(5, "Léia Santos", "Rua Amarela, 579", "62955443322");
        Passageiro p6 = criarPassageiro(6, "Carlos Pereira", "Rua Roxa, 321", "62944332211");

        passageiroRepo.salvar(p1);
        passageiroRepo.salvar(p2);
        passageiroRepo.salvar(p3);
        passageiroRepo.salvar(p4);
        passageiroRepo.salvar(p5);
        passageiroRepo.salvar(p6);

        // Associar passageiros aos turnos
        turnoManha.adicionarPassageiro(p1.getId());
        turnoManha.adicionarPassageiro(p2.getId());
        turnoManha.adicionarPassageiro(p3.getId());

        turnoNoite.adicionarPassageiro(p4.getId());
        turnoNoite.adicionarPassageiro(p5.getId());

        turnoTarde.adicionarPassageiro(p6.getId());

        // Confirmar alguns passageiros
        turnoManha.confirmarParticipacao(p1.getId());
        turnoManha.confirmarParticipacao(p2.getId());
        turnoManha.confirmarParticipacao(p3.getId());
        p1.confirmarPresenca();
        p2.confirmarPresenca();
        p3.confirmarPresenca();

        turnoNoite.confirmarParticipacao(p4.getId());
        turnoNoite.confirmarParticipacao(p5.getId());
        p4.confirmarPresenca();
        p5.confirmarPresenca();

        turnoTarde.confirmarParticipacao(p6.getId());
        p6.confirmarPresenca();

        // Salvar mudanças
        turnoRepo.salvar(turnoManha);
        turnoRepo.salvar(turnoNoite);
        turnoRepo.salvar(turnoTarde);
        passageiroRepo.salvar(p1);
        passageiroRepo.salvar(p2);
        passageiroRepo.salvar(p3);
        passageiroRepo.salvar(p4);
        passageiroRepo.salvar(p5);
        passageiroRepo.salvar(p6);

        System.out.println("✓ Dados de teste inicializados com sucesso!");
        System.out.println("  - 3 turnos criados");
        System.out.println("  - 6 passageiros criados");
        System.out.println("  - Confirmações registradas");
    }

    private static Passageiro criarPassageiro(int id, String nome, String endereco, String telefone) {
        return Passageiro.criar(
            id,
            nome,
            Endereco.simples(endereco),
            telefone,
            ""
        );
    }
}
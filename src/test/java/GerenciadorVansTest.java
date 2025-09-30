
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.appvagasvan.model.*;
import br.com.appvagasvan.services.GerenciadorVans;

import static org.junit.jupiter.api.Assertions.*;

public class GerenciadorVansTest {

    private GerenciadorVans gerenciador;
    private Turno turnoManha;
    private Turno turnoNoite;

    @BeforeEach
    void setUp() {
        gerenciador = GerenciadorVans.getInstance(); 
        
        gerenciador.getTurnos().clear();
        gerenciador.getPassageiros().clear();

        turnoManha = gerenciador.criarTurno("Manhã", "06:00", 20, "05:00");
        turnoNoite = gerenciador.criarTurno("Noite", "18:00", 15, "17:00");
    }

    @Test
    void testCriarEBuscarPassageiro() {
        Passageiro p1 = gerenciador.criarPassageiro("Alice Criacao", "Rua A, 10", turnoManha);
        
        assertNotNull(p1);
        assertEquals(1, p1.getId());
        assertEquals(1, gerenciador.getPassageiros().size());
        assertTrue(turnoManha.getPassageirosAssociados().contains(p1));
    }

    @Test
    void testAtualizarPassageiro() {
        Passageiro p1 = gerenciador.criarPassageiro("Beta Antiga", "Rua Antiga", turnoManha);
        
        Passageiro passageiroAtualizado = new Passageiro(p1.getId(), "Beta Nova", "Rua Nova Otimizada", false);
        gerenciador.atualizarPassageiro(passageiroAtualizado);

        assertEquals("Beta Nova", p1.getNome());
        assertEquals("Rua Nova Otimizada", p1.getEnderecoColeta());
    }

    @Test
    void testDeletarPassageiro() {
        Passageiro p1 = gerenciador.criarPassageiro("Davi Deletavel", "Rua X", turnoManha);
        
        gerenciador.deletarPassageiro(p1);

        assertEquals(0, gerenciador.getPassageiros().size());
        assertFalse(turnoManha.getPassageirosAssociados().contains(p1));
    }

    @Test
    void testDeletarTurnoComSucesso() {
        gerenciador.deletarTurno(turnoNoite);
        assertEquals(1, gerenciador.getTurnos().size());
    }
    
    @Test
    void testDeletarTurnoComPassageirosFalha() {
        gerenciador.criarPassageiro("Passageiro Fixo", "Rua Deleção", turnoManha);
        
        assertThrows(IllegalStateException.class, () -> {
            gerenciador.deletarTurno(turnoManha);
        });
        
        assertEquals(2, gerenciador.getTurnos().size());
    }
    
    @Test
    void testCriarTurnoCapacidadeInvalidaFalha() {
        assertThrows(IllegalArgumentException.class, () -> {
            gerenciador.criarTurno("Turno Ruim", "10:00", 0, "09:00");
        });
    }

    @Test
    void testConfirmarEVisualizarParticipacao() {
        Passageiro p1 = gerenciador.criarPassageiro("Confirmado", "Rua C", turnoManha);
        Passageiro p2 = gerenciador.criarPassageiro("Nao Confirmado", "Rua D", turnoManha);

        gerenciador.confirmarParticipacao(p1, turnoManha);
        
        assertTrue(p1.isConfirmado());
        assertTrue(turnoManha.getPassageirosConfirmados().contains(p1));
        assertEquals(1, turnoManha.getPassageirosConfirmados().size());
        
        assertFalse(p2.isConfirmado());
    }
    
    @Test
    void testAdicionarRemoverPassageiroDoTurno() {
        Passageiro p1 = gerenciador.criarPassageiro("Pedro", "Rua P", null);
        
        gerenciador.adicionarPassageiroAoTurno(p1, turnoManha);
        assertTrue(turnoManha.getPassageirosAssociados().contains(p1));
        
        gerenciador.removerPassageiroDoTurno(p1, turnoManha);
        assertFalse(turnoManha.getPassageirosAssociados().contains(p1));
    }
    
    @Test
    void testSimularCorridaOtimizada() {
        Passageiro p_alice = gerenciador.criarPassageiro("Alice", "End A", turnoManha);
        Passageiro p_beto = gerenciador.criarPassageiro("Beto", "End B", turnoManha);
        Passageiro p_carlos = gerenciador.criarPassageiro("Carlos", "End C", turnoManha);
        
        gerenciador.confirmarParticipacao(p_alice, turnoManha);
        gerenciador.confirmarParticipacao(p_carlos, turnoManha);

        SimulacaoCorrida simulacao = gerenciador.simularCorrida(turnoManha);
        
        assertEquals(2, simulacao.getOrdemColetaOtimizada().size());
        
        assertEquals("Alice", simulacao.getOrdemColetaOtimizada().get(0).getNome());
        assertEquals("Carlos", simulacao.getOrdemColetaOtimizada().get(1).getNome());
        
        assertTrue(simulacao.getDistanciaTotalKm() > 0);
        assertTrue(simulacao.getTempoEstimadoMinutos() > 0);
    }
}
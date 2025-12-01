package br.com.appvagasvan.domain.turno;

import br.com.appvagasvan.domain.motorista.Motorista;
import br.com.appvagasvan.domain.events.*;
import br.com.appvagasvan.domain.exception.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Turno {
    private final Integer id;
    private final Motorista motorista;
    private TipoTurno tipoTurno;
    private Horario horario;
    private Integer capacidade;
    private HorarioLembrete horarioLembrete;
    private List<Integer> passageirosAssociados;
    private List<Integer> passageirosConfirmados;
    private List<Object> domainEvents;

    private Turno(Integer id, Motorista motorista, TipoTurno tipoTurno, Horario horario,
                  Integer capacidade, HorarioLembrete horarioLembrete) {
        this.id = validarId(id);
        this.motorista = motorista;
        this.tipoTurno = tipoTurno;
        this.horario = horario;
        this.capacidade = validarCapacidade(capacidade);
        this.horarioLembrete = horarioLembrete;
        this.passageirosAssociados = new ArrayList<>();
        this.passageirosConfirmados = new ArrayList<>();
        this.domainEvents = new ArrayList<>();
    }

    public static Turno criar(Integer id, Motorista motorista, TipoTurno tipoTurno, Horario horario,
                              Integer capacidade, HorarioLembrete horarioLembrete) {
        if (id == null || motorista == null || tipoTurno == null || horario == null || capacidade == null) {
            throw new DomainException("Dados obrigatórios do turno não podem ser nulos");
        }
        if (horarioLembrete != null && !horarioLembrete.isAntesDe(horario)) {
            throw new DomainException("Horário de lembrete deve ser anterior ao horário do turno");
        }
        Turno turno = new Turno(id, motorista, tipoTurno, horario, capacidade, horarioLembrete);
        turno.domainEvents.add(new TurnoCriadoEvent(id));
        return turno;
    }

    public static Turno reconstituir(Integer id, Motorista motorista, TipoTurno tipoTurno, Horario horario,
                                     Integer capacidade, HorarioLembrete horarioLembrete,
                                     List<Integer> associados, List<Integer> confirmados) {
        Turno turno = new Turno(id, motorista, tipoTurno, horario, capacidade, horarioLembrete);
        turno.passageirosAssociados = new ArrayList<>(associados);
        turno.passageirosConfirmados = new ArrayList<>(confirmados);
        return turno;
    }

    private Integer validarId(Integer id) {
        if (id <= 0) {
            throw new DomainException("ID do turno deve ser positivo");
        }
        return id;
    }

    private Integer validarCapacidade(Integer capacidade) {
        if (capacidade == null || capacidade <= 0) {
            throw new DomainException("Capacidade de vagas deve ser positiva");
        }
        if (capacidade > 100) {
            throw new DomainException("Capacidade de vagas não pode exceder 100");
        }
        return capacidade;
    }

    public void adicionarPassageiro(Integer passageiroId) {
        if (passageiroId == null) {
            throw new DomainException("ID do passageiro não pode ser nulo");
        }
        if (passageirosAssociados.contains(passageiroId)) {
            // Passageiro já está associado, não faz nada.
            return;
        }
        if (passageirosAssociados.size() >= capacidade) {
            domainEvents.add(new CapacidadeEsgotadaEvent(this.id));
            throw new CapacidadeEsgotadaException("Capacidade máxima do turno foi atingida para associação de passageiros");
        }
        passageirosAssociados.add(passageiroId);
        domainEvents.add(new PassageiroAdicionadoAoTurnoEvent(this.id, passageiroId));
    }

    public void removerPassageiro(Integer passageiroId) {
        if (passageiroId == null) {
            throw new DomainException("ID do passageiro não pode ser nulo");
        }
        if (!passageirosAssociados.contains(passageiroId)) {
            throw new PassageiroNaoAssociadoException("Passageiro não está associado a este turno");
        }
        passageirosAssociados.remove(passageiroId);
        passageirosConfirmados.remove(passageiroId);
        domainEvents.add(new PassageiroRemovidoDoTurnoEvent(this.id, passageiroId));
    }

    public void confirmarParticipacao(Integer passageiroId) {
        if (passageiroId == null) {
            throw new DomainException("ID do passageiro não pode ser nulo");
        }
        // A capacidade para confirmação é baseada na capacidade geral, não na associação
        // Mas a associação já deve ter garantido que o número total não excede.
        if (!passageirosAssociados.contains(passageiroId)) {
            throw new PassageiroNaoAssociadoException(
                "Passageiro não está associado a este turno. Associe o passageiro antes de confirmar.");
        }
        if (passageirosConfirmados.contains(passageiroId)) {
            // Passageiro já está confirmado, não faz nada.
            return;
        }
        if (passageirosConfirmados.size() >= capacidade) {
            domainEvents.add(new CapacidadeEsgotadaEvent(this.id));
            throw new CapacidadeEsgotadaException("Capacidade máxima do turno foi atingida para passageiros confirmados");
        }
        passageirosConfirmados.add(passageiroId);
    }

    public void cancelarConfirmacao(Integer passageiroId) {
        if (passageiroId == null) {
            throw new DomainException("ID do passageiro não pode ser nulo");
        }
        if (!passageirosConfirmados.contains(passageiroId)) {
            throw new DomainException("Passageiro não está confirmado neste turno");
        }
        passageirosConfirmados.remove(passageiroId);
    }

    public void atualizarInformacoes(TipoTurno novoTipo, Horario novoHorario,
                                     Integer novaCapacidade, HorarioLembrete novoLembrete) {
        if (novoTipo != null) {
            this.tipoTurno = novoTipo;
        }
        if (novoHorario != null) {
            this.horario = novoHorario;
        }
        if (novaCapacidade != null) {
            if (novaCapacidade < passageirosAssociados.size()) { // Changed to passageirosAssociados
                throw new DomainException(
                    "Nova capacidade não pode ser menor que o número de passageiros associados");
            }
            this.capacidade = validarCapacidade(novaCapacidade);
        }
        if (novoLembrete != null) {
            if (!novoLembrete.isAntesDe(this.horario)) {
                throw new DomainException("Horário de lembrete deve ser anterior ao horário do turno");
            }
            this.horarioLembrete = novoLembrete;
        }
    }

    public boolean podeSerExcluido() {
        return passageirosAssociados.isEmpty();
    }

    public boolean temVagasDisponiveis() {
        return passageirosAssociados.size() < capacidade; // Changed to passageirosAssociados
    }

    public Integer getVagasDisponiveis() {
        return capacidade - passageirosAssociados.size(); // Changed to passageirosAssociados
    }

    public boolean isPassageiroAssociado(Integer passageiroId) {
        return passageirosAssociados.contains(passageiroId);
    }

    public boolean isPassageiroConfirmado(Integer passageiroId) {
        return passageirosConfirmados.contains(passageiroId);
    }

    public Integer getId() {
        return id;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public TipoTurno getTipoTurno() {
        return tipoTurno;
    }

    public Horario getHorario() {
        return horario;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public HorarioLembrete getHorarioLembrete() {
        return horarioLembrete;
    }

    public List<Integer> getPassageirosAssociados() {
        return Collections.unmodifiableList(passageirosAssociados);
    }

    public List<Integer> getPassageirosConfirmados() {
        return Collections.unmodifiableList(passageirosConfirmados);
    }

    public List<Integer> getPassageirosNaoConfirmados() {
        List<Integer> naoConfirmados = new ArrayList<>(passageirosAssociados);
        naoConfirmados.removeAll(passageirosConfirmados);
        return Collections.unmodifiableList(naoConfirmados);
    }

    public List<Object> getDomainEvents() {
        return new ArrayList<>(domainEvents);
    }

    public void clearDomainEvents() {
        this.domainEvents.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turno turno = (Turno) o;
        return id.equals(turno.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

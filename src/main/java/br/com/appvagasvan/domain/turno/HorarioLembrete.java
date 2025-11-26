package br.com.appvagasvan.domain.turno;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import br.com.appvagasvan.domain.exception.DomainException;

public class HorarioLembrete {
    private final LocalTime hora;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private HorarioLembrete(LocalTime hora) {
        if (hora == null) {
            throw new DomainException("Horário de lembrete não pode ser nulo");
        }
        this.hora = hora;
    }

    public static HorarioLembrete of(String horarioStr) {
        try {
            LocalTime hora = LocalTime.parse(horarioStr, FORMATTER);
            return new HorarioLembrete(hora);
        } catch (DateTimeParseException e) {
            throw new DomainException("Formato de horário inválido. Use HH:mm (ex: 07:30)");
        }
    }

    public static HorarioLembrete of(LocalTime hora) {
        return new HorarioLembrete(hora);
    }

    public static HorarioLembrete vazio() {
        return null; // Lembrete opcional
    }

    public LocalTime getHora() {
        return hora;
    }

    public String getHoraFormatada() {
        return hora.format(FORMATTER);
    }

    public boolean isAntesDe(Horario horarioTurno) {
        return this.hora.isBefore(horarioTurno.getHora());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HorarioLembrete that = (HorarioLembrete) o;
        return hora.equals(that.hora);
    }

    @Override
    public int hashCode() {
        return hora.hashCode();
    }

    @Override
    public String toString() {
        return getHoraFormatada();
    }
}
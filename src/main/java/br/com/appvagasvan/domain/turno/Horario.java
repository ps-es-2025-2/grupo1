package br.com.appvagasvan.domain.turno;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import br.com.appvagasvan.domain.exception.DomainException;

public class Horario {
    private final LocalTime hora;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private Horario(LocalTime hora) {
        if (hora == null) {
            throw new DomainException("Horário não pode ser nulo");
        }
        this.hora = hora;
    }

    public static Horario of(String horarioStr) {
        try {
            LocalTime hora = LocalTime.parse(horarioStr, FORMATTER);
            return new Horario(hora);
        } catch (DateTimeParseException e) {
            throw new DomainException("Formato de horário inválido. Use HH:mm (ex: 08:00)");
        }
    }

    public static Horario of(LocalTime hora) {
        return new Horario(hora);
    }

    public LocalTime getHora() {
        return hora;
    }

    public String getHoraFormatada() {
        return hora.format(FORMATTER);
    }

    public boolean isAntesDe(Horario outro) {
        return this.hora.isBefore(outro.hora);
    }

    public boolean isDepoisDe(Horario outro) {
        return this.hora.isAfter(outro.hora);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Horario horario = (Horario) o;
        return hora.equals(horario.hora);
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
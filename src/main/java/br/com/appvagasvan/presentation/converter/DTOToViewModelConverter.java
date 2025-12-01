package br.com.appvagasvan.presentation.converter;

import br.com.appvagasvan.application.dto.*;
import br.com.appvagasvan.presentation.viewmodel.*;
import javafx.collections.FXCollections;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Conversor de DTOs da camada de aplicação para ViewModels da camada de apresentação
 * Responsável por adaptar os dados para uso com JavaFX Properties
 */
public class DTOToViewModelConverter {

    /**
     * Converte PassageiroOutput para PassageiroViewModel
     */
    public static PassageiroViewModel toViewModel(PassageiroOutput dto) {
        return new PassageiroViewModel(
            dto.getId(),
            dto.getNome(),
            dto.getEndereco(),
            dto.getTelefone()
        );
    }

    /**
     * Converte lista de PassageiroOutput para lista de PassageiroViewModel
     */
    public static List<PassageiroViewModel> toViewModelList(List<PassageiroOutput> dtos) {
        return dtos.stream()
            .map(DTOToViewModelConverter::toViewModel)
            .collect(Collectors.toList());
    }

    /**
     * Converte TurnoOutput para TurnoViewModel
     */
    public static TurnoViewModel toViewModel(TurnoOutput dto) {
        return new TurnoViewModel(
            dto.getId(),
            dto.getHorario(),
            dto.getTipoTurno(),
            dto.getVagasDisponiveis(),
            dto.getPassageirosCount() // Assumindo que TurnoOutput tem este método
        );
    }

    /**
     * Converte SimulacaoCorridaOutput para SimulacaoViewModel
     */
    public static SimulacaoViewModel toViewModel(SimulacaoCorridaOutput dto) {
        SimulacaoViewModel viewModel = new SimulacaoViewModel();
        
        viewModel.setNomeTurno(dto.getTipoTurno().toString());
        viewModel.setDistanciaKm(dto.getDistanciaKm());
        viewModel.setTempoFormatado(dto.getTempoFormatado());
        
        var passageirosVM = FXCollections.observableArrayList(
            toViewModelList(dto.getOrdemColeta())
        );
        viewModel.setOrdemColeta(passageirosVM);
        
        return viewModel;
    }

    /**
     * Converte ListaPassageirosConfirmadosOutput para lista de ViewModels
     */
    public static List<PassageiroViewModel> fromListaConfirmados(
            ListaPassageirosConfirmadosOutput dto) {
        return toViewModelList(dto.getPassageirosConfirmados());
    }
}
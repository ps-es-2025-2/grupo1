package br.com.appvagasvan.domain.passageiro;

import br.com.appvagasvan.domain.events.PassageiroCancelouPresencaEvent;
import br.com.appvagasvan.domain.events.PassageiroConfirmouPresencaEvent;
import br.com.appvagasvan.domain.exception.DomainException;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidade de domínio Passageiro - Aggregate Root
 * Representa um passageiro que utiliza o serviço de van
 */
public class Passageiro {
    private final Integer id;
    private String nome;
    private Endereco enderecoColeta;
    private String telefone;
    private String deviceToken;
    private boolean confirmado;
    private List<Object> domainEvents;

    // Construtor privado - usar factory method
    private Passageiro(Integer id, String nome, Endereco enderecoColeta, 
                       String telefone, String deviceToken) {
        this.id = validarId(id);
        this.nome = validarNome(nome);
        this.enderecoColeta = enderecoColeta;
        this.telefone = validarTelefone(telefone);
        this.deviceToken = deviceToken;
        this.confirmado = false;
        this.domainEvents = new ArrayList<>();
    }

    // Factory method
    public static Passageiro criar(Integer id, String nome, Endereco enderecoColeta,
                                   String telefone, String deviceToken) {
        if (id == null || nome == null || enderecoColeta == null) {
            throw new DomainException("Dados obrigatórios do passageiro não podem ser nulos");
        }
        return new Passageiro(id, nome, enderecoColeta, telefone, deviceToken);
    }

    // Reconstituir passageiro do repositório
    public static Passageiro reconstituir(Integer id, String nome, Endereco enderecoColeta,
                                          String telefone, String deviceToken, 
                                          boolean status) {
        Passageiro passageiro = new Passageiro(id, nome, enderecoColeta, telefone, deviceToken);
        passageiro.confirmado = status;
        return passageiro;
    }

    // Métodos de validação
    private Integer validarId(Integer id) {
        if (id <= 0) {
            throw new DomainException("ID do passageiro deve ser positivo");
        }
        return id;
    }

    private String validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new DomainException("Nome não pode ser vazio");
        }
        if (nome.trim().length() < 3) {
            throw new DomainException("Nome deve ter pelo menos 3 caracteres");
        }
        if (nome.length() > 100) {
            throw new DomainException("Nome não pode ter mais de 100 caracteres");
        }
        return nome.trim();
    }

    private String validarTelefone(String telefone) {
        if (telefone != null && !telefone.trim().isEmpty()) {
            String numeroLimpo = telefone.replaceAll("[^0-9]", "");
            if (numeroLimpo.length() > 0 && (numeroLimpo.length() < 10 || numeroLimpo.length() > 11)) {
                throw new DomainException("Telefone inválido. Se fornecido, o número deve ter 10 ou 11 dígitos. Ex: (XX) XXXXX-XXXX");
            }
            if (numeroLimpo.isEmpty()) {
                return null;
            }
            return numeroLimpo;
        }
        return null;
    }

    // Métodos de negócio
    public void confirmarPresenca() {
        if (this.confirmado) {
            throw new DomainException("Passageiro já está confirmado");
        }
        
        this.confirmado = true;
        this.domainEvents.add(new PassageiroConfirmouPresencaEvent(this.id));
    }

    public void cancelarPresenca() {
        if (!this.confirmado) {
            throw new DomainException("Passageiro não está confirmado");
        }
        
        this.confirmado = false;
        this.domainEvents.add(new PassageiroCancelouPresencaEvent(this.id));
    }

    public void atualizarDados(String novoNome, Endereco novoEndereco, String novoTelefone) {
        if (novoNome != null) {
            this.nome = validarNome(novoNome);
        }
        if (novoEndereco != null) {
            this.enderecoColeta = novoEndereco;
        }
        if (novoTelefone != null) {
            this.telefone = validarTelefone(novoTelefone);
        }
    }

    public void atualizarDeviceToken(String novoToken) {
        this.deviceToken = novoToken;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEnderecoColeta() {
        return enderecoColeta;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public boolean isConfirmado() {
        return confirmado;
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
        Passageiro that = (Passageiro) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
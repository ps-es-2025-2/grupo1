package br.com.appvagasvan.domain.passageiro;

import br.com.appvagasvan.domain.exception.DomainException;

public class Endereco {
    private final String logradouro;
    private final String numero;
    private final String complemento;
    private final String bairro;
    private final String cidade;
    private final String estado;
    private final String cep;

    private Endereco(String logradouro, String numero, String complemento,
                     String bairro, String cidade, String estado, String cep) {
        if (logradouro == null || logradouro.trim().isEmpty()) {
            throw new DomainException("Logradouro não pode ser vazio");
        }
        if (cidade == null || cidade.trim().isEmpty()) {
            throw new DomainException("Cidade não pode ser vazia");
        }
        
        this.logradouro = logradouro.trim();
        this.numero = numero != null ? numero.trim() : "";
        this.complemento = complemento != null ? complemento.trim() : "";
        this.bairro = bairro != null ? bairro.trim() : "";
        this.cidade = cidade.trim();
        this.estado = estado != null ? estado.trim() : "";
        this.cep = cep != null ? cep.trim() : "";
    }

    public static Endereco of(String logradouro, String numero, String complemento,
                              String bairro, String cidade, String estado, String cep) {
        return new Endereco(logradouro, numero, complemento, bairro, cidade, estado, cep);
    }

    public static Endereco simples(String enderecoCompleto) {
        if (enderecoCompleto == null || enderecoCompleto.trim().isEmpty()) {
            throw new DomainException("Endereço não pode ser vazio");
        }
        return new Endereco(enderecoCompleto, "", "", "", "Não especificada", "", "");
    }

    public String getEnderecoCompleto() {
        StringBuilder sb = new StringBuilder(logradouro);
        if (!numero.isEmpty()) sb.append(", ").append(numero);
        if (!complemento.isEmpty()) sb.append(" - ").append(complemento);
        if (!bairro.isEmpty()) sb.append(" - ").append(bairro);
        sb.append(" - ").append(cidade);
        if (!estado.isEmpty()) sb.append("/").append(estado);
        if (!cep.isEmpty()) sb.append(" - CEP: ").append(cep);
        return sb.toString();
    }

    // Getters individuais
    public String getLogradouro() { return logradouro; }
    public String getNumero() { return numero; }
    public String getComplemento() { return complemento; }
    public String getBairro() { return bairro; }
    public String getCidade() { return cidade; }
    public String getEstado() { return estado; }
    public String getCep() { return cep; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return logradouro.equals(endereco.logradouro) &&
               numero.equals(endereco.numero) &&
               cidade.equals(endereco.cidade);
    }

    @Override
    public int hashCode() {
        return logradouro.hashCode() + numero.hashCode() + cidade.hashCode();
    }

    @Override
    public String toString() {
        return getEnderecoCompleto();
    }
}

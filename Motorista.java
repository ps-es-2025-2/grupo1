import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Motorista {
    private final IntegerProperty id;
    private final StringProperty nome;
    private final StringProperty enderecoOrigem;
    private final ObservableList<Turno> turnosGerenciados;

    public Motorista(int id, String nome, String enderecoOrigem) {
        this.id = new SimpleIntegerProperty(id);
        this.nome = new SimpleStringProperty(nome);
        this.enderecoOrigem = new SimpleStringProperty(enderecoOrigem);
        this.turnosGerenciados = FXCollections.observableArrayList();
    }

    public int getId() { return id.get(); }
    public String getNome() { return nome.get(); }
    public String getEnderecoOrigem() { return enderecoOrigem.get(); }
    public ObservableList<Turno> getTurnosGerenciados() { return turnosGerenciados; }

    public void setId(int id) { this.id.set(id); }
    public void setNome(String nome) { this.nome.set(nome); }
    public void setEnderecoOrigem(String enderecoOrigem) { this.enderecoOrigem.set(enderecoOrigem); }

    public IntegerProperty idProperty() { return id; }
    public StringProperty nomeProperty() { return nome; }
    public StringProperty enderecoOrigemProperty() { return enderecoOrigem; }
}

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Passageiro {
    private final IntegerProperty id;
    private final StringProperty nome;
    private final StringProperty enderecoColeta;
    private final BooleanProperty isConfirmado;

    public Passageiro(int id, String nome, String enderecoColeta, boolean isConfirmado) {
        this.id = new SimpleIntegerProperty(id);
        this.nome = new SimpleStringProperty(nome);
        this.enderecoColeta = new SimpleStringProperty(enderecoColeta);
        this.isConfirmado = new SimpleBooleanProperty(isConfirmado);
    }

    public int getId() { 
        return id.get(); }
    public String getNome() { return nome.get(); }
    public String getEnderecoColeta() { return enderecoColeta.get(); }
    public boolean isConfirmado() { return isConfirmado.get(); }

    public void setId(int id) { this.id.set(id); }
    public void setNome(String nome) { this.nome.set(nome); }
    public void setEnderecoColeta(String enderecoColeta) { this.enderecoColeta.set(enderecoColeta); }
    public void setIsConfirmado(boolean isConfirmado) { this.isConfirmado.set(isConfirmado); }

    public IntegerProperty idProperty() { return id; }
    public StringProperty nomeProperty() { return nome; }
    public StringProperty enderecoColetaProperty() { return enderecoColeta; }
    public BooleanProperty isConfirmadoProperty() { return isConfirmado; }
}

package model;

/**
 *
 * @author André Schwerz
 */
public class Piloto {

    private int id;
    private String nome;
    private Equipe equipeAtual;
    private Pais pais;
    private boolean status;

    public Equipe getEquipeAtual() {
        return equipeAtual;
    }

    public void setEquipeAtual(Equipe equipeAtual) {
        this.equipeAtual = equipeAtual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

package model;

public class Paciente {

    private String nome;
    private String especialidade;
    private boolean prioritario;
    private int numero;
    private String estado;

    // Construtor atualizado
    public Paciente(String nome, String especialidade, boolean prioritario, int numero) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.prioritario = prioritario;
        this.numero = numero;
        this.estado = "Em Espera";  // Inicializa o estado como "Em Espera"
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public boolean isPrioritario() {
        return prioritario;
    }

    public int getNumero() {
        return numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Número " + numero + " - " + nome + ", Especialidade: " + especialidade + ", " + (prioritario ? "Prioritário" : "Normal") + ", Estado: " + estado;
    }
}

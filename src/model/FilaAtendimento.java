package model;

import java.util.LinkedList;
import java.util.Queue;

public class FilaAtendimento {

    private String especialidade;
    private Queue<Paciente> pacientes;

    public FilaAtendimento(String especialidade) {
        this.especialidade = especialidade;
        this.pacientes = new LinkedList<>();
    }

    // Método para adicionar um paciente à fila
    public void adicionarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    // Método para remover o próximo paciente da fila
    public Paciente atenderProximo() {
        return pacientes.poll(); // Remove o próximo paciente da fila e o retorna
    }

    // Verifica se a fila está vazia
    public boolean isEmpty() {
        return pacientes.isEmpty();
    }

    // Método para obter o nome da especialidade
    public String getEspecialidade() {
        return especialidade;
    }

    // Retorna a fila de pacientes
    public Queue<Paciente> getPacientes() {
        return pacientes;
    }

    // Método para contar o número de pacientes na fila
    public int contarPacientes() {
        return pacientes.size(); // Usando o método size() da Queue
    }


}

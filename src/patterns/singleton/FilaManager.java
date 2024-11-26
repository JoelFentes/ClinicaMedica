package patterns.singleton;

import model.FilaAtendimento;
import model.Paciente;

import java.util.HashMap;
import java.util.Map;

public class FilaManager {

    private static FilaManager instancia;
    private Map<String, FilaAtendimento> filas;

    // Construtor privado para Singleton
    private FilaManager() {
        this.filas = new HashMap<>();
    }

    // Método para obter a instância única
    public static FilaManager getInstancia() {
        if (instancia == null) {
            instancia = new FilaManager();
        }
        return instancia;
    }

    // Método para adicionar um paciente à fila de uma especialidade
    public void adicionarPaciente(String especialidade, Paciente paciente) {
        filas.putIfAbsent(especialidade, new FilaAtendimento(especialidade));
        filas.get(especialidade).adicionarPaciente(paciente);
    }

    // Método para remover o próximo paciente da fila de uma especialidade
    public Paciente removerPaciente(String especialidade) {
        FilaAtendimento fila = filas.get(especialidade);
        if (fila != null && !fila.isEmpty()) {
            return fila.atenderProximo(); // Chama o método da fila para remover o paciente
        }
        return null; // Retorna null se a fila estiver vazia
    }

    // Método para obter todas as filas (precisamos disso para exibir todas as filas)
    public Map<String, FilaAtendimento> getFilas() {
        return filas;
    }
}

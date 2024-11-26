package view;

import model.FilaAtendimento;
import model.Paciente;

public class FilaAtendimentoView {

    // Método para exibir os pacientes na fila de uma especialidade
    public static void mostrarFila(FilaAtendimento fila) {
        System.out.println("Fila da especialidade: " + fila.getEspecialidade());

        // Enquanto houver pacientes na fila, atenda-os um a um
        while (!fila.isEmpty()) {
            Paciente paciente = fila.atenderProximo(); // Chama o próximo paciente
            if (paciente != null) {
                System.out.println("Chamando paciente: " + paciente.getNumero()
                        + " - " + paciente.getNome()
                        + " (" + (paciente.isPrioritario() ? "Prioritário" : "Normal") + ")");
            }
        }
    }
}

package patterns.singleton;

import patterns.observer.FilaNotifier;
import model.Paciente;
import java.util.Random;

public class AtendimentoSimulator {
    private FilaManager filaManager;
    private FilaNotifier filaNotifier;
    private Random random;

    public AtendimentoSimulator(FilaManager filaManager, FilaNotifier filaNotifier) {
        this.filaManager = filaManager;
        this.filaNotifier = filaNotifier;
        this.random = new Random();
    }

    public void iniciarAtendimento() {
        boolean filasVazias = false;
        while (!filasVazias) {
            filasVazias = true;  // Assume que todas as filas estão vazias

            for (String especialidade : filaManager.getFilas().keySet()) {
                Paciente paciente = filaManager.removerPaciente(especialidade);

                if (paciente != null) {
                    filasVazias = false;  // Pelo menos uma fila ainda tem pacientes
                    System.out.println("Atendendo paciente " + paciente.getNumero() + ": "
                            + paciente.getNome() + " - " + paciente.getEspecialidade()
                            + " (" + (paciente.isPrioritario() ? "Prioritário" : "Normal") + ")");

                    // Exibe o estado do próximo paciente antes do atendimento
                    Paciente proximoPaciente = filaManager.getFilas().get(especialidade).getPacientes().peek();
                    if (proximoPaciente != null) {
                        System.out.println("Próximo paciente na fila: " + proximoPaciente.getNumero() + " - "
                                + proximoPaciente.getNome() + " (" + (proximoPaciente.isPrioritario() ? "Prioritário" : "Normal") + ") - Em Espera");
                    } else {
                        System.out.println("Não há mais pacientes na fila da especialidade " + especialidade);
                    }

                    // Notifica os atendentes da especialidade do paciente
                    filaNotifier.notificarObservadores("Paciente " + paciente.getNome() + " atendido.");

                    // Pausar para o próximo atendimento
                    int tempoDeEspera = 2000 + (random.nextInt(3) * 2000);
                    try {
                        Thread.sleep(tempoDeEspera); // Pausa no atendimento por um tempo aleatório
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }

            // Exibe o estado das filas após o atendimento
            System.out.println("\nEstado das filas após o atendimento:");
            filaManager.getFilas().forEach((especialidade, fila) -> {
                System.out.println("Fila da especialidade " + especialidade + ": "
                        + fila.getPacientes().size() + " pacientes restantes. \n");
            });
        }

        System.out.println("Não há mais pacientes nas filas. Atendimento concluído.");
    }
}

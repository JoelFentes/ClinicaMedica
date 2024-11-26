import model.Paciente;
import model.Especialidade;
import patterns.singleton.AtendimentoSimulator;
import patterns.singleton.FilaManager;
import patterns.observer.FilaNotifier;
import patterns.observer.Atendente;
import view.FilaAtendimentoView;
import view.PacienteView;
import repository.PacienteRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        FilaManager filaManager = FilaManager.getInstancia();
        FilaNotifier filaNotifier = new FilaNotifier();
        AtendimentoSimulator simulator = new AtendimentoSimulator(filaManager, filaNotifier);

        // Criar e registrar um único atendente
        Atendente atendente = new Atendente("Carlos", "Todas as especialidades");
        filaNotifier.adicionarObservador(atendente);

        // Carregar os pacientes já registrados no arquivo
        List<Paciente> pacientesCarregados = PacienteRepository.carregarPacientes();
        for (Paciente paciente : pacientesCarregados) {
            Especialidade especialidade = Especialidade.fromString(paciente.getEspecialidade());
            filaManager.adicionarPaciente(especialidade.name(), paciente);
        }

        // Lista de especialidades para alternar
        List<Especialidade> especialidades = Arrays.asList(
                Especialidade.CARDIOLOGIA,
                Especialidade.PEDIATRIA,
                Especialidade.ORTOPEDIA,
                Especialidade.DERMATOLOGIA
        );

        // Exibir o menu de opções
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            // Exibir o menu
            System.out.println("\n----- Bem-vindo ao Sistema Progamed -----");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Iniciar atendimento de pacientes");
            System.out.println("2 - Verificar status das filas de atendimento");
            System.out.println("3 - Adicionar paciente à fila");
            System.out.println("0 - Sair");
            System.out.println("----------------------------------------");

            int escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (escolha) {
                case 1:
                    // Iniciar o atendimento (simulando chamando pacientes)
                    System.out.println("\nIniciando atendimento de pacientes...\n");
                    simulator.iniciarAtendimento();
                    break;
                case 2:
                    // Exibir todas as filas de atendimento
                    System.out.println("\nExibindo status das filas de atendimento...\n");
                    filaManager.getFilas().forEach((especialidade, fila) -> {
                        System.out.println("Atendimento da especialidade: " + especialidade + "\n");
                        FilaAtendimentoView.mostrarFila(fila);
                    });
                    break;
                case 3:
                    // Captura os dados do paciente e adiciona à fila
                    System.out.println("\nAdicionando um novo paciente...\n");
                    Especialidade especialidadeEscolhida = especialidades.get((int) (Math.random() * especialidades.size()));  // Escolher aleatoriamente uma especialidade
                    Paciente paciente = PacienteView.capturarPaciente();
                    paciente.setEspecialidade(especialidadeEscolhida.name());

                    // Adiciona o paciente na fila do gerenciador
                    filaManager.adicionarPaciente(especialidadeEscolhida.name(), paciente);
                    System.out.println("Paciente registrado: " + paciente + "\n");

                    // Notificar os atendentes sobre o paciente
                    filaNotifier.notificarObservadores("Paciente " + paciente.getNome() + " foi adicionado à fila de " + especialidadeEscolhida.name());

                    // Salvar o paciente no arquivo
                    pacientesCarregados.add(paciente);
                    PacienteRepository.salvarTodosPacientes(pacientesCarregados);
                    break;
                case 0:
                    continuar = false;
                    System.out.println("\nSistema encerrado.\n");
                    break;
                default:
                    System.out.println("\nOpção inválida! Tente novamente.\n");
                    break;
            }
        }

        scanner.close();
    }
}

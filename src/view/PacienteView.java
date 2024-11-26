package view;

import model.Paciente;
import model.Especialidade;

import java.util.Scanner;

public class PacienteView {

    private static int numeroPaciente = 1;  // Número inicial do paciente

    public static Paciente capturarPaciente() {
        Scanner scanner = new Scanner(System.in);

        // Captura do nome do paciente
        System.out.print("Nome do paciente: ");
        String nome = scanner.nextLine();

        // Validação da especialidade
        String especialidade = null;
        boolean especialidadeValida = false;
        while (!especialidadeValida) {
            System.out.print("Especialidade (Cardiologia, Pediatria, Ortopedia, Dermatologia): ");
            especialidade = scanner.nextLine();
            try {
                Especialidade.fromString(especialidade); // Valida se a especialidade existe.
                especialidadeValida = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Especialidade inválida! Tente novamente.");
            }
        }

        // Captura de se o paciente é prioritário
        System.out.print("Prioritário? (S/N) ");
        boolean prioritario = scanner.nextLine().equalsIgnoreCase("s");

        // Criação do paciente
        Paciente paciente = new Paciente(nome, especialidade, prioritario, numeroPaciente);

        numeroPaciente++;

        return paciente;
    }
}

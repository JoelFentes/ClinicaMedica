package repository;

import model.Paciente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteRepository {

    private static final String ARQUIVO_PACIENTES = "pacientes.txt";

    // Método para carregar pacientes do arquivo
    public static List<Paciente> carregarPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_PACIENTES))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 4) {
                    // Parse dos dados do paciente
                    String nome = dados[1];
                    String especialidade = dados[2];
                    boolean prioritario = dados[3].equalsIgnoreCase("sim");
                    int numero = Integer.parseInt(dados[0]);
                    Paciente paciente = new Paciente(nome, especialidade, prioritario, numero);
                    pacientes.add(paciente);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar pacientes: " + e.getMessage());
        }
        return pacientes;
    }

    // Método para salvar todos os pacientes no arquivo
    public static void salvarTodosPacientes(List<Paciente> pacientes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_PACIENTES))) {
            for (Paciente paciente : pacientes) {
                writer.write(paciente.getNumero() + "," + paciente.getNome() + "," + paciente.getEspecialidade() + "," + (paciente.isPrioritario() ? "sim" : "não") + "\n");
            }
            System.out.println("Pacientes salvos no arquivo.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar pacientes: " + e.getMessage());
        }
    }
}

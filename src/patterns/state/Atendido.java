package patterns.state;

import model.Paciente;

public class Atendido implements EstadoAtendimento {
    @Override
    public void processarAtendimento(Paciente paciente) {
        System.out.println("Paciente " + paciente.getNome() + " foi atendido.");
    }
}

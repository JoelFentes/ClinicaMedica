package patterns.state;

import model.Paciente;

public class Finalizado implements EstadoAtendimento {
    @Override
    public void processarAtendimento(Paciente paciente) {
        System.out.println("Paciente " + paciente.getNome() + " teve o atendimento finalizado.");
    }
}

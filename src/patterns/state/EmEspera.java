package patterns.state;

import model.Paciente;

public class EmEspera implements EstadoAtendimento {
    @Override
    public void processarAtendimento(Paciente paciente) {
        System.out.println("Paciente " + paciente.getNome() + " está em espera.");
    }
}

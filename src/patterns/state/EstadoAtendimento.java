package patterns.state;

import model.Paciente;

public interface EstadoAtendimento {
    void processarAtendimento(Paciente paciente);
}

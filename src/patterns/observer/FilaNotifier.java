package patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class FilaNotifier {

    // Lista de observadores (Atendente)
    private List<NotificacaoObserver> observers = new ArrayList<>();

    // Adiciona um observador (Atendente)
    public void adicionarObservador(NotificacaoObserver observer) {
        observers.add(observer);
    }

    // Remove um observador
    public void removerObservador(NotificacaoObserver observer) {
        observers.remove(observer);
    }

    // Notifica os observadores
    public void notificarObservadores(String mensagem) {
        for (NotificacaoObserver observer : observers) {
            observer.notificar(mensagem);
        }
    }
}

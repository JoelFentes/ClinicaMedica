package patterns.observer;

public class Atendente implements NotificacaoObserver {
    private String nome;
    private String especialidade;

    public Atendente(String nome, String especialidade) {
        this.nome = nome;
        this.especialidade = especialidade;
    }

    @Override
    public void notificar(String mensagem) {
        // Imprime a mensagem de notificação com o guichê
        System.out.println("Atendente " + nome + " (" + especialidade + ") recebeu a notificação: " + mensagem);

        String[] guiches = {"Guichê 1", "Guichê 2", "Guichê 3", "Guichê 4"};
        int guicheIndex = (int) (Math.random() * guiches.length);  // Atribuindo aleatoriamente um guichê
        System.out.println("Paciente será direcionado para: " + guiches[guicheIndex] + "\n");
    }

    public String getEspecialidade() {
        return especialidade;
    }
}

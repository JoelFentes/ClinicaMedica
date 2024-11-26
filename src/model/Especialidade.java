package model;

public enum Especialidade {
    CARDIOLOGIA,
    PEDIATRIA,
    ORTOPEDIA,
    DERMATOLOGIA;

    public static Especialidade fromString(String value) {
        for (Especialidade esp : Especialidade.values()) {
            if (esp.name().equalsIgnoreCase(value)) {
                return esp;
            }
        }
        throw new IllegalArgumentException("Especialidade inválida: " + value);
    }
}

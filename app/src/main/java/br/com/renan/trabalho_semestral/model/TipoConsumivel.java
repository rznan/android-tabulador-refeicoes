package br.com.renan.trabalho_semestral.model;

/**
 * @author: renan santos carvalho
 */
public enum TipoConsumivel {
    ALIMENTO(0),
    BEBIDA(1);

    private final int value;

    TipoConsumivel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

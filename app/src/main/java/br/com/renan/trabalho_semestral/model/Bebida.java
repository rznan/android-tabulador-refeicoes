package br.com.renan.trabalho_semestral.model;

import androidx.annotation.NonNull;

/**
 *@author: renan santos carvalho
 */
public class Bebida extends Consumivel{
    int volume;
    int acucares;

    public Bebida() {
        super();
    }

    public Bebida(int id, int calorias, String nome, int volume, int acucares) {
        super(id, calorias, nome, TipoConsumivel.BEBIDA);
        this.volume = volume;
        this.acucares = acucares;
    }

    @Override
    public String calcularNutricaoCompleta(int quant) {
        return    id + ", "
                + nome + ": "
                + calorias * quant + "kcal, "
                + volume * quant + "mL, "
                + acucares * quant + "g (sugar)";
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getAcucares() {
        return acucares;
    }

    public void setAcucares(int acucares) {
        this.acucares = acucares;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString() + ", " + volume + "mL, " + acucares + "g (sugar)";
    }
}

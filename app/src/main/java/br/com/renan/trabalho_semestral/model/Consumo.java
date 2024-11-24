package br.com.renan.trabalho_semestral.model;

import androidx.annotation.NonNull;

/**
 *@author: renan santos carvalho
 */
public class Consumo {
    Consumivel item;
    int quant;

    public Consumo(Consumivel item, int quant) {
        this.item = item;
        this.quant = quant;
    }

    public Consumivel getItem() {
        return item;
    }

    public void setItem(Consumivel item) {
        this.item = item;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public String resumoTotal() {
        return "X" + quant + " > " + item.calcularNutricaoCompleta(quant);
    }

    @NonNull
    @Override
    public String toString() {
        return "X" + quant + " - " + item.calcularNutricao(quant);
    }
}

package br.com.renan.trabalho_semestral.model;

import androidx.annotation.NonNull;

public class Alimento extends Consumivel {
    int proteinas;
    int carboidratos;
    int gorduras;

    public Alimento() {
    }

    public Alimento(int id, int calorias, String nome, int proteinas, int carboidratos, int gorduras) {
        super(id, calorias, nome, 0);
        this.proteinas = proteinas;
        this.carboidratos = carboidratos;
        this.gorduras = gorduras;
    }

    @Override
    public String calcularNutricaoCompleta(int quant) {
        return    id + ", "
                + nome + ": "
                + calorias * quant + "kcal, "
                + proteinas * quant + "g (prot), "
                + carboidratos * quant + "g (carb), "
                + gorduras * quant + "g (fat)";
    }

    public int getProteinas() {
        return proteinas;
    }

    public void setProteinas(int proteinas) {
        this.proteinas = proteinas;
    }

    public int getCarboidratos() {
        return carboidratos;
    }

    public void setCarboidratos(int carboidratos) {
        this.carboidratos = carboidratos;
    }

    public int getGorduras() {
        return gorduras;
    }

    public void setGorduras(int gorduras) {
        this.gorduras = gorduras;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString() +
                ", " + proteinas +
                "g (prot), " +
                carboidratos +
                "g (carb), " +
                gorduras + "g (fat)";
    }
}

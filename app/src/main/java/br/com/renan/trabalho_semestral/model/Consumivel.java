package br.com.renan.trabalho_semestral.model;

import androidx.annotation.NonNull;

/**
 *@author: renan santos carvalho
 */
public abstract class Consumivel {
    // pode implementar um contador para criar ids únicos para bebida e alimento
    int id;
    int calorias;
    String nome;

    public abstract TipoConsumivel getTipo();

    public Consumivel() {
        super();
    }

    public Consumivel(int id, int calorias, String nome) {
        this.id = id;
        this.calorias = calorias;
        this.nome = nome;
    }

    public String calcularNutricao(int quant) {
        return id + ", " + nome + ": " + calorias * quant + "kcal";
    }

    public String calcularNutricaoCompleta(int quant) {
        return calcularNutricao(quant);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @NonNull
    @Override
    public String toString() {
        return id + ", " + nome + ": " + calorias + "kcal";
    }
}

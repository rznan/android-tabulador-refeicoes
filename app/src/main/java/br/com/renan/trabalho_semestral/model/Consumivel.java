package br.com.renan.trabalho_semestral.model;

import androidx.annotation.NonNull;

public class Consumivel {
    // pode implementar um contador para criar ids únicos para bebida e alimento
    int id;
    int calorias;
    String nome;
    int tipo;

    public Consumivel() {
        super();
    }

    public Consumivel(int id, int calorias, String nome, int tipo) {
        this.id = id;
        this.calorias = calorias;
        this.nome = nome;
        this.tipo = tipo;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @NonNull
    @Override
    public String toString() {
        return id + ", " + nome + ": " + calorias + "kcal";
    }
}

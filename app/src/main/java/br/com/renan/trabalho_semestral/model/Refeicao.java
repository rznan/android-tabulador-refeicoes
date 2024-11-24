package br.com.renan.trabalho_semestral.model;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *@author: renan santos carvalho
 */
public class Refeicao {
    int id;
    LocalDate data;
    List<Consumo> itens;

    public Refeicao() {
        itens = new ArrayList<>();
        data = LocalDate.now();
    }

    public Refeicao(int id, LocalDate data) {
        this.id = id;
        this.data = data;
        itens = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<Consumo> getItens() {
        return itens;
    }

    public void setItens(List<Consumo> itens) {
        this.itens = itens;
    }

    public void addItem(Consumivel c, int quant) {
        itens.add(new Consumo(c, quant));
    }

    public void removeItem(int idItem) {
        itens.removeIf(c -> c.getItem().getId() == idItem);
    }

    public String detalharItens() {
        StringBuilder sb = new StringBuilder();
        if(id != 0 && id >= 1) {
            sb.append(id).append(", ");
        }
        sb.append(data.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        sb.append("\n");

        int count = 0;
        for(Consumo c : itens) {
            sb.append("\t").append(count).append("| ").append(c.resumoTotal());
            if(count < itens.size() - 1) {
                sb.append("\n");
            }
            count++;
        }

        return sb.toString();
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(", ").append(data.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        sb.append("\n");

        int count = 0;
        for(Consumo c : itens) {
            sb.append("\t").append(count).append("| ").append(c.toString());
            if(count < itens.size() - 1) {
                sb.append("\n");
            }
            count++;
        }

        return sb.toString();
    }
}

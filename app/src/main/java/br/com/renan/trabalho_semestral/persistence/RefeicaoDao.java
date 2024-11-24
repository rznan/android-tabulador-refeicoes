package br.com.renan.trabalho_semestral.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import br.com.renan.trabalho_semestral.model.Alimento;
import br.com.renan.trabalho_semestral.model.Consumivel;
import br.com.renan.trabalho_semestral.model.Consumo;
import br.com.renan.trabalho_semestral.model.Refeicao;
import br.com.renan.trabalho_semestral.support.SafeParser;

public class RefeicaoDao implements IOpenClosableDAO<Refeicao, RefeicaoDao> {

    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase database;


    public RefeicaoDao(Context context) {
        this.context = context;
    }


    @Override
    public RefeicaoDao open() throws SQLException {
        gDao = new GenericDao(context);
        database = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() throws SQLException {
        gDao.close();
    }

    @Override
    public void insert(Refeicao refeicao) throws SQLException {
        ContentValues cvR = getContentValuesRefeicao(refeicao);
        long resultRefeicao = database.insert("Refeicao", null, cvR);
        if(resultRefeicao == -1) {
            throw new SQLException("Não foi possível Salvar a refeição");
        }
        boolean erroInsercaoItem = false;
        for(Consumo c : refeicao.getItens()) {
            ContentValues cvC = getContentValuesConsumo(c);
            cvC.put("id_refeicao", refeicao.getId());
            long resultConsumo = database.insert("Consumo", null, cvC);
            erroInsercaoItem = erroInsercaoItem || resultConsumo == -1;
        }
        if(erroInsercaoItem) {
            throw new SQLException("Não foi possível Salvar Todos os Itens");
        }
    }

    @Override
    public int update(Refeicao refeicao) throws SQLException {
        return 0;
    }

    @Override
    public void delete(Refeicao refeicao) throws SQLException {

    }

    @Override
    public Refeicao findOne(Refeicao refeicao) throws SQLException {
        return null;
    }

    @Override
    public List<Refeicao> findAll() throws SQLException {
        return Collections.emptyList();
    }

    @Override
    public List<Refeicao> findByType(int typeCode) throws SQLException {
        return Collections.emptyList();
    }

    @SuppressLint("Range")
    private static @NonNull Refeicao getRefeicao(Cursor cursor) {
        Refeicao refeicao = new Refeicao();
        refeicao.setId(cursor.getInt(cursor.getColumnIndex("id_refeicao")));
        refeicao.setData(getLocalDate(cursor));

        return refeicao;
    }

    @SuppressLint("Range")
    private static LocalDate getLocalDate(Cursor cursor) {
        return LocalDate.parse(
                cursor.getString(
                        cursor.getColumnIndex("data_refeicao")
                )
        );
    }

    private static @NonNull ContentValues getContentValuesConsumo(Consumo consumo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_consumivel", consumo.getItem().getId());
        contentValues.put("quantidade", consumo.getQuant());
        return contentValues;
    }

    private static @NonNull ContentValues getContentValuesRefeicao(Refeicao refeicao) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_refeicao", refeicao.getId());
        contentValues.put("data_refeicao", refeicao.getData().toString());
        return contentValues;
    }
}

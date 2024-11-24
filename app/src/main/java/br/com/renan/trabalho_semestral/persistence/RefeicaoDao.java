package br.com.renan.trabalho_semestral.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.intellij.lang.annotations.Language;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.renan.trabalho_semestral.model.Consumo;
import br.com.renan.trabalho_semestral.model.Refeicao;

public class RefeicaoDao implements IOpenClosableDAO<Refeicao, RefeicaoDao> {

    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase database;

    private final ConsumivelDao consumivelDao;

    public RefeicaoDao(Context context) {
        this.context = context;
        this.consumivelDao = new ConsumivelDao(context);
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
        database.delete(
                "Consumo",
                "id_refeicao = " + refeicao.getId(),
                null
        );
        ContentValues cvR = getContentValuesRefeicao(refeicao);
        int updateResult = database.update(
                "Refeicao", cvR,
                "id_refeicao = " + refeicao.getId(),
                null
        );
        boolean erroInsercaoItem = false;
        for(Consumo c : refeicao.getItens()) {
            ContentValues cvC = getContentValuesConsumo(c);
            cvC.put("id_refeicao", refeicao.getId());
            long resultConsumo = database.insert("Consumo", null, cvC);
            erroInsercaoItem = erroInsercaoItem || resultConsumo == -1;
        }
        if(erroInsercaoItem) {
            throw new SQLException("Não foi possível Atualizar Todos os Itens");
        }
        return updateResult;
    }

    @Override
    public void delete(Refeicao refeicao) throws SQLException {
        database.delete(
               "Consumo",
                "id_refeicao = " + refeicao.getId(),
                null
        );
        database.delete(
                "Refeicao",
                "id_refeicao = " + refeicao.getId(),
                null
        );
    }

    @Override
    public Refeicao findOne(Refeicao refeicao) throws SQLException {
        int idRefeicao = refeicao.getId();
        @Language("RoomSql") String sql =
                "SELECT " +
                        "id_refeicao, " +
                        "data_refeicao " +
                        "FROM Refeicao " +
                        "WHERE id_refeicao = " + idRefeicao;

        Cursor cursor = getCursor(sql);
        refeicao = null;
        if(consumivelDao.open() == null){
            consumivelDao.open();
        }
        if (cursor != null && !cursor.isAfterLast()) {
            refeicao = getRefeicao(cursor);
            cursor.close();
            List<Consumo> consumos = consumivelDao.findConsumoByRefeicao(idRefeicao);
            refeicao.setItens(consumos);
        }
        consumivelDao.close();

        return refeicao;
    }

    @Override
    public List<Refeicao> findAll() throws SQLException {
        List<Refeicao> refeicoes = new ArrayList<>();
        @Language("RoomSql") String sql =
                "SELECT " +
                        "id_refeicao, " +
                        "data_refeicao " +
                        "FROM Refeicao";

        Cursor cursor = getCursor(sql);
        if(consumivelDao.open() == null){
            consumivelDao.open();
        }
        if (cursor != null) {
            while (!cursor.isAfterLast()) {
                Refeicao refeicao = getRefeicao(cursor);
                List<Consumo> consumos = consumivelDao.findConsumoByRefeicao(refeicao.getId());
                refeicao.setItens(consumos);
                refeicoes.add(refeicao);
                cursor.moveToNext();
            }
            cursor.close();
        }
        consumivelDao.close();

        return refeicoes;
    }

    @Override
    public List<Refeicao> findByType(int typeCode) throws SQLException {
        return findAll();
    }

    private @Nullable Cursor getCursor(@Language("RoomSql") String sql) {
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
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

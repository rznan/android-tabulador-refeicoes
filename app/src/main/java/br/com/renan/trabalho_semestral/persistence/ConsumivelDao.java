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
import java.util.ArrayList;
import java.util.List;

import br.com.renan.trabalho_semestral.model.Alimento;
import br.com.renan.trabalho_semestral.model.Bebida;
import br.com.renan.trabalho_semestral.model.Consumivel;
import br.com.renan.trabalho_semestral.model.Consumo;
import br.com.renan.trabalho_semestral.model.TipoConsumivel;

public class ConsumivelDao implements IOpenClosableDAO<Consumivel, ConsumivelDao>, ICRUDDao<Consumivel> {

    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase database;


    public ConsumivelDao(Context context) {
        this.context = context;
    }


    @Override
    public ConsumivelDao open() throws SQLException {
        gDao = new GenericDao(context);
        database = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() throws SQLException {
        gDao.close();
    }


    @Override
    public void insert(Consumivel consumivel) throws SQLException {
        ContentValues cvC = getContentValuesConsumivel(consumivel);
        long consumivel1 = database.insert("consumivel", null, cvC);

        if(consumivel.getTipo() == TipoConsumivel.BEBIDA) {
            ContentValues cvB = getContentValuesBebida((Bebida) consumivel);
            database.insert("bebida", null, cvB);
        } else {
            ContentValues cvA = getContentValuesAlimento((Alimento) consumivel);
            database.insert("alimento", null, cvA);
        }
    }

    @Override
    public int update(Consumivel consumivel) throws SQLException {
        ContentValues cvC = getContentValuesConsumivel(consumivel);
        database.update(
            "Consumivel",  cvC,
            "id_consumivel = " + consumivel.getId(),
            null
        );

        if(consumivel.getTipo() == TipoConsumivel.BEBIDA) {
            ContentValues cvB = getContentValuesBebida((Bebida) consumivel);
            return database.update(
                    "Bebida",  cvB,
                    "id_consumivel = " + consumivel.getId(),
                    null
            );
        } else {
            ContentValues cvA = getContentValuesAlimento((Alimento) consumivel);
            return database.update(
                    "Alimento",  cvA,
                    "id_consumivel = " + consumivel.getId(),
                    null
            );
        }
    }

    @Override
    public void delete(Consumivel consumivel) throws SQLException {
        if(consumivel.getTipo() == TipoConsumivel.BEBIDA) {
            database.delete(
                    "Bebida",
                    "id_consumivel = " + consumivel.getId(),
                    null
            );
        } else {
            database.delete(
                    "Alimento",
                    "id_consumivel = " + consumivel.getId(),
                    null
            );
        }
        database.delete(
                "Consumo",
                "id_consumivel = " + consumivel.getId(),
                null
        );
        database.delete(
                "Consumivel",
                "id_consumivel = " + consumivel.getId(),
                null
        );
    }

    @Override
    public Consumivel findOne(Consumivel consumivel) throws SQLException {
        @Language("RoomSql") String sql;
        if(consumivel.getTipo() == TipoConsumivel.BEBIDA) {
             sql =
                    "SELECT " +
                            "C.id_consumivel, " +
                            "C.calorias, " +
                            "C.nome,  " +
                            "C.tipo, " +
                            "B.volume, " +
                            "B.acucares " +
                            "FROM Consumivel C, Bebida B " +
                            "WHERE C.id_consumivel = B.id_consumivel AND C.id_consumivel = " + consumivel.getId();
        } else {
            sql =
                    "SELECT " +
                            "C.id_consumivel, " +
                            "C.calorias, " +
                            "C.nome, " +
                            "C.tipo, " +
                            "A.proteinas, " +
                            "A.gorduras, " +
                            "A.carboidratos " +
                            "FROM Consumivel C, Alimento A " +
                            "WHERE C.id_consumivel = A.id_consumivel AND C.id_consumivel = " + consumivel.getId();
        }
        Cursor cursor = getCursor(sql);
        consumivel = null;
        if (cursor != null && !cursor.isAfterLast()) {
            consumivel = getConsumivel(cursor);
            cursor.close();
        }
        return consumivel;
    }

    // O programa não fará uso de encontrar todos os consumiveis,
    // o que esta presente aqui é apenas uma gambiarra
    @Override
    public List<Consumivel> findAll() throws SQLException {
        List<Consumivel> list = new ArrayList<>();
        @Language("RoomSql") String sql =
                "SELECT " +
                        "C.id_consumivel," +
                        "C.calorias," +
                        "C.nome, " +
                        "C.tipo," +
                        "B.volume," +
                        "B.acucares, " +
                        "A.proteinas," +
                        "A.gorduras," +
                        "A.carboidratos " +
                        "FROM Consumivel C, Bebida B " +
                        "WHERE C.id_consumivel = B.id_consumivel AND C.id_consumivel = A.id_consumivel";

        Cursor cursor = getCursor(sql);
        while(cursor != null && !cursor.isAfterLast()) {
            Consumivel consumivel = getConsumivel(cursor);
            list.add(consumivel);
            cursor.moveToNext();
        }
        return list;
    }

    @Override
    public List<Consumivel> findByType(int typeCode) throws SQLException {
        List<Consumivel> list = new ArrayList<>();
        @Language("RoomSql") String sql;
        if(typeCode == TipoConsumivel.BEBIDA.getValue()) {
            sql =
                    "SELECT " +
                            "C.id_consumivel," +
                            "C.calorias," +
                            "C.nome, " +
                            "C.tipo," +
                            "B.volume," +
                            "B.acucares " +
                            "FROM Consumivel C, Bebida B " +
                            "WHERE C.id_consumivel = B.id_consumivel";
        } else {
            sql =
                    "SELECT " +
                            "C.id_consumivel," +
                            "C.calorias," +
                            "C.nome, " +
                            "C.tipo," +
                            "A.proteinas," +
                            "A.gorduras," +
                            "A.carboidratos " +
                            "FROM Consumivel C, Alimento A " +
                            "WHERE C.id_consumivel = A.id_consumivel";
        }
        Cursor cursor = getCursor(sql);
        while (cursor != null && !cursor.isAfterLast()) {
            Consumivel consumivel = getConsumivel(cursor);
            list.add(consumivel);
            cursor.moveToNext();
        }
        return list;
    }

    @SuppressLint("Range")
    public List<Consumo> findConsumoByRefeicao(int idRefeicao) throws SQLException {
        List<Consumo> list = new ArrayList<>();
        @Language("RoomSql") String sqlBebidas =
                "SELECT " +
                        "C.id_refeicao, " +
                        "C.id_consumivel, " +
                        "C.quantidade, " +
                        "Cn.calorias, " +
                        "Cn.nome, " +
                        "Cn.tipo, " +
                        "b.volume," +
                        "b.acucares " +
                        "FROM Consumo C, Consumivel Cn, Bebida b " +
                        "WHERE " +
                        "C.id_consumivel = Cn.id_consumivel AND " +
                        "C.id_consumivel = b.id_consumivel AND " +
                        "C.id_refeicao = " + idRefeicao;

        @Language("RoomSql") String sqlAlimento =
                "SELECT " +
                        "C.id_refeicao, " +
                        "C.id_consumivel, " +
                        "C.quantidade, " +
                        "Cn.calorias, " +
                        "Cn.nome, " +
                        "Cn.tipo, " +
                        "A.proteinas, " +
                        "A.gorduras, " +
                        "A.carboidratos " +
                        "FROM Consumo C, Consumivel Cn, Alimento A " +
                        "WHERE " +
                        "C.id_consumivel = Cn.id_consumivel AND " +
                        "C.id_consumivel = A.id_consumivel AND " +
                        "C.id_refeicao = " + idRefeicao;

        Cursor cursor = getCursor(sqlBebidas);
        while (cursor != null && !cursor.isAfterLast()) {
            list.add(getConsumo(cursor));
            cursor.moveToNext();
        }

        cursor = getCursor(sqlAlimento);
        while (cursor != null && !cursor.isAfterLast()) {
            list.add(getConsumo(cursor));
            cursor.moveToNext();
        }

        return list;
    }

    @SuppressLint("Range")
    private static Consumo getConsumo(Cursor cursor) {
        Consumivel consumivel = getConsumivel(cursor);
        Consumo consumo = new Consumo();
        consumo.setQuant(cursor.getInt(cursor.getColumnIndex("quantidade")));
        consumo.setItem(consumivel);
        return consumo;
    }


    private @Nullable Cursor getCursor(@Language("RoomSql") String sql) {
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    @SuppressLint("Range")
    private static @NonNull Consumivel getConsumivel(Cursor cursor) {
        int tipo = cursor.getInt(cursor.getColumnIndex("tipo"));
        if (tipo == TipoConsumivel.BEBIDA.getValue()) {
            return getBebida(cursor);
        }
        else {
            return getAlimento(cursor);
        }
    }

    @SuppressLint("Range")
    private static @NonNull Alimento getAlimento(Cursor cursor) {
        Alimento alimento = new Alimento();
        alimento.setId(cursor.getInt(cursor.getColumnIndex("id_consumivel")));
        alimento.setCalorias(cursor.getInt(cursor.getColumnIndex("calorias")));
        alimento.setNome(cursor.getString(cursor.getColumnIndex("nome")));
        alimento.setProteinas(cursor.getInt(cursor.getColumnIndex("proteinas")));
        alimento.setGorduras(cursor.getInt(cursor.getColumnIndex("gorduras")));
        alimento.setCarboidratos(cursor.getInt(cursor.getColumnIndex("carboidratos")));
        return alimento;
    }

    @SuppressLint("Range")
    private static @NonNull Bebida getBebida(Cursor cursor) {
        Bebida bebida = new Bebida();
        bebida.setId(cursor.getInt(cursor.getColumnIndex("id_consumivel")));
        bebida.setCalorias(cursor.getInt(cursor.getColumnIndex("calorias")));
        bebida.setNome(cursor.getString(cursor.getColumnIndex("nome")));
        bebida.setVolume(cursor.getInt(cursor.getColumnIndex("volume")));
        bebida.setAcucares(cursor.getInt(cursor.getColumnIndex("acucares")));
        return bebida;
    }

    private static ContentValues getContentValuesAlimento(Alimento alimento) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_consumivel", alimento.getId());
        contentValues.put("proteinas", alimento.getProteinas());
        contentValues.put("gorduras", alimento.getGorduras());
        contentValues.put("carboidratos", alimento.getCarboidratos());
        return contentValues;
    }

    private static ContentValues getContentValuesBebida(Bebida bebida) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_consumivel", bebida.getId());
        contentValues.put("volume", bebida.getVolume());
        contentValues.put("acucares", bebida.getAcucares());
        return contentValues;
    }

    private static @NonNull ContentValues getContentValuesConsumivel(Consumivel consumivel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_consumivel", consumivel.getId());
        contentValues.put("calorias", consumivel.getCalorias());
        contentValues.put("tipo", consumivel.getTipo().getValue());
        contentValues.put("nome", consumivel.getNome());
        return contentValues;
    }
}

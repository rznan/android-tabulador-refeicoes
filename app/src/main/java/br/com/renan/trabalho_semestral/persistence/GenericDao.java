package br.com.renan.trabalho_semestral.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author: renan santos carvalho
 */
public class GenericDao extends SQLiteOpenHelper {

    private static final String DATABASE = "DIETA.DB";
    private static final int DATABASE_VER = 1;
    private static final String CREATE_TABLE_CONSUMIVEL =
            "CREATE TABLE Consumivel(" +
                    "id_consumivel INT NOT NULL PRIMARY KEY," +
                    "calorias INT," +
                    "nome VARCHAR(100) NOT NULL," +
                    "tipo INT NOT NULL);";
    private static final String CREATE_TABLE_BEBIDA =
            "CREATE TABLE Bebida(" +
                    "id_consumivel INT NOT NULL," +
                    "volume INT," +
                    "acucares INT," +
                    "FOREIGN KEY (id_consumivel) REFERENCES Consumivel(id_consumivel));";
    private static final String CREATE_TABLE_ALIMENTO =
            "CREATE TABLE Alimento(" +
                    "id_consumivel INT NOT NULL," +
                    "proteinas INT," +
                    "gorduras INT," +
                    "carboidratos INT," +
                    "FOREIGN KEY (id_consumivel) REFERENCES Consumivel(id_consumivel));";
    private static final String CREATE_TABLE_REFEICAO =
            "CREATE TABLE Refeicao(" +
                    "id_refeicao INT NOT NULL PRIMARY KEY," +
                    "data_refeicao VARCHAR(10) NOT NULL);";
    private static final String CREATE_TABLE_CONSUMO =
            "CREATE TABLE Consumo(" +
                    "id_refeicao INT NOT NULL," +
                    "id_consumivel INT NOT NULL," +
                    "quantidade INT NOT NULL," +
                    "FOREIGN KEY (id_refeicao) REFERENCES REFEICAO(id_refeicao)," +
                    "FOREIGN KEY (id_consumivel) REFERENCES CONSUMIVEL(id_consumivel));";


    public GenericDao(Context context) {
        super(context, DATABASE, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_CONSUMIVEL);
        sqLiteDatabase.execSQL(CREATE_TABLE_BEBIDA);
        sqLiteDatabase.execSQL(CREATE_TABLE_ALIMENTO);
        sqLiteDatabase.execSQL(CREATE_TABLE_REFEICAO);
        sqLiteDatabase.execSQL(CREATE_TABLE_CONSUMO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int o, int n) {
        if(n > o) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Consumo");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Refeicao");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Alimento");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Bebida");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Consumivel");
            onCreate(sqLiteDatabase);
        }
    }
}

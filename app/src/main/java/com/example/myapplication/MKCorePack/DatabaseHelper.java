package com.example.myapplication.MKCorePack;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;

public class DatabaseHelper extends SQLiteOpenHelper implements Serializable{
    private static final String DATABASE_NAME = "kombatsstats.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных
    public static final String TABLE = "kombats"; // название таблицы в бд
    // названия столбцов
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_LEFT_CHARACTER_ID = "left_character_id";
    public static final String COLUMN_LEFT_VARIATION_ID = "left_character_variation_id";
    public static final String COLUMN_LEFT_PLAYER = "left_player";
    public static final String COLUMN_RIGHT_CHARACTER_ID = "right_character_id";
    public static final String COLUMN_RIGHT_VARIATION_ID = "right_character_variation_id";
    public static final String COLUMN_RIGHT_PLAYER = "right_player";
    public static final String COLUMN_ISRANKED = "isRanked";
    public static final String COLUMN_KOMBAT_LEAGUE_SEASON = "kombat_league_season";
    public static final String COLUMN_LEFT_PLAYER_MMR = "left_player_mmr";
    public static final String COLUMN_RIGHT_PLAYER_MMR = "right_player_mmr";
    public static final String COLUMN_WINNER_SIDE = "winner_side";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    public void reset(SQLiteDatabase db) {
        onUpgrade(db, 1 , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE kombats (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_LEFT_CHARACTER_ID
                + " INTEGER, " + COLUMN_LEFT_VARIATION_ID
                + " INTEGER, " + COLUMN_LEFT_PLAYER + " TEXT, " + COLUMN_RIGHT_CHARACTER_ID
                + " INTEGER, " + COLUMN_RIGHT_VARIATION_ID
                + " INTEGER, " + COLUMN_RIGHT_PLAYER + " TEXT, " + COLUMN_ISRANKED
                + " INTEGER, " + COLUMN_KOMBAT_LEAGUE_SEASON + " INTEGER, " + COLUMN_LEFT_PLAYER_MMR + " INTEGER, "
                + COLUMN_RIGHT_PLAYER_MMR + " INTEGER, " + COLUMN_WINNER_SIDE + " INTEGER);");
        // добавление начальных данных
        db.execSQL("INSERT INTO "+ TABLE + " (" + COLUMN_LEFT_CHARACTER_ID
                + ", " + COLUMN_LEFT_VARIATION_ID
                + ", " + COLUMN_LEFT_PLAYER + ", " + COLUMN_RIGHT_CHARACTER_ID + ", " + COLUMN_RIGHT_VARIATION_ID
                + ", " + COLUMN_RIGHT_PLAYER
                + ", " + COLUMN_ISRANKED + ", " + COLUMN_KOMBAT_LEAGUE_SEASON + ", " + COLUMN_LEFT_PLAYER_MMR + ", " + COLUMN_RIGHT_PLAYER_MMR
                + ", " + COLUMN_WINNER_SIDE
                + ") VALUES (1, 1, 'Scarvis', 2, 2, 'King', 0, 0, 0, 0, 0);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}

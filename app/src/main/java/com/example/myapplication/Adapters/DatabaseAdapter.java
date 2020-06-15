package com.example.myapplication.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.MKCorePack.Character;
import com.example.myapplication.MKCorePack.DatabaseHelper;
import com.example.myapplication.MKCorePack.Kombat;
import com.example.myapplication.MKCorePack.MKCore;
import com.example.myapplication.MKCorePack.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter implements Serializable {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseAdapter(Context context){
        dbHelper = new DatabaseHelper(context.getApplicationContext());
    }

    public DatabaseAdapter open(){
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    private Cursor getAllEntries(){
        String[] columns = new String[] {DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_LEFT_CHARACTER_ID, DatabaseHelper.COLUMN_LEFT_VARIATION_ID,
                DatabaseHelper.COLUMN_LEFT_PLAYER, DatabaseHelper.COLUMN_RIGHT_CHARACTER_ID,
                DatabaseHelper.COLUMN_RIGHT_VARIATION_ID, DatabaseHelper.COLUMN_RIGHT_PLAYER,
                DatabaseHelper.COLUMN_ISRANKED, DatabaseHelper.COLUMN_KOMBAT_LEAGUE_SEASON, DatabaseHelper.COLUMN_LEFT_PLAYER_MMR,
                DatabaseHelper.COLUMN_RIGHT_PLAYER_MMR, DatabaseHelper.COLUMN_WINNER_SIDE};
        return  database.query(DatabaseHelper.TABLE, columns, null, null, null, null, null);
    }

    private Kombat getKombat(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
        Character leftCharacter = Character.getCharacterById(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_LEFT_CHARACTER_ID)));
        leftCharacter.setVariation(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_LEFT_VARIATION_ID)));
        String leftPlayer = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LEFT_PLAYER));
        Character rightCharacter = Character.getCharacterById(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_RIGHT_CHARACTER_ID)));
        rightCharacter.setVariation(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_RIGHT_VARIATION_ID)));
        String rightPlayer = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_RIGHT_PLAYER));
        int isRanked = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ISRANKED));
        int kombatLeagueSeason = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_KOMBAT_LEAGUE_SEASON));
        int leftPlayerMMR = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_LEFT_PLAYER_MMR));
        int rightPlayerMMR = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_RIGHT_PLAYER_MMR));
        int winnerSide = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_WINNER_SIDE));
        Kombat kombat = new Kombat(id,
                leftCharacter,
                rightCharacter,
                Player.getPlayer(leftPlayer, leftPlayerMMR),
                Player.getPlayer(rightPlayer, rightPlayerMMR),
                (isRanked == 1),
                kombatLeagueSeason,
                winnerSide
        );
        return kombat;
    }

    public ArrayList<Kombat> getKombats(){
        ArrayList<Kombat> kombatsArrayList = new ArrayList<>();
        Cursor cursor = getAllEntries();
        if(cursor.moveToFirst()){
            do{
                kombatsArrayList.add(getKombat(cursor));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return  kombatsArrayList;
    }

    public long getCount(){
        return DatabaseUtils.queryNumEntries(database, DatabaseHelper.TABLE);
    }

    public Kombat getKombat(long id){
        Kombat kombat = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?", DatabaseHelper.TABLE, DatabaseHelper.COLUMN_ID);
        Cursor cursor = database.rawQuery(query, new String[]{ String.valueOf(id)});
        if(cursor.moveToFirst()){
            kombat = getKombat(cursor);
        }
        cursor.close();
        return  kombat;
    }

    private ContentValues getKombatCV(Kombat kombat) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_LEFT_CHARACTER_ID, kombat.getLeftCharacter().getId());
        cv.put(DatabaseHelper.COLUMN_LEFT_VARIATION_ID, kombat.getLeftCharacter().getVariation().getId());
        cv.put(DatabaseHelper.COLUMN_LEFT_PLAYER, kombat.getLeftPlayer().getNickName());
        cv.put(DatabaseHelper.COLUMN_RIGHT_CHARACTER_ID, kombat.getRightCharacter().getId());
        cv.put(DatabaseHelper.COLUMN_RIGHT_VARIATION_ID, kombat.getRightCharacter().getVariation().getId());
        cv.put(DatabaseHelper.COLUMN_RIGHT_PLAYER, kombat.getRightPlayer().getNickName());
        cv.put(DatabaseHelper.COLUMN_ISRANKED, kombat.getIsRankedInt());
        cv.put(DatabaseHelper.COLUMN_KOMBAT_LEAGUE_SEASON, kombat.getKombatLeagueSeason());
        cv.put(DatabaseHelper.COLUMN_LEFT_PLAYER_MMR, kombat.getLeftPlayer().getMmr());
        cv.put(DatabaseHelper.COLUMN_RIGHT_PLAYER_MMR, kombat.getRightPlayer().getMmr());
        cv.put(DatabaseHelper.COLUMN_WINNER_SIDE, kombat.getIntWinnerSide());
        return cv;
    }

    public long insert(Kombat kombat){
        ContentValues cv = getKombatCV(kombat);
        return  database.insert(DatabaseHelper.TABLE, null, cv);
    }

    public long delete(long userId){
        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(userId)};
        return database.delete(DatabaseHelper.TABLE, whereClause, whereArgs);
    }

    public long update(Kombat kombat){
        String whereClause = DatabaseHelper.COLUMN_ID + "=" + String.valueOf(kombat.getId());
        ContentValues cv = getKombatCV(kombat);
        return database.update(DatabaseHelper.TABLE, cv, whereClause, null);
    }
}

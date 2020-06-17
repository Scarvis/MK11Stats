package com.example.myapplication.MKCorePack;

import android.content.Context;

import com.example.myapplication.MainActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class JSONHelper {
    private static final String FILE_NAME = "data.json";
    private static final String CHARACTERS_NAME = "characters.json";

    public static boolean exportToJSON(Context context, ArrayList<Kombat> dataList) {
        Gson gson = new Gson();
        DataItems dataItems = new DataItems();
        dataItems.setKombats(dataList);
        String jsonString = gson.toJson(dataItems);

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fileOutputStream.write(jsonString.getBytes());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static boolean addToJSON(Context context, ArrayList<Kombat> dataList) {
        Gson gson = new Gson();
        DataItems dataItems = new DataItems();
        dataItems.setKombats(dataList);
        String jsonString = gson.toJson(dataItems);

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_APPEND);
            fileOutputStream.write(jsonString.getBytes());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static ArrayList<Kombat> importFromJSON(Context context) {
        InputStreamReader streamReader = null;
        FileInputStream fileInputStream = null;
        try{
            fileInputStream = context.openFileInput(FILE_NAME);
            streamReader = new InputStreamReader(fileInputStream);
            Gson gson = new Gson();
            DataItems dataItems = gson.fromJson(streamReader, DataItems.class);
            return dataItems.getKombats();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public static ArrayList<Character> importCharactersFromJSON(String text) {
        try {
            JSONObject characterJson = new JSONObject(text);
            JSONArray charArray = characterJson.getJSONArray("characters");
            ArrayList<Character> characterArrayList = new ArrayList<>();

            for (int i = 0; i < charArray.length(); i++) {
                JSONObject elem = charArray.getJSONObject(i);
                ArrayList<Variation> variationArrayList = new ArrayList<>();
                int id = elem.getInt("id");
                String name = elem.getString("name");

                JSONObject dlc = elem.getJSONObject("DLC");
                String dlcName = dlc.getString("name");
                String releaseDate = dlc.getString("releaseDate");
                DLCCharacter dlcCharacter = new DLCCharacter(dlcName, releaseDate);

                JSONArray variationArr = elem.getJSONArray("variation");
                JSONObject typeVar = variationArr.getJSONObject(0);
                JSONArray typeVarArr = typeVar.getJSONArray("ranked");
                for (int j = 0; j < typeVarArr.length(); j++) {
                    JSONObject var = typeVarArr.getJSONObject(j);
                    variationArrayList.add(new Variation(j + 1, var.getString("title")));
                }
                characterArrayList.add(new Character(id, name, variationArrayList, 0, dlcCharacter));
            }
            Collections.sort(characterArrayList, Character.CharactersIdComparator);
            return characterArrayList;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static class DataItems {
        private ArrayList<Kombat> kombats;

        ArrayList<Kombat> getKombats() {
            return kombats;
        }
        void setKombats(ArrayList<Kombat> kombats) {
            this.kombats = kombats;
        }
    }
}

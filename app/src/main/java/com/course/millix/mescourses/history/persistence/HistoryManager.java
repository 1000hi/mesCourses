package com.course.millix.mescourses.history.persistence;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.course.millix.mescourses.ItemCourse;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de gestion de l'historique du panier de course
 * Elle s'occupera de gérer la sauvegarde de l'historique dans le téléphone
 */
public class HistoryManager implements Serializable {

    private static final String JSON_NAME = "/history.json";
    @SuppressLint("SdCardPath")
    private static final String DEFAULT_PATH = "/data/user/0/com.course.millix.mescourses/files";
    private List<ItemCourse> listeDeCourse = new ArrayList<>();

    /**
     * Constructeur vide
     */
    public HistoryManager() {
    }


    /**
     * Getter de la liste
     *
     * @return la liste totale des courses
     */
    public List<ItemCourse> getListeDeCourse() {
        return listeDeCourse;
    }

    /**
     * Fonction de sauvegarde de l'historique dans le format JSON
     *
     * @return true si le sauvegarde a réussie, false sinon
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void saveJson(String folderName, String filename) {

        try (FileOutputStream fos = new FileOutputStream(new File(folderName))) {
            if (filename != null) {
                fos.write(filename.getBytes());
            }
        } catch (IOException e) {
            Log.e("IOException", e.toString());
        }
    }

    /**
     * Fonction qui verifie si un fichier est présent
     * <p>
     *
     * @param fileName Chemin du fichier
     * @return true si il est présent, false sinon
     */
    private boolean isFilePresent(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }


    private String itemToJson(ItemCourse item) {
        return new Gson().toJson(item);
    }

    private String itemListToJson(List<ItemCourse> items) {
        Gson gs = new Gson();
        return new Gson().toJson(items);
    }

    /**
     * Fonction qui lit le fichier mis en paramètre
     * <p>
     *
     * @param fileName
     * @return le string lu ou null sinon
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String readJson(String fileName) {

        StringBuilder sb = new StringBuilder();
        try (FileInputStream fis = new FileInputStream(fileName);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader bufferedReader = new BufferedReader(isr)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            Log.e("IOException", e.toString());
            return null;
        }

        return sb.toString();
    }

    /**
     * Méthode générale de sauvegarde
     *
     * @return true si sauvegarde réussie, non sinon
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void save(ItemCourse item) throws JSONException {

        List<ItemCourse> items = new ArrayList<>();
        if (isFilePresent(DEFAULT_PATH + JSON_NAME)) {
            //si le json existe on va le lire, puis ajouter l'historique actuel
            String oldHistoJson = readJson(DEFAULT_PATH + JSON_NAME);
            JSONArray jsonObj = new JSONArray(oldHistoJson);
            items.addAll(jsonToItemList(jsonObj));
            //On ajoute l'item à sauvegarder
            items.add(item);
            String history = itemListToJson(items);
            saveJson(DEFAULT_PATH + JSON_NAME, history);
        } else {
            items.add(item);
            String currentHistoJson = itemListToJson(items);
            File mypath = new File(DEFAULT_PATH);
            if (!mypath.exists() && !mypath.mkdir()) {
                //la creation du dossier à echoué
                Log.e("MKDIR_FAIL", "ECHEC CREATION DOSSIER");
            }
            saveJson(DEFAULT_PATH + JSON_NAME, currentHistoJson);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public List<ItemCourse> getHistory() {
        List<ItemCourse> articleHistory = new ArrayList<>();
        if(!isFilePresent(DEFAULT_PATH+JSON_NAME)){
            return articleHistory;
        }
        String result = readJson(DEFAULT_PATH + JSON_NAME);
        if (result != null) {
            try {
                JSONArray jsonObj = new JSONArray(result);
                articleHistory.addAll(jsonToItemList(jsonObj));
            } catch (JSONException e) {
                Log.e("ERRORJSON", e.toString());
            }

        }
        return articleHistory;
    }


    private List<ItemCourse> jsonToItemList(JSONArray jsonObj) throws JSONException {

        List<ItemCourse> listeArticle = new ArrayList<>();
        for (int i = 0; i < jsonObj.length(); i++) {
            JSONObject presObj = jsonObj.getJSONObject(i);
            ItemCourse iCTemp = new ItemCourse(presObj.getString("denomination"), presObj.getString("doneDate"), true, Integer.valueOf(presObj.getString("quantite")));
            listeArticle.add(iCTemp);
        }

        return listeArticle;
    }

    public boolean flushHistory() {
        File file = new File(DEFAULT_PATH + JSON_NAME);
        return !file.exists() || file.delete();
    }


}

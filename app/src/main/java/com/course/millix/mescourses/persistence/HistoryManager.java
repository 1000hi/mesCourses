package com.course.millix.mescourses.persistence;

import com.course.millix.mescourses.ItemCourse;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mvaillant on 01/08/2018.
 */

/**
 * Classe de gestion de l'historique du panier de course
 * Elle s'occupera de gérer la sauvegarde de l'historique dans le téléphone
 */
public class HistoryManager {

    private List<ItemCourse> listeDeCourse = new ArrayList<>();

    /**
     * Constructeur vide
     */
    public HistoryManager() {
    }

    /**
     * Méthode d'ajout d'un élément dans la liste à sauvegarder
     * @param item
     */
    public void addCourse(ItemCourse item){
        listeDeCourse.add(item);
    }

    /**
     * Getter de la liste
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
    public boolean saveInStorage(){
        //sauvegarde une liste d'element dans le téléphone
        return true;
    }


    public JSONObject itemListToJson(List<ItemCourse> items){
        return null;
    }

    public List<ItemCourse> jsonToItemList(JSONObject json){
        return null;
    }

    public List<ItemCourse> mergeLists(List<ItemCourse> l1, List<ItemCourse>l2){
        return null;
    }


}

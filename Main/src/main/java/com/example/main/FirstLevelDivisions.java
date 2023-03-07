package com.example.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FirstLevelDivisions {
    private static ObservableList<FirstLevelDivisions> allDivisions = FXCollections.observableArrayList();
    private static ObservableList<FirstLevelDivisions> allDivisionsFiltered = FXCollections.observableArrayList();
    private int divisionId;
    private String division;
    private int countryId;

    public FirstLevelDivisions(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    public String toString(){return getDivision();}
    public static ObservableList<FirstLevelDivisions> getAllDivisions(){
        return allDivisions;
    }
    public static void addDivisions(FirstLevelDivisions app){
        allDivisions.add(app);
    }
    public static ObservableList<FirstLevelDivisions> getAllDivisionsFiltered(){
        return allDivisionsFiltered;
    }
    public static void addDivisionsFiltered(FirstLevelDivisions app){
        allDivisionsFiltered.add(app);
    }

    public static void populateList(){
        try {
            allDivisions.clear();

            ResultSet rs = Query.queryDB("SELECT * FROM first_level_divisions");

            while (rs.next()) {
                FirstLevelDivisions object = new FirstLevelDivisions(
                        rs.getInt("division_id"),
                        rs.getString("division"),
                        rs.getInt("country_id"));
                addDivisions(object);
            }
        }catch (SQLException se){

        }
    }
    public static void filteredList(int cId){
        allDivisionsFiltered.clear();
        for (FirstLevelDivisions x: allDivisions){
            if (cId == x.getCountryId()){
                addDivisionsFiltered(x);
            }
        }
    }
    public static int getCountryId(int id){
        try{
            ResultSet rs = Query.queryDB("SELECT * FROM first_level_divisions");
            while (rs.next()){
                if (rs.getInt("division_id") == (id)){
                    return rs.getInt("country_id")-1;
                }
            }
            return 0;
        }catch (SQLException se){

        }
        return 0;
    }

    public static int getDivisionId(int id){
        int country_id = 0;
        try{
            ResultSet rs = Query.queryDB("SELECT * FROM first_level_divisions");
            while (rs.next()){
                if (rs.getInt("division_id") == (id)){
                    country_id = rs.getInt("country_id");
                }
            }
            ResultSet gs = Query.queryDB("SELECT * FROM countries");
            while (gs.next()){
                if (gs.getInt("country_id") == country_id){
                    return gs.getInt("country_id") - 1;
                }
            }
            return 0;
        }catch (SQLException se){

        }
        return 0;
    }
}

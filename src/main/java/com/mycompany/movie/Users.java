/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.movie;

import java.util.List;

/**
 *
 * @author raschad
 */
public class Users {
    private int userId;
    private String name;
    private List<String> purchased;
    private List<String> viewed;
    private int num_of_rep;
    
    public Users(int userId,String name, List<String> viewed, List<String> purchased) {
        this.userId = userId;
        this.name = name;
        this.viewed = viewed;
        this.purchased = purchased;
    }

    
    public Users() {
    
    }

    public List<String> getPurchased() {
        return purchased;
    }

    public void setPurchased(List<String> purchased) {
        this.purchased = purchased;
    }

    public int getNum_of_rep() {
        return num_of_rep;
    }

    public void setNum_of_rep(int num_of_rep) {
        this.num_of_rep = num_of_rep;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<String> getViewed() {
        return viewed;
    }

    public void setViewed(List<String> viewed) {
        this.viewed = viewed;
    }
    
    
}

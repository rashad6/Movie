/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.movie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author raschad
 */
public class Movie {

        static List<Products> movieProducts = new ArrayList<>();
        static List<String> searchedGenres = new ArrayList<>();
        static List<Users> allDataUser = new ArrayList<>();
    public static void main(String[] args) {
        ArrayList<String> products = new ArrayList<>();
        ArrayList<String> users = new ArrayList<>();
        Map<Integer, Integer> sessions = new HashMap<>();
        String[] product;
        String[] user;
        //first read datas from files
        try {
            File myObjforProducts = new File("C:\\Users\\raschad\\Documents\\NetBeansProjects\\movie\\MovieProductData\\Products.txt");
            File myObjforUser = new File("C:\\Users\\raschad\\Documents\\NetBeansProjects\\movie\\MovieProductData\\Users.txt");
            File myObjforCurrentUserSession = new File("C:\\Users\\raschad\\Documents\\NetBeansProjects\\movie\\MovieProductData\\CurrentUserSession.txt");
            Scanner myReaderforProd = new Scanner(myObjforProducts);
            Scanner myReaderforUser = new Scanner(myObjforUser);
            Scanner myReaderforSession = new Scanner(myObjforCurrentUserSession);
            while(myReaderforProd.hasNextLine()){
               String prodData = myReaderforProd.nextLine();
               products.add(prodData);
                //System.out.println(data);
            }
            while(myReaderforUser.hasNextLine()){
               String userData = myReaderforUser.nextLine();
               users.add(userData); 
               //System.out.println(userData);
            }
            while(myReaderforSession.hasNextLine()){
              String SessionData = myReaderforSession.nextLine();
              String[] session = SessionData.split("[ ,]+");
              sessions.put(Integer.parseInt(session[0]), Integer.parseInt(session[1]));
//               sess.add(SessionData);
                //System.out.println(userData);
            }
            myReaderforProd.close();
            myReaderforUser.close();
            myReaderforUser.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
        //take data about products as a string 
        //create array regarding to products and assign it to list in producsts class type 
        for (String allProduct : products) {
             product = allProduct.split("[,]");
             Products newProduct = new Products(Integer.parseInt(product[0]), product[1], product[2], product[3], product[4],
                      product[5], product[6], product[7], Double.parseDouble(product[8]), Double.parseDouble(product[9]));
             movieProducts.add(newProduct);
             
        }
        //CASE 1
        //sort products regarding to rating
        List<Products> sortedProduct = movieProducts.stream().
                sorted(Comparator.comparing(Products::getRating)).collect(Collectors.toList());
        
        //i want to demonstrate only 5 movies with rating only
        for(int i= sortedProduct.size()-1; i >34; i--){
            System.out.println(sortedProduct.get(i).getName() + "  " + " the rating is : " + sortedProduct.get(i).getRating());
        }
        
        //take user datas from user.txt and create users class type list to store datas
        //for viewed and purchased movies create first arrays then assign them to Arraylist
        for(String userData  : users){
            user = userData.split("[ ,]+");
            String[] viewed = user[2].split("[;]");
            String[] purchased = user[3].split("[;]");
            Users u = new Users(Integer.parseInt(user[0]), user[1], Arrays.asList(viewed) , Arrays.asList(purchased) );
            allDataUser.add(u);
        }
        
        //CASE 2
        //here i take datas from usersession and work with movies' genres
        //default set userDefinedMovieId because when i check movies, some of them have less genres than 5, if it is possible, then i take as a default 0th genre
        //i just took genres from userssesion as a string and send to recommendedMovies method to choose random movie with same genre and create random number between 0-40 in recommendedMovies method, 
        //hashset for not to duplicate movies for recommendation 
        //created 2 methods, one for moviegenres from usersession, other one to find recommended movie id for users
        //i had the idea that i can recommend movie according to purchased movies, because if user bought these movies, it means they want to see more about same genres
        //but i didn't implemented 
        //at the end it shows user ids and recommended movie ids on console
        for(int userId : sessions.keySet()){
            int userDefinedMovieId =0;
            int movieId = sessions.get(userId);
            int recommendedMovieId;
            HashSet<Integer> movieIds = new HashSet<>();
            searchedGenres = movieGenres(movieId);
            System.out.print("userId : " + userId + " recommendendMoviesId : ");
            
            for(int i =0; i < 5; i++){
            if(searchedGenres.get(i)==null)
                recommendedMovieId = recommendedMovies(userId-1, searchedGenres.get(userDefinedMovieId));
            else
                recommendedMovieId = recommendedMovies(userId-1, searchedGenres.get(i));
            
            movieIds.add(recommendedMovieId);
                //System.out.println("size is : " + movieIds.size());
            }
            if(movieIds.size()<5)
                {
                   while(movieIds.size()<5){
                     recommendedMovieId = recommendedMovies(userId-1, searchedGenres.get(userDefinedMovieId));
                     movieIds.add(recommendedMovieId);
                   } 
                 }
            //ArrayList<Integer> prevIndex = new ArrayList<>();
            List<HashSet<Integer>> recommendedMovies = new ArrayList<>();  
            recommendedMovies.add(movieIds);
            for(int j = 0; j < recommendedMovies.size(); j++)
                System.out.print(recommendedMovies.get(j));
            System.out.println();
        } 
        //System.out.println("just try " + allDataUser.get(0).getViewed().indexOf(Integer.toString(22)));
       
       
    }
    public static List<String> movieGenres(int id){
        List<String> genres = new ArrayList<>();
               genres.add(movieProducts.get(id-1).getKeyword1());
               genres.add(movieProducts.get(id-1).getKeyword2());
               genres.add(movieProducts.get(id-1).getKeyword3());
               genres.add(movieProducts.get(id-1).getKeyword4());
               genres.add(movieProducts.get(id-1).getKeyword5());
        return genres;
    }
    //i just intialized recommendedMovieId with 100, also there is no movie id with it
    public static int recommendedMovies(int userId,String genre){
        Random rand1 = new Random();
        
        int recommendedMovieId = 100;
        int randomIndex = rand1.nextInt(40);
        
        //check if this movie id already used from users
        if(allDataUser.get(userId).getViewed().indexOf(Integer.toString(randomIndex+1))>=0){
           while(allDataUser.get(userId).getViewed().indexOf(Integer.toString(randomIndex+1))>=0){
               randomIndex = rand1.nextInt(40);
           }
        }
        
        if(movieProducts.get(randomIndex).getKeyword1().equals(genre)||
           movieProducts.get(randomIndex).getKeyword2().equals(genre)||  
           movieProducts.get(randomIndex).getKeyword3().equals(genre)||  
           movieProducts.get(randomIndex).getKeyword4().equals(genre)|| 
           movieProducts.get(randomIndex).getKeyword5().equals(genre))
        {
             recommendedMovieId = randomIndex + 1;
           }
        else 
            recommendedMovieId = recommendedMovies(userId, genre);
        return recommendedMovieId;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import connection.DBConnection;
import java.sql.*;
import java.util.ArrayList;


/**
 *
 * @author taher
 */
public class MovieItem {
    private int id;
    private String name;
    private String actor;
    private String rate;
    private String poster;
    private String overview;
    private String rentalPrice;
    private int reviewId;
    private int tailerId;
    private int numberOfMovies;
    private String releaseDate;
    private String directorName;
    private ArrayList<String> category;
        private String Link;//bdrdr
    private String NameOfcategory;//bdrdr

    public MovieItem(int id, String name, String actor, String rate, String poster, String overview
            , String rentalPrice, int reviewId, int tailerId, int numberOfMovies, String releaseDate, String directorName
            , ArrayList<String> category) {
        this.id = id;
        this.name = name;
        this.actor = actor;
        this.rate = rate;
        this.poster = poster;
        this.overview = overview;
        this.rentalPrice = rentalPrice;
        this.reviewId = reviewId;
        this.tailerId = tailerId;
        this.numberOfMovies = numberOfMovies;
        this.releaseDate = releaseDate;
        this.directorName = directorName;
        this.category = category;
    }
    
    public MovieItem(int id, String name, String actor, float rate, String poster, String overview
            , float rentalPrice, int reviewId, int tailerId, int numberOfMovies, String releaseDate, String directorName
            , ArrayList<String> category) {
        this.id = id;
        this.name = name;
        this.actor = actor;
        this.rate = Float.toString(rate);
        this.poster = poster;
        this.overview = overview;
        this.rentalPrice = Float.toString(rentalPrice) ;
        this.reviewId = reviewId;
        this.tailerId = tailerId;
        this.numberOfMovies = numberOfMovies;
        this.releaseDate = releaseDate;
        this.directorName = directorName;
        this.category = category;
    }
      public MovieItem(int id, String name, String actor, float rate, String poster, String overview//bdrddr
            , float rentalPrice,  String releaseDate, String directorName
            ) {
        this.id = id;
        this.name = name;
        this.actor = actor;
        this.rate = Float.toString(rate);
        this.poster = poster;
        this.overview = overview;
        this.rentalPrice = Float.toString(rentalPrice);
        this.releaseDate = releaseDate;
        this.directorName = directorName;
        
    }
    
    public MovieItem( String Link) {//bdrdr
        
        this.Link = Link;
    }
     public MovieItem( int id,String NameOfcategory) {//bdrdr
        this.id = id;
        this.NameOfcategory = NameOfcategory;
    }
     
     public String getLink()
     {
         return this.Link;
     }
    
    public static ArrayList<MovieItem> updateAdmin(int id,String divName, String value ) {//bdrdr
        
        ArrayList<MovieItem> result = new ArrayList<>();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection Con = null;
            Statement Stmt = null;
            ResultSet RS = null;

            Con = DBConnection.createConnection();
            Stmt = Con.createStatement();


            Stmt.executeUpdate(
                    


                    "UPDATE movie SET "+divName+"='"+value+"' where id='"+id+"';"
            );
            
           DBConnection.closeConnection();
        
        } catch (Exception cnfe) {
            System.err.println("Exception: " + cnfe);
        }
        
        return result;
    }
    
     public void setLink(String Link) {//bdrdr
        this.Link = Link;
    }

    public String getNameOfcategory() {//bdrdr
        return NameOfcategory;
    }

    public void setNameOfcategory(String NameOfcategory) {//bdrdr
        this.NameOfcategory = NameOfcategory;
    }
    
    public MovieItem(int id, String poster, String name, ArrayList<String> category) {
        this.id = id;
        this.poster = poster;
        this.name = name;
        this.category = category;
    }

    public MovieItem(int id, String poster, String name, String releaseDate, String rate, String actor) {
        this.id = id;
        this.poster = poster;
        this.name = name;
        this.releaseDate = releaseDate;
        this.rate = rate;
        this.actor = actor;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(String rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getTailerId() {
        return tailerId;
    }

    public void setTailerId(int tailerId) {
        this.tailerId = tailerId;
    }

    public int getNumberOfMovies() {
        return numberOfMovies;
    }

    public void setNumberOfMovies(int numberOfMovies) {
        this.numberOfMovies = numberOfMovies;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }
    
    public boolean insert(){
        return false;
    }
    
    public static ArrayList<MovieItem> selectAll (){
        return null;
    }
    
    public static ArrayList<MovieItem> selectByID (int id){//change password and see what if function constractor 
       ArrayList<MovieItem> result = new ArrayList<>();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        
            
            Connection Con = null;
            Statement Stmt = null;
            ResultSet RS = null;

            Con = DBConnection.createConnection();
            Stmt = Con.createStatement();
            RS = Stmt.executeQuery(
                    "select ID, Name, Actor, rate, Poster, Overview, rentalPrice, releaseDate, DirectorName "
                            + "from movie where ID='"+id+"';"
            );
            
            while(RS.next())
            {
                MovieItem item = new MovieItem(RS.getInt("ID"), RS.getString("Name"),RS.getString("Actor"),RS.getFloat("rate"),RS.getString("Poster"), RS.getString("Overview"),RS.getFloat("rentalPrice"),RS.getString("releaseDate"),RS.getString("DirectorName"));
                result.add(item);
            }
        
            DBConnection.closeConnection();
            
        } catch (Exception cnfe) {
            System.err.println("Exception: " + cnfe);
        }
        
        return result;
    }


    public ArrayList<String> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<String> category) {
        this.category = category;
    }
    
    public static ArrayList<MovieItem> selectAllUrlsAndIds() {
        
        ArrayList<MovieItem> result = new ArrayList<>();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        
            Connection Con = null;
            Statement Stmt = null;
            ResultSet RS = null;

            Con = DBConnection.createConnection();
            Stmt = Con.createStatement();
            RS = Stmt.executeQuery(
                    "select ID, Poster, Name, releaseDate, rate, Actor from Movie;"
            );
            
            while(RS.next()){
                MovieItem item = new MovieItem(RS.getInt("ID"), RS.getString("Poster"), RS.getString("Name")
                        ,  RS.getString("releaseDate"),  RS.getString("rate"),  RS.getString("Actor"));
                setCategory(item);
                result.add(item);
            }
            DBConnection.closeConnection();
            
            
        } catch (Exception cnfe) {
            System.err.println("Exception: " + cnfe);
        }
        
        return result;
    }
    
    public boolean update(int id, String name, String actor, String rate, String poster, String overview
            , String rentalPrice, int reviewId, int tailerId, int numberOfMovies, String releaseDate, String directorName){
        return false;
    }
    
    private static void setCategory (MovieItem item) {
        ArrayList<String> result = new ArrayList<>();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        
            Connection Con = null;
            Statement Stmt = null;
            ResultSet RS = null;

            Con = DBConnection.createConnection();
            Stmt = Con.createStatement();
            
            
            RS = Stmt.executeQuery(
                    "select Name from "
                            + "("
                            + "select Category.Name, CategoryMovie.MovieID from Category "
                            + "Inner JOIN CategoryMovie "
                            + "ON  CategoryMovie.CategoryID = Category.ID"
                            + ") AS T where MovieID = "+  item.getId() +";"
            );
            
            while(RS.next()){
                result.add(RS.getString("Name"));
            }
            
            DBConnection.closeConnection();
        
        } catch (Exception cnfe) {
            System.err.println("Exception: " + cnfe);
        }
        
        item.setCategory(result);
    }
    
    
    public static ArrayList<MovieItem> getTrailers(int id) {//bdrdr
        
        ArrayList<MovieItem> result = new ArrayList<>();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        
            Connection Con = null;
            Statement Stmt = null;
            ResultSet RS = null;

            Con = DBConnection.createConnection();
            Stmt = Con.createStatement();


            RS = Stmt.executeQuery(
                    
                    "select Link from tailer where MovieID='"+id+"';"
            );
            
            while(RS.next()){
                MovieItem item = new MovieItem(RS.getString("Link"));
                result.add(item);
            }
            
            DBConnection.closeConnection();
        
        } catch (Exception cnfe) {
            System.err.println("Exception: " + cnfe);
        }
        
        return result;
    }
    public static ArrayList<MovieItem> getCategorys(int id) {//change name to name of category from DB
        
        ArrayList<MovieItem> result = new ArrayList<>();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        
            Connection Con = null;
            Statement Stmt = null;
            ResultSet RS = null;

            Con = DBConnection.createConnection();
            Stmt = Con.createStatement();


            RS = Stmt.executeQuery(
                    
                    "select ID,category.Name from  category where ID IN (select CategoryID from categorymovie where MovieID='"+id+"');"
            );
            
            while(RS.next()){
                MovieItem item = new MovieItem(RS.getInt("ID"),RS.getString("category.Name"));
                result.add(item);
            }
            
            DBConnection.closeConnection();
        
        } catch (Exception cnfe) {
            System.err.println("Exception: " + cnfe);
        }
        
        return result;
    }
    
    public static ArrayList<MovieItem> selectRentMovies (int user_id) {
        ArrayList<MovieItem> result = new ArrayList<>();
 
        try {
            Class.forName("com.mysql.jdbc.Driver");
 
            Connection Con = null;
            Statement Stmt = null;
            ResultSet RS = null;
 
            Con = DBConnection.createConnection();
            Stmt = Con.createStatement();
            RS = Stmt.executeQuery(
                    "select MovieID from rentingData where IDUser = " + user_id + ";"
            );
 
            while(RS.next()){
 
                int id = RS.getInt("MovieID");
 
 
                result.addAll(selectByID(id));
            }
            DBConnection.closeConnection();
 
 
        } catch (Exception cnfe) {
            System.err.println("Exception: " + cnfe);
        }
 
        return result;
    }
    
}

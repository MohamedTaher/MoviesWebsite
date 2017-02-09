/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author mohamed2
 */
public class RentingData 
{
    private int movieID;
    private int userID;
    private String startTime;
    private String expiredTime;
    private int returned;
    private int sendMail;
    
    public RentingData()
    {
        movieID = userID = returned = sendMail = 0;
        startTime = new String();
        expiredTime = new String();
    }
    public RentingData(int movieid , int userid , String starttime , String expiredtime , int returned_ , int sendmail)
    {
        movieID = movieid;
        userID = userid;
        startTime = starttime;
        expiredTime = expiredtime;
        returned = returned_;
        sendMail = sendmail;
    }
    
    public void setMovieID(int movieid)
    {
        movieID = movieid;
    }
    public void setUserID(int userid)
    {
        userID = userid;
    }
    public void setReturned(int returned_)
    {
        returned = returned_;
    }
    public void setSendMail(int sendmail)
    {
        sendMail = sendmail;
    }
    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }
    public void setExpiredTime(String expiredTime)
    {
        this.expiredTime = expiredTime;
    }
    
    
}

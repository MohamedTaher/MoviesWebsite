/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;



class Admin extends User
{
    private boolean type;
    
    public Admin()
    {
        type = false;
    }
    public void setType(boolean type)
    {
        this.type = type;
    }
    public boolean getType()
    {
        return type;
    }
    
}

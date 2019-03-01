package com.maindbpackage.venkat.bdpackage;

/**
 * Created by venkat on 7/7/2018.
 */

public class Blood {
    private String name;
    private String bgrp;
    private String phno;





    private String loc_c;
   private String loc_d;
   private String loc_p;


    public Blood(String name, String bgrp, String phno,  String loc_c, String loc_d, String loc_p){
        this.name=name;
        this.phno=phno;
        this.bgrp=bgrp;
        this.loc_c=loc_c;
        this.loc_d=loc_d;
        this.loc_p=loc_p;
    }
    public String getname(){return name;}
    public String getBgrp(){return bgrp;}
    public String getPhno(){return phno;}
   public String getLoc_country(){return loc_c;}
    public String getLoc_dist(){return loc_d;}
   public String getLoc_pin(){return loc_p;}


    public void setName(String name){this.name=name;}
    public void setBgrp(String bgrp){this.bgrp=bgrp;}
    public void setPhno(String phno){this.phno=phno;}

  public void setLoc_country(String loc_c){this.loc_c=loc_c;}
   public void setLoc_dist(String loc_d){this.loc_d=loc_d;}
   public void setLoc_pin(String loc_p){this.loc_p=loc_p;}


}

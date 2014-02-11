package com.success_v1.vehicule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
public class MyFragmentPagerAdapter extends FragmentPagerAdapter{
  
 final int PAGE_COUNT = 3;
  
 public MyFragmentPagerAdapter(FragmentManager fm) {
  super(fm);
   
 }
 
 /** This method will be invoked when a page is requested to create */
 @Override
 public Fragment getItem(int arg0) {
  Bundle data = new Bundle();
  switch(arg0){
   
   /** Android tab is selected */
   case 0:
    vehiculeUrbain vehiculeUrbain = new vehiculeUrbain();   
    data.putInt("current_page", arg0+1);
    vehiculeUrbain.setArguments(data);
    return vehiculeUrbain;
     
   case 1:
	   vehiculeCompacte vehiculeCompacte = new vehiculeCompacte();   
	   data.putInt("current_page", arg0+1);
	   vehiculeCompacte.setArguments(data);
	   return vehiculeCompacte;
	   
   case 2:
	   vehiculeCompacte vehiculeSportive = new vehiculeCompacte();   
	   data.putInt("current_page", arg0+1);
	   vehiculeSportive.setArguments(data);
	   return vehiculeSportive;
  }
  
  
   
  return null;
 }
 
 /** Returns the number of pages */
 @Override
 public int getCount() { 
  return PAGE_COUNT;
 }
  
}
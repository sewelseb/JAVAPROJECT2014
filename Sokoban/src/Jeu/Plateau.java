package Jeu;

import java.lang.reflect.Array;
import java.util.Scanner;
import java.io.*; 

public class Plateau {
	
	private Case tabCases [][];

	public Case getTabCases(Integer x, Integer y) 
		{
			return tabCases[x][y];
		}

	//x et y représentent les dimentions du tableau
	public void setTabCases(Integer x, Integer y)
		{
			
			for (int i = 0; i < y; i++) { //verticale
				for (int j = 0; j < x; j++) { //horizontale
					
					tabCases[i][j] = new Case(" ");
				}
			}
			this.tabCases = tabCases;
		}
	
	public Plateau()
		{
			
		}
	
	public void chargerPlateauDepuisFichier(String fichier)
		{
			String chaine="";
			
			
			 Scanner sc = null;
			 try
				 {
					 sc = new Scanner(new File(fichier));
			         while (sc.hasNextLine()) 
			             {
			                 for (char c : sc.next().toCharArray()) 
				                 {
				                     
				                 }
			             }
				 }
			 catch (Exception e)
				{
					System.out.println(e.toString());
				}
			
		}
	
	

}

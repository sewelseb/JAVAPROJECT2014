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
			this.tabCases = new Case [y][x];
			for (int i = 0; i < y; i++) { //verticale
				for (int j = 0; j < x; j++) { //horizontale
					
					tabCases[i][j] = new Case(" ");
				}
			}
			this.tabCases = tabCases;
		}
	public void setTabCasesAvecValeur(Integer x, Integer y, String lettre)
		{
			
					System.out.println(lettre);
					this.tabCases[x][y].setEtat(lettre);
				
			
		}
	public Plateau()
		{
			
		}
	
	public void chargerPlateauDepuisFichier(String fichier)
		{
			
			Integer i=0;
			Integer j=0;
			Integer x=0;
			Integer y=0;
			
			 Scanner sc = null;
			 try
				 {
					 sc = new Scanner(new File(fichier));
					 while (sc.hasNextLine()) 
		             {
						 y++;
		                 for (char c : sc.next().toCharArray()) 
			                 {
		                	 	x++;
			                 }
		             }
					 this.setTabCases(x, y);
			         while (sc.hasNextLine()) 
			             {
			                 for (char c : sc.next().toCharArray()) 
				                 {
			                	 	String s = Character.toString(c);
			                	 	System.out.println(s);
			                	 	setTabCasesAvecValeur(i, j, s);
				                 }
			             }
			         sc.close();
				 }
			 catch (Exception e)
				{
					System.out.println(e.toString());
				}
			
		}
	
	

}

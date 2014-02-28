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

	//x et y repr�sentent les dimentions du tableau
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
			
					
					this.tabCases[y][x].setEtat(lettre);
				
			
		}
	public Plateau()
		{
			
		}
	public Plateau(String fichier)
		{
			this.chargerPlateauDepuisFichier(fichier);
		}
	
	public void chargerPlateauDepuisFichier(String fichier)
		{
			
			Integer i=0;
			Integer j=0;
			Integer x=0;
			Integer y=0;
			Integer xMax=0;
			
			 Scanner sc = null;
			 //prise des dimentions maximales du tableau pour l'initialier
			 try
				 {
					 sc = new Scanner(new File(fichier));
					 while (sc.hasNextLine()) 
		             {
						 y++;
						 x=0;
		                 for (char c : sc.next().toCharArray()) 
			                 {
		                	 	
		                	 	x++;
		                	 	
			                 }
		                 if (x>xMax)
			                 {
			                	xMax=x; 
			                 }
		             }
					 
					 this.setTabCases(xMax, y);
					 sc.close();
					 //remplissage du tableau
					 sc = new Scanner(new File(fichier));
					 //System.out.println("yMax:");
					 //System.out.println(y);
					 //System.out.println("xMax:");
					 //System.out.println(xMax);
			         while (sc.hasNextLine()) 
			             {
			        	 	
			        	 	j=0;
			                 for (char c : sc.next().toCharArray()) 
				                 {
			                	 	String s = Character.toString(c);
			                	 	//System.out.println(s);
			                	 	setTabCasesAvecValeur(j, i, s);
			                	 	j++;
			                	 	//System.out.println("j:");
					                //System.out.println(j);
				                 }
			                 //System.out.println("j sortie de boucle:");
			                 //System.out.println(j);
			                 for (Integer n=j; n<xMax ; n++)
				                 {
			                	 	//System.out.println(n);
			                	 	setTabCasesAvecValeur(n, i, ".");
				                 }
			                 
			                 i++;
			             }
			         sc.close();
				 }
			 catch (Exception e)
				{
					System.out.println(e.toString());
				}
			
		}
	// renvoie un array avec en [0] y et en [1] x
	public Integer[] dimentionDeTabCases()
		{
			Integer retour[]= new Integer [2];
			//System.out.println(this.tabCases.length);
			retour[0]=(this.tabCases.length);
			retour[1]=0;
			for (Integer i=0; i<retour[0]; i++)
				{
					//System.out.println(i);
					if (retour[1]<this.tabCases[i].length)
						{
							retour[1]=(this.tabCases[i].length);
						}
				}
			
			return retour;
		}
	public void afficherPlateau()
		{
			Integer[] dimention = this.dimentionDeTabCases();
			String ligne="";
			String caractere="";
			for (Integer i=0; i<dimention[0]; i++)
				{
					ligne="";
					for (Integer j=0; j<dimention[1]; j++)
						{
							
							if (this.getTabCases(i, j).getEtat().equals("."))
								{
									caractere=" ";
								}
							else
								{
									caractere=this.getTabCases(i, j).getEtat();
								}
							ligne+=caractere;
						}
					System.out.println(ligne);
				}
		}
	
	
	public int jeu(){ /*Logique d'un jeu complet.*/
		int[] coorJoueur=trouveJoueur();
		int[][] listeObjectifs=trouverObjectifs();
		int command=0;
		Scanner sc = new Scanner(System.in);
		do{
			command = Integer.parseInt(sc.next());
			this.move(coorJoueur, command);
			this.afficherPlateau();
			if (conditionVictoire(listeObjectifs)){
				System.out.println("GAGNE!!!");
				command=5;
			}
		} while (command!=5);
		return 0;
	}
	
	public boolean conditionVictoire(int[][] listeObjectifs) /*V�rifie si les storages sont remplis*/
	{
		for (int c=0; c < listeObjectifs.length; c++){
			if (!(this.getTabCases(listeObjectifs[c][0], listeObjectifs[c][1]).getEtat().equals("B"))){
				return false;
			}
		}
		return true;
	}
	
	public int[][] trouverObjectifs(){ /*Situer les objectifs dans le jeu. Peut �tre rapidement r�-adapter si on pr�voit le cas box d�j� au-dessus d'un store*/
		Integer[] dimension=this.dimentionDeTabCases();
		int count=0;
 		for (int i=0;i < dimension[0]; i++){
			for (int j=0; j < dimension[1]; j++){
				if (this.getTabCases(i, j).getEtat().equals("O"))
					count++;
			}
 		}
 		int[][] listeObjectifs= new int[count][2];
 		count=0;
 		for (int x=0;x < dimension[0]; x++){
			for (int y=0; y < dimension[1]; y++){
				if (this.getTabCases(x, y).getEtat().equals("O"))
				{
					listeObjectifs[count][0]=x;
					listeObjectifs[count][1]=y;
					count++;
				}
			}
 		}
 		return listeObjectifs;
	}
	
	public void move(int[] coorJoueur,int dir) /*Algo de mouvement*/
	{
		switch (dir){
		default: System.out.println("Ah,ah,ah.Petit malin."); break;
			
		/*Vers le haut*/
		case 8: 
			if(this.getTabCases(coorJoueur[0]-1, coorJoueur[1]).getEtat().equals("="))
			{
				System.out.println("Votre t�te rencontre un mur. Ouch.");
			}
			else if(this.getTabCases(coorJoueur[0]-1, coorJoueur[1]).getEtat().equals("B"))
			{
				if(this.getTabCases(coorJoueur[0]-2, coorJoueur[1]).getEtat().equals("=") || this.getTabCases(coorJoueur[0]-2, coorJoueur[1]).getEtat().equals("B"))
				{
					System.out.println("La boite ne bouge pas.");
				}
				else{
					this.getTabCases(coorJoueur[0]-1, coorJoueur[1]).setEtat(".");
					this.getTabCases(coorJoueur[0]-2, coorJoueur[1]).setEtat("B");
				}
			}
			else
			{
				this.getTabCases(coorJoueur[0], coorJoueur[1]).setEtat(".");
				coorJoueur[0]--;
				this.getTabCases(coorJoueur[0], coorJoueur[1]).setEtat("X");
			}
			break;
			
		/*Vers la droite*/
		case 6: 
			if(this.getTabCases(coorJoueur[0], coorJoueur[1]+1).getEtat().equals("="))
			{
				System.out.println("Votre t�te rencontre un mur. Ouch.");
			}
			else if(this.getTabCases(coorJoueur[0], coorJoueur[1]+1).getEtat().equals("B"))
			{
				if (this.getTabCases(coorJoueur[0], coorJoueur[1]+2).getEtat().equals("=") || this.getTabCases(coorJoueur[0], coorJoueur[1]+2).getEtat().equals("B"))
				{
					System.out.println("La boite ne bouge pas.");
				}
				else
				{
					this.getTabCases(coorJoueur[0], coorJoueur[1]+1).setEtat(".");
					this.getTabCases(coorJoueur[0], coorJoueur[1]+2).setEtat("B");
				}
			}
			else
			{
				this.getTabCases(coorJoueur[0], coorJoueur[1]).setEtat(".");
				coorJoueur[1]++;
				this.getTabCases(coorJoueur[0], coorJoueur[1]).setEtat("X");
			}
			break;
			
		/*Vers le bas*/
		case 2: 
			if(this.getTabCases(coorJoueur[0]+1, coorJoueur[1]).getEtat().equals("="))
			{
				System.out.println("Votre t�te rencontre un mur. Ouch.");
			}
			else if(this.getTabCases(coorJoueur[0]+1, coorJoueur[1]).getEtat().equals("B"))
			{
				if(this.getTabCases(coorJoueur[0]+2, coorJoueur[1]).getEtat().equals("=") || this.getTabCases(coorJoueur[0]+2, coorJoueur[1]).getEtat().equals("B"))
				{
					System.out.println("La boite ne bouge pas.");
				}
				else{
					this.getTabCases(coorJoueur[0]+1, coorJoueur[1]).setEtat(".");
					this.getTabCases(coorJoueur[0]+2, coorJoueur[1]).setEtat("B");
				}
			}
			else
			{
				this.getTabCases(coorJoueur[0], coorJoueur[1]).setEtat(".");
				coorJoueur[0]++;
				this.getTabCases(coorJoueur[0], coorJoueur[1]).setEtat("X");
			}
			break;
		
		/*Vers la gauche*/
		case 4: 
			if(this.getTabCases(coorJoueur[0], coorJoueur[1]-1).getEtat().equals("="))
			{
				System.out.println("Votre t�te rencontre un mur. Ouch.");
			}
			else if(this.getTabCases(coorJoueur[0], coorJoueur[1]-1).getEtat().equals("B"))
			{
				if (this.getTabCases(coorJoueur[0], coorJoueur[1]-2).getEtat().equals("=") || this.getTabCases(coorJoueur[0], coorJoueur[1]-2).getEtat().equals("B"))
				{
					System.out.println("La boite ne bouge pas.");
				}
				else
				{
					this.getTabCases(coorJoueur[0], coorJoueur[1]-1).setEtat(".");
					this.getTabCases(coorJoueur[0], coorJoueur[1]-2).setEtat("B");
				}
			}
			else
			{
				this.getTabCases(coorJoueur[0], coorJoueur[1]).setEtat(".");
				coorJoueur[1]--;
				this.getTabCases(coorJoueur[0], coorJoueur[1]).setEtat("X");
			}
			break;
		}
		return;
	}

	public int[] trouveJoueur() /*Permet de situer rapidement la position du joueur dans le plateau*/
	{ 
		int[] coorJoueur=new int[2];
		Integer[] dimension=this.dimentionDeTabCases();
 		for (int i=0;i < dimension[0]; i++){
			for (int j=0; j < dimension[1]; j++){
				if (this.getTabCases(i, j).getEtat().equals("X")){
					coorJoueur[0]=i;
					coorJoueur[1]=j;
				}
			}
		}
		return coorJoueur;
	}
}

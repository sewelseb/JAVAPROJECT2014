package Jeu;

public class Case {
	private String etat;

	public String getEtat() {
		
		String etat= this.etat;
		
		if (etat != "X" && etat != "O" && etat != "B" && etat != "=" && etat != " ")
		{
			this.setEtat(" ");
		}
		return this.etat;
	}

	public void setEtat(String etat) {
		System.out.println(etat);
		String etatFinal;
		if (etat != "X" && etat != "O" && etat != "B" && etat != "=" && etat != " ")
		{
			etatFinal = " ";
		}
		else
		{
			etatFinal = etat;
		}
		this.etat = etatFinal;
	}
	
	public Case (String etat)
	{
		setEtat(etat);
		
	}
	

}

package Jeu;

public class Case {
	private String etat;

	public String getEtat() {
		
		String etat= this.etat;
		
		if (etat.equals("X") || etat.equals("O") || etat.equals("B") || etat.equals("=") || etat.equals(" ") || etat.equals("S")|| etat.equals("."))
		{
			
		}
		else
		{
			//this.setEtat(" ");
		}
		
		return this.etat;
	}

	public void setEtat(String etat) {
		
		String etatFinal;
		
		if (etat.equals("X") || etat.equals("O") || etat.equals("B") || etat.equals("=") || etat.equals(" ") || etat.equals("S")|| etat.equals("."))
		{
			
			etatFinal = etat;
		}
		else
		{
			System.out.println(etat);
			etatFinal = " ";
		}
		System.out.println(etatFinal);
		this.etat = etatFinal;
	}
	
	public Case (String etat)
	{
		setEtat(etat);
		
	}
	

}


public class Artikel {
	private String[] forfattere;
	private String titel;
	private Tidsskrift tidsskrift;
	private Artikel[] referenceliste;
	
	public Artikel(String[] forfattere, String titel, Tidsskrift tidsskrift) {
		this.forfattere=forfattere;
		this.titel=titel;
		this.tidsskrift=tidsskrift;
		
	}
	
	public void setReferenceliste(Artikel[] referenceliste) {
		this.referenceliste=referenceliste;
	}
	
	public String toString() {
		String s="";
		for(String forfatter : forfattere) {
			s+=forfatter + ", ";
		}
		s+="\""+titel+"\"";
		s+=", " + tidsskrift;
		return s;
	}

}

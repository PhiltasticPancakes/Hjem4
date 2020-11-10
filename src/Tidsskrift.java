
public class Tidsskrift {
	private String titel;
	private Forlag forlag;
	private String issn;
	
	public Tidsskrift(String titel) {
		this.titel=titel;
	}
	
	public void setForlag(Forlag forlag){
		this.forlag=forlag;
	}
	
	public void setIssn(String issn) {
		this.issn=issn;
	}
	
	public String toString() {
		return titel;
	}
}

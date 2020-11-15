
public class Tidsskrift {
	private String titel;
	private Forlag forlag = new Forlag("unknown", "unknown");
	private String issn = "unknown";
	
	public Tidsskrift(String titel) {
		this.titel=titel;
		titel = titel == null || titel == "" ? titel = "unknown" : titel;
	}
	
	public void setForlag(Forlag forlag){
		this.forlag=forlag;
		this.forlag = (forlag == null) ? new Forlag("unknown", "unknown") : forlag;
	}
	
	public void setIssn(String issn) {
		this.issn=issn;
		issn = issn == null || issn == "" ? "unknown" : issn;
	}
	
	public String toString() {
		return titel + ", " + forlag + ", " + issn;
	}
	public static void main(String[] args) {
		Tidsskrift tidsskrift = new Tidsskrift("titel");
		System.out.println(tidsskrift);
	}
}


public class Forlag {
	private String navn;
	private String sted;
	
	public Forlag(String navn, String sted) {
		this.navn=navn;
		this.sted=sted;
		this.navn = this.navn == null || this.navn == "" ? "unknown" : this.navn;
		this.sted = this.sted == null || this.sted == "" ? "unknown" : this.sted;
	}
	
	public String toString() {
		return navn + ", " + sted;
	}
}


public class ArtikelTest {
	public static void main(String[] args) {
		Forlag forlag1 = new Forlag("University Press", "Denmark");
		Tidsskrift[] tidsskrifter = {new Tidsskrift("Journal of Logic"), new Tidsskrift("Brain")};
		for(Tidsskrift tidsskrift: tidsskrifter) {
			tidsskrift.setForlag(forlag1);
		}
		String[] forfattere1= {"A. Abe", "A. Turning"};
		String[] forfattere2= {"B. Bim"};
		Artikel artikel1 = new Artikel(forfattere1, "A", tidsskrifter[0]);
		Artikel artikel2 = new Artikel(forfattere2, "B", tidsskrifter[1]);
		Artikel[] reflist1 = {artikel2};
		artikel1.setReferenceliste(reflist1);
		System.out.println(artikel1);
	}
}

package exampleeight;
import java.lang.Double;
public class Attori {
	
	private String lastname;
	private Double valutazione;
	public Attori() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Attori(String lastname, Double valutazione) {
		super();
		this.lastname = lastname;
		this.valutazione = valutazione;
	}

	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Double getValutazione() {
		return valutazione;
	}
	public void setValutazione(Double valutazione) {
		this.valutazione = valutazione;
	}
	
	

}

package examplefour;

public class Impiegati {
	
	private String impiegato;
	private String departimento;
	private Double value;
	public Impiegati(String impiegato, String departimento, Double value) {
		super();
		this.impiegato = impiegato;
		this.departimento = departimento;
		this.value = value;
	}
	public String getImpiegato() {
		return impiegato;
	}
	public void setImpiegato(String impiegato) {
		this.impiegato = impiegato;
	}
	public String getDepartimento() {
		return departimento;
	}
	public void setDepartimento(String departimento) {
		this.departimento = departimento;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
	

}

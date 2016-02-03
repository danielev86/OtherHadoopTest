package examplesix;
import java.lang.*;
public class Ciclista {
	private String nome;
	private String cognome;
	private Double value;
	
	public Ciclista(String nome, String cognome, Double value) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.value = value;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
}

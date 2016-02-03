package examplefive;

public class PairProdotti {
	
	private String prodotto;
	private Double value;
	public PairProdotti() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PairProdotti(String prodotto, Double value) {
		super();
		this.prodotto = prodotto;
		this.value = value;
	}
	public String getProdotto() {
		return prodotto;
	}
	public void setProdotto(String prodotto) {
		this.prodotto = prodotto;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
	

}

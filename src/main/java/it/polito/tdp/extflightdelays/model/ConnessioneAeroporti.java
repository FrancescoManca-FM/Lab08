package it.polito.tdp.extflightdelays.model;

public class ConnessioneAeroporti {

	private String id;
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	private Airport partenza;
	private Airport arrivo;
	private int nVoli;
	private double distanzaMedia;
	
	
	public ConnessioneAeroporti(Airport partenza, Airport arrivo, int nVoli, double distanzaMedia) {
		super();
		this.id = partenza.getId()+"-"+arrivo.getId();
		this.partenza = partenza;
		this.arrivo = arrivo;
		this.nVoli = nVoli;
		this.distanzaMedia = distanzaMedia;
	}


	public Airport getPartenza() {
		return partenza;
	}


	public void setPartenza(Airport partenza) {
		this.partenza = partenza;
	}


	public Airport getArrivo() {
		return arrivo;
	}


	public void setArrivo(Airport arrivo) {
		this.arrivo = arrivo;
	}


	public int getnVoli() {
		return nVoli;
	}


	public void setnVoli(int nVoli) {
		this.nVoli = nVoli;
	}


	public double getDistanzaMedia() {
		return distanzaMedia;
	}


	public void setDistanzaMedia(double distanzaMedia) {
		this.distanzaMedia = distanzaMedia;
	}
	
	
	
}

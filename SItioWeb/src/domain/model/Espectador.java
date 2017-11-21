package domain.model;

public class Espectador {
	
	String informe;
	
	public void notificar(String informe) {
		this.informe = informe;
	}

	public String getInforme() {
		return informe;
	}
}

package locadoras.locadoraDeCarros.enumeration;

public enum TiposDeCarros {

	SOUTHCAR("COMPACTOS", 4, 210, 150, 200, 90), WESTCAR("ESPORTIVOS", 2, 530, 150, 200, 90), NORTHCAR("SUVS", 7, 630,
			580, 600, 590);

	private String tipo;
	private int quantidadeDePessoas;
	private int segundaSextaClienteRegular;
	private int segundaSextaClienteFidelidade;
	private int fimDeSemanaClienteRegular;
	private int fimDeSemanaClienteFidelidade;

	private TiposDeCarros(String tipo, int quantidadeDePessoas, int segundaSextaClienteRegular,
			int segundaSextaClienteFidelidade, int fimDeSemanaClienteRegular, int fimDeSemanaClienteFidelidade) {
		this.tipo = tipo;
		this.quantidadeDePessoas = quantidadeDePessoas;
		this.segundaSextaClienteRegular = segundaSextaClienteRegular;
		this.segundaSextaClienteFidelidade = segundaSextaClienteFidelidade;
		this.fimDeSemanaClienteRegular = fimDeSemanaClienteRegular;
		this.fimDeSemanaClienteFidelidade = fimDeSemanaClienteFidelidade;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getQuantidadeDePessoas() {
		return quantidadeDePessoas;
	}

	public void setQuantidadeDePessoas(int quantidadeDePessoas) {
		this.quantidadeDePessoas = quantidadeDePessoas;
	}

	public int getSegundaSextaClienteRegular() {
		return segundaSextaClienteRegular;
	}

	public void setSegundaSextaClienteRegular(int segundaSextaClienteRegular) {
		this.segundaSextaClienteRegular = segundaSextaClienteRegular;
	}

	public int getSegundaSextaClienteFidelidade() {
		return segundaSextaClienteFidelidade;
	}

	public void setSegundaSextaClienteFidelidade(int segundaSextaClienteFidelidade) {
		this.segundaSextaClienteFidelidade = segundaSextaClienteFidelidade;
	}

	public int getFimDeSemanaClienteRegular() {
		return fimDeSemanaClienteRegular;
	}

	public void setFimDeSemanaClienteRegular(int fimDeSemanaClienteRegular) {
		this.fimDeSemanaClienteRegular = fimDeSemanaClienteRegular;
	}

	public int getFimDeSemanaClienteFidelidade() {
		return fimDeSemanaClienteFidelidade;
	}

	public void setFimDeSemanaClienteFidelidade(int fimDeSemanaClienteFidelidade) {
		this.fimDeSemanaClienteFidelidade = fimDeSemanaClienteFidelidade;
	}

}

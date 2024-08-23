package maven.ex2;

public class Pessoa {
	private String nome;
	private int codigo;
	private int idade;
	
	
	public Pessoa() {
		this.nome = "";
		this.codigo = 1;
		this.idade = 0;
	}
	public Pessoa(String nome, int codigo, int idade) {
		this.nome = nome;
		this.codigo = codigo;
		this.idade = idade;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}


	@Override
	public String toString() {
		return "Pessoa [codigo=" + codigo + ", nome=" + nome + ", idade=" + idade+"]";
	}	
}

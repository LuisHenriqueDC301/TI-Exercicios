package maven.ex2;
import java.sql.*;

public class DAO {
	protected Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "postgres";
		int porta = 5433;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "postgres";
		String password = "123";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	
	public boolean inserirUsuario(Pessoa pessoa) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO pessoa(nome,codigo,idade) VALUES ("+pessoa.getNome()+", "+ pessoa.getCodigo()+", "+pessoa.getIdade() + ");");
			st.close();
			status = true;
		} catch (SQLException u){
			throw new RuntimeException(u);
		}
		return status;
	}
	public boolean atualizarUsuario(Pessoa pessoa) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPTADE pessoa SET nome = '"+pessoa.getNome() + "', idade = '"+pessoa.getNome()+"' WHERE codigo ='"+pessoa.getCodigo();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u){
			throw new RuntimeException(u);
		}
		return status;
	}

}

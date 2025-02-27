package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class BD {
	
	private static Connection conexao = null;
	
	public static Connection conectarBD() { // conectar com BD
		try {
			if(conexao == null) {
				Properties properties = carregarProperties();
				String url = properties.getProperty("dburl");
				//conexao = DriverManager.getConnection(url, properties);
				conexao = DriverManager.getConnection(url, properties);
				System.out.println("Banco de dados conectado...");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		return conexao;
	}
	
	public static void desconectarBD() { // desconectar do BD
		if (conexao != null) {
			try {
				System.out.println("Banco de dados desconectado...");
				conexao.close();
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	private static Properties carregarProperties() { // método que carrega as informações para fazer a conexão
		
		try (FileInputStream arquivoDaConexao = new FileInputStream("db.properties")){
			Properties properties = new Properties();
			properties.load(arquivoDaConexao);
			return properties;
		} 
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static void fecharStatement(Statement st) { // fechar a conexão do Statement
		
		if(st != null) {
			try {
				st.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void fecharResultSet(ResultSet rs) {  // fechar a conexão do ResultSet
		
		if(rs != null) {
			try {
				rs.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
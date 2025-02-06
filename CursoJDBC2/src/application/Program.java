package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.BD;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat formatoDaData = new SimpleDateFormat("dd/MM/yyyy");
		Connection conexao = null;
		PreparedStatement instrucaoSQL = null;
		
		try {
			conexao = BD.conectarBD();
			instrucaoSQL = conexao.prepareStatement(
					"INSERT INTO cursojdbc.seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES (?, ?, ?, ?, ?)"
					);
			instrucaoSQL.setString(1, "João Vitor");
			instrucaoSQL.setString(2, "joao.duarte@gmail.com");
			instrucaoSQL.setDate(3, new java.sql.Date(formatoDaData.parse("10/05/2004").getTime()));
			instrucaoSQL.setDouble(4, 1500.0);
			instrucaoSQL.setInt(5, 1);
			int linhasAlteradas = instrucaoSQL.executeUpdate();
			System.out.println("Banco de dados atualizado com " + linhasAlteradas + " linhas alteradas!");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		finally {
			BD.fecharStatement(instrucaoSQL);
			BD.desconectarBD();
		}
	}
}
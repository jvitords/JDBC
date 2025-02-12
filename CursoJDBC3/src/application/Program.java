package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.BD;

public class Program {

	public static void main(String[] args) {
		
		//SimpleDateFormat formatoDaData = new SimpleDateFormat("dd/MM/yyyy");
		Connection conexao = null;
		PreparedStatement instrucaoSQL = null;
		
		try {
			conexao = BD.conectarBD();
			instrucaoSQL = conexao.prepareStatement("UPDATE seller SET BaseSalary = BaseSalary + ? WHERE (DepartmentId = ?)"); // atualizar dados
			instrucaoSQL.setDouble(1, 200.0);
			instrucaoSQL.setInt(2, 2);
			int linhas = instrucaoSQL.executeUpdate(); // executar as linhas de cima
			System.out.println("Linhas alteradas: " + linhas);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			BD.fecharStatement(instrucaoSQL);
			BD.desconectarBD();
		}
	}
}
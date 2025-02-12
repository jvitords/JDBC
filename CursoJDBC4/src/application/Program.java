package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutionException;


import db.BD;
import db.DbException;

public class Program {

	public static void main(String[] args) {
		
		//SimpleDateFormat formatoDaData = new SimpleDateFormat("dd/MM/yyyy");
		Connection conexao = null;
		Statement instrucaoSQL = null;
		
		try {
			conexao = BD.conectarBD();
			
			conexao.setAutoCommit(false); // quer dizer que ele não pode terminar sua execução no automático
			
			instrucaoSQL = conexao.createStatement(); 
			int linhas1 = instrucaoSQL.executeUpdate("UPDATE seller SET BaseSalary = 4000 WHERE (DepartmentId = 3)");
			
			//int x = 1; if (x < 2) { throw new SQLException("SQL Exception lançada!"); } // fingir um erro
			 
			int linhas2 = instrucaoSQL.executeUpdate("UPDATE seller SET BaseSalary = 3000 WHERE (DepartmentId = 4)");
			
			conexao.commit(); // só realiza os comandos anteriores se chegar até aqui sem estourar nenhuma excessão 
			
			System.out.println("Linhas alteradas: " + linhas1);
			System.out.println("Linhas alteradas: " + linhas2);

		}
		catch (SQLException e) {
			try {
				conexao.rollback(); // chega aqui se estourar alguma exceção
				throw new DbException("Ocorreu um erro, mas o rollBack foi realizado com sucesso!");
			} 
			catch (SQLException e2) {
				throw new DbException("Exception: Erro ao fazer o rollback!");
			}
		}
		
		finally {
			BD.fecharStatement(instrucaoSQL);
			BD.desconectarBD();
		}
	}
}
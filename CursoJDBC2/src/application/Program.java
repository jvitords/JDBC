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
		
		SimpleDateFormat formatoDaData = new SimpleDateFormat("dd/MM/yyyy");
		Connection conexao = null;
		PreparedStatement instrucaoSQL = null;
		
		try {
			conexao = BD.conectarBD();
			/*
			instrucaoSQL = conexao.prepareStatement(
					"INSERT INTO cursojdbc.seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES (?, ?, ?, ?, ?)" // funcionário
					, Statement.RETURN_GENERATED_KEYS 
					);
			instrucaoSQL.setString(1, "João Vitor");
			instrucaoSQL.setString(2, "joao.duarte@gmail.com");
			instrucaoSQL.setDate(3, new java.sql.Date(formatoDaData.parse("10/05/2004").getTime()));
			instrucaoSQL.setDouble(4, 1500.0);
			instrucaoSQL.setInt(5, 1);
			*/
			
			instrucaoSQL = conexao.prepareStatement("INSERT INTO cursojdbc.department (Name) VALUES ('Departamento 1'), ('Departamento 2')" 
					, Statement.RETURN_GENERATED_KEYS);
			int linhasAlteradas = instrucaoSQL.executeUpdate(); // executa a instrução acima e retorna a quantidade de linhas modificadas
			
			if(linhasAlteradas > 0) {
				ResultSet idDoNovoFuncionario = instrucaoSQL.getGeneratedKeys(); // retornar os ID's dos novos funcionários inseridos
				while (idDoNovoFuncionario.next()) {
					int id = idDoNovoFuncionario.getInt("ID"); // pega o ID da coluna "Id"
					System.out.println("ID: " + id);
				}
			}
			else {
				System.out.println("Nenhuma linha foi alterada!");
			}
			
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
package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.BD;

public class Program {

	public static void main(String[] args) {
		
		Connection connection = null; // faz a conexão
		Statement consultaSQL = null; // faz a consulta SQL
		ResultSet resultadoDaConsultaSQL = null; // recebe o resultado da consulta SQL
		
		// fazer a consulta SQL
		try {
			connection = BD.conectarBD();
			consultaSQL = connection.createStatement(); // cria o método p/ fazer a consulta 
			resultadoDaConsultaSQL = consultaSQL.executeQuery("SELECT * FROM department"); // recebe o resultado da consulta feita pelo "Statement"
			
			// mostrar os departamentos encontrados
			while (resultadoDaConsultaSQL.next()){
				System.out.println(resultadoDaConsultaSQL.getInt("Id") + ", " + resultadoDaConsultaSQL.getString("Name")); // mostra o ID e Nome do departamento
			}
			BD.fecharStatement(consultaSQL);
			BD.fecharResultSet(resultadoDaConsultaSQL);
			BD.desconectarBD();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

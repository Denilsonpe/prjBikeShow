
package br.com.bikeshow.dal;

import java.sql.*;

/**
 *
 * @author denilson
 */
public class ModuloConexao {
    //método responsavél por estabelecer a conexão com o banco de dados
    public static Connection conector(){
        java.sql.Connection conexao = null;
        //A linga abaixo "chama" o driver
        String driver = "com.mysql.jdbc.Driver";
        //Armazenando infos referentes ao banco
        String url = "jdbc:mysql://localhost:3306/dbbikeshow";
        String user = "root";
        String password = "";
        //estabelece a conexão com o banco
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            //a linha comentada abaixo serve de apoio para esclarecer o erro
            //System.out.println(e);
            return null;
        }
    }
    
}

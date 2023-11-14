package Daos;

import java.sql.*;
import Beans.trabajadores;
import Daos.DaoBase;

public class trabajadorDao extends DaoBase{
















    public boolean validarUsuarioPasswordHashed(String username, String password){

        String sql = "SELECT * FROM employees_credentials where email = ? and password_hashed = sha2(?,256)";

        boolean exito = false;

        try(Connection connection = getConection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,username);
            pstmt.setString(2,password);

            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    exito = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return exito;
    }









}

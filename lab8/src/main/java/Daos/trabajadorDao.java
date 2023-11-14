package Daos;

import java.sql.*;
import java.util.ArrayList;

import Beans.trabajadores;
import Beans.trabajadores_credenciales;
import DTO.VentasPorTrabajador;
import Daos.DaoBase;

public class trabajadorDao extends DaoBase{

    public trabajadores obtenerTrabajador(String dni){

        trabajadores_credenciales trabajadores_credenciales = null;

        String sql = "Select * from trabajadores_credenciales t \n" +
                "Where t.trabajadores_dni = ? ";

        try (Connection conn = this.getConection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,trabajadores_credenciales);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    trabajadores_credenciales = new trabajadores_credenciales();
                    trabajadores_credenciales.setTrabajadores_dni(rs.getString(1));
                    trabajadores_credenciales.setEmail(rs.getString(2));
                    trabajadores_credenciales.setPassword_hashed(rs.getString(3));
                }
            }
        } catch (SQLException e){
                e.printStackTrace();
        }
        return trabajadores;
    }

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



    public ArrayList<VentasPorTrabajador> listaVentasPorTrabajador() {

        ArrayList<VentasPorTrabajador> lista = new ArrayList<>();

        String sql = "select dniTrabajador as 'DNI', count(idventa) as '# de ventas'" +
                "from ventas" +
                "group by dniTrabajador;";

        try (Connection conn = this.getConection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()){
                VentasPorTrabajador vpt = new VentasPorTrabajador();
                vpt.setDniTrabajador(rs.getString(1));
                vpt.setCantidadVentas(rs.getInt(2));
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return lista;
    }
}

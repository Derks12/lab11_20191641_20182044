package Daos;

import java.sql.*;
import java.util.ArrayList;

import Beans.trabajadores;
import DTO.VentasPorTrabajador;
import Daos.DaoBase;

public class trabajadorDao extends DaoBase{

    public trabajadores obtenerTrabajador(String dni){

        trabajadores trabajadores = null;

        String sql = "Select * from trabajadores t \n" +
                "Where t.dni = ? ";

        try (Connection conn = this.getConection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,dni);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    trabajadores = new trabajadores();
                    trabajadores.setDni(rs.getString(1));
                    trabajadores.setNombres(rs.getString(2));
                    trabajadores.setApellidos(rs.getString(3));
                    trabajadores.setIdsede(rs.getInt(4));
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

package com.grupo8.dao;

import com.grupo8.config.OracleConnectionUtil;
import com.grupo8.models.Correo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CorreoDao {

    // inyeccion sql tabla correo
    public void insertCorreo(Correo correo) throws Exception {
        String sql = "INSERT INTO CORREOS (SUBJECT, MENSAJE, DESTINATARIOS, ID_USUARIO) VALUES (?, ?, ?, ?)";

        try 
        (
            Connection conn = OracleConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) 
        {
            ps.setString(1, correo.getSubject());
            ps.setString(2, correo.getMensaje());
            ps.setString(3, correo.getDestinatarios());
            ps.setString(4, correo.getIdUsuario());

            ps.executeUpdate();
            System.out.println("Correo insertado correctamente.");
        } 
        catch (SQLException e) {
            throw new Exception("Error al insertar correo: " + e.getMessage(), e);
        }
    }

    // get todos los correos de un usuario por id usuario
    public List<Correo> getCorreosByIdUsuario(String idUsuario) throws Exception {
        List<Correo> correos = new ArrayList<>();
        String sql = "SELECT ID, SUBJECT, MENSAJE, DESTINATARIOS, ID_USUARIO FROM CORREOS WHERE ID_USUARIO = ?";

        try 
        (
            Connection conn = OracleConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) 
        {

            ps.setString(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Correo correo = new Correo();
                correo.setId(rs.getInt("ID"));
                correo.setSubject(rs.getString("SUBJECT"));
                correo.setMensaje(rs.getString("MENSAJE"));
                correo.setDestinatarios(rs.getString("DESTINATARIOS"));
                correo.setIdUsuario(rs.getString("ID_USUARIO"));

                correos.add(correo);
            }
        } 
        catch (SQLException e) {
            throw new Exception("Error al obtener correos por ID_USUARIO: " + e.getMessage(), e);
        }

        return correos;
    }
}
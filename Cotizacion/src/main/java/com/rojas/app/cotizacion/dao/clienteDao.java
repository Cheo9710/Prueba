package com.rojas.app.cotizacion.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rojas.app.cotizacion.model.cliente;
import com.rojas.app.cotizacion.model.producto;
@Service
public class clienteDao implements datosBaseDatos{
	
    public List<producto> ejecutaQueryObtenerProducto(String sql,Map<String,String>parametros) {
        String url = "jdbc:h2:file:./DataBaseCotizaciones";
        String user = "sa";
        String password = "sa";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<producto> respuesta = new ArrayList<>();
    	try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.prepareStatement(sql);
            for(int i=0;i<parametros.size();i++) {
            	stmt.setString((i+1), parametros.get((""+i)));
            }
            rs = stmt.executeQuery();
            try {
    			while(rs.next()) {
    				producto p = new producto(rs.getString("MODELO"),rs.getString("TIPO"),rs.getString("DETALLES"),
    						rs.getDouble("PRECIO"));
    				respuesta.add(p);
    			}
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
        }
    	catch (SQLException e) {
            e.printStackTrace();
        }
    	catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    	finally {
            try {
            	if (rs != null)  rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    	return respuesta;
    }
    
    public cliente ejecutaQueryObtenerClienteId(String sql,Map<String,String>parametros) {
        String url = "jdbc:h2:file:./DataBaseCotizaciones";
        String user = "sa";
        String password = "sa";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        cliente resultado = null;
    	try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.prepareStatement(sql);
            for(int i=0;i<parametros.size();i++) {
            	stmt.setString((i+1), parametros.get((""+i)));
            }
            rs = stmt.executeQuery();            
    		try {
    			while(rs.next()) {
	    			resultado = new cliente(rs.getString("NOMBRE"),rs.getString("APELLIDOPATERNO"),
	    					rs.getString("APELLIDOMATERNO"),rs.getString("CELULAR"),rs.getString("CORREOELECTRONICO"));
    			}
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
        }
    	catch (SQLException e) {
            e.printStackTrace();
        }
    	catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    	finally {
            try {
            	if (rs != null)  rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    	return resultado;
    }
    
    public void ejecutaQueryGuardaCliente(String sql,Map<String,String>parametros) {
        String url = "jdbc:h2:file:./DataBaseCotizaciones";
        String user = "sa";
        String password = "sa";
        Connection conn = null;
        PreparedStatement stmt = null;
        //ResultSet rs = null;
    	try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.prepareStatement(sql);
            for(int i=0;i<parametros.size();i++) {
            	stmt.setString((i+1), parametros.get((""+i)));
            }
            stmt.execute();
        }
    	catch (SQLException e) {
            e.printStackTrace();
        }
    	catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    	finally {
            try {
            	//if (rs != null)  rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public Integer ejecutaQueryConsultaIdCelular(String sql,Map<String,String>parametros) {
        String url = "jdbc:h2:file:./DataBaseCotizaciones";
        String user = "sa";
        String password = "sa";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer respuesta = null;
    	try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.prepareStatement(sql);
            for(int i=0;i<parametros.size();i++) {
            	stmt.setString((i+1), parametros.get((""+i)));
            }
            rs = stmt.executeQuery();
            while(rs.next()) {
            	respuesta = rs.getInt("ID");
			}
            
        }
    	catch (SQLException e) {
            e.printStackTrace();
        }
    	catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    	finally {
            try {
            	if (rs != null)  rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    	return respuesta;
    }
    
    public void ejecutaQueryGuardaCotizacion(String sql,Map<String,String>parametros) {
        String url = "jdbc:h2:file:./DataBaseCotizaciones";
        String user = "sa";
        String password = "sa";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
    	try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.prepareStatement(sql);
            for(int i=0;i<parametros.size();i++) {
            	stmt.setString((i+1), parametros.get((""+i)));
            }
            stmt.execute();
        }
    	catch (SQLException e) {
            e.printStackTrace();
        }
    	catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    	finally {
            try {
            	if (rs != null)  rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

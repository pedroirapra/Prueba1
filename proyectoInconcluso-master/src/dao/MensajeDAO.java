package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Mensaje;
import modelo.Usuario;

// REVISAR CONSULTAS SQL // REVISAR CONSULTAS SQL// REVISAR CONSULTAS SQL
// REVISAR CONSULTAS SQL// REVISAR CONSULTAS SQL// REVISAR CONSULTAS SQL

public class MensajeDAO {

    Conexion con;

    public MensajeDAO() {
        this.con = new Conexion();
    }

    public ArrayList<Mensaje> getMensajes(int id) {

        ArrayList<Mensaje> mensajes = new ArrayList<>();
        Connection accesoBD = con.getConexion();

        try {
            String sql = "SELECT * FROM mensaje WHERE id_usr_receptor=" + id;

            //System.out.println(sql);
            Statement st = accesoBD.createStatement();
            ResultSet resultados = st.executeQuery(sql);

            while (resultados.next()) {
                int id_mensaje = resultados.getInt("id_mensaje");
                String contenido = resultados.getString("contenido");
                int id_usr_emisor = resultados.getInt("id_usr_emisor");
                int id_usr_receptor = resultados.getInt("id_usr_receptor");
                
                UsuarioDAO us= new UsuarioDAO();
                //clave foranea
                 mensajes.add(new Mensaje(id_mensaje,us.getUsuarioById(id_usr_emisor),us.getUsuarioById(id_usr_receptor), contenido));
            }
          
            return mensajes;
        } catch (Exception e) {
            System.out.println();
            System.out.println("Error al obtener");
            e.printStackTrace();
            return null;
        }
    }

    public Mensaje getMensajeById(int id) {
        Mensaje m;
        Connection accesoBD = con.getConexion();

        try {
            String sql = "SELECT * FROM mensaje WHERE id_mensaje=" + id + ";";

            Statement st = accesoBD.createStatement(); 
            ResultSet resultados = st.executeQuery(sql); 

            
            if (resultados.first()) {
                int id_mensaje = resultados.getInt("id_mensaje");
                String contenido = resultados.getString("contenido");
                int id_usr_emisor = resultados.getInt("id_usr_emisor");
                int id_usr_receptor = resultados.getInt("id_usr_receptor");
                
                UsuarioDAO us= new UsuarioDAO();
                m=new Mensaje(id_mensaje, us.getUsuarioById(id_usr_emisor),us.getUsuarioById(id_usr_receptor), contenido);

                
//                if (o.getId() == 0) {
//                    System.out.println("No se ha encontrado información sobre el id: " + id);
//                }
                return m;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println();
            System.out.println("Error al obtener");
            e.printStackTrace();
            return null;
        }
    }

    public boolean insertarMensaje(Mensaje m) {
        Connection accesoBD = con.getConexion();

        try {
            String sql="INSERT INTO `mensaje` (`contenido`, `id_usr_emisor`, `id_usr_receptor`) VALUES ('"+m.getContenido()+"',"+m.getEmisor().getId_usuario()+","+m.getRemitente().getId_usuario()+")";
            Statement st = accesoBD.createStatement();
            st.executeUpdate(sql); 

            return true;
        } catch (Exception e) {
            System.out.println("Error al insertar");
            e.printStackTrace();
        }

        return false;
    }
    public boolean editarMensaje( Mensaje m,int id ){
        Connection accesoBD = con.getConexion();
        
        try{
             
            String sql= "UPDATE `mensaje` SET `contenido` = '"+m.getContenido()+"', `id_usr_receptor` = '"+m.getRemitente().getId_usuario()+"' WHERE `mensaje`.`id_mensaje` ="+id;
            Statement st = accesoBD.createStatement();
            st.executeUpdate(sql);
            return true;
        }catch(Exception e){
            System.out.println("Error al editar");
            e.printStackTrace();
        }
        return false;
}
   
    public boolean borrarMensaje( int id ){
        Connection accesoBD = con.getConexion();
        
        
        try{
            String sql= "DELETE FROM `mensaje` WHERE `mensaje`.`id` = "+id;
            Statement st = accesoBD.createStatement();
            st.executeUpdate(sql);
            return true;
        }catch(Exception e){
            System.out.println("Error al borrar");
            e.printStackTrace();
        }
        return false;
        
}
}




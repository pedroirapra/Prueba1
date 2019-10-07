package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Usuario;

// REVISAR CONSULTAS SQL // REVISAR CONSULTAS SQL// REVISAR CONSULTAS SQL
// REVISAR CONSULTAS SQL// REVISAR CONSULTAS SQL// REVISAR CONSULTAS SQL
public class UsuarioDAO {

    Conexion con;

    public UsuarioDAO() {
        this.con = new Conexion();
    }

    public ArrayList<Usuario> getUsuarios() {

        ArrayList<Usuario> usuarios = new ArrayList<>();
        Connection accesoBD = con.getConexion();

        try {
            String sql = "SELECT * FROM usuario ";

            //System.out.println(sql);
            Statement st = accesoBD.createStatement();
            ResultSet resultados = st.executeQuery(sql);

            while (resultados.next()) {
                int id_usuario = resultados.getInt("id_usuario");
                String nombre = resultados.getString("nombre");
                usuarios.add(new Usuario(id_usuario, nombre));
            }

            return usuarios;
        } catch (Exception e) {
            System.out.println();
            System.out.println("Error al obtener");
            e.printStackTrace();
            return null;
        }
    }

    public Usuario getUsuarioById(int id) {
        Usuario u;
        Connection accesoBD = con.getConexion();

        try {
            String sql = "SELECT * FROM usuario WHERE id_usuario=" + id + ";";

            Statement st = accesoBD.createStatement();
            ResultSet resultados = st.executeQuery(sql);

            if (resultados.first()) {
                int id_usuario = resultados.getInt("id_usuario");
                String nombre = resultados.getString("nombre");

                u = new Usuario(id_usuario, nombre);

//                if (o.getId() == 0) {
//                    System.out.println("No se ha encontrado información sobre el id: " + id);
//                }
                return u;
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

    public boolean insertarUsuario(Usuario u) {
        Connection accesoBD = con.getConexion();

        try {
            String sql = "INSERT INTO `usuario` (`nombre`) VALUES ("+u.getNombre()+")";
            Statement st = accesoBD.createStatement();
            st.executeUpdate(sql);

            return true;
        } catch (Exception e) {
            System.out.println("Error al insertar");
            e.printStackTrace();
        }

        return false;
    }

    public boolean editarUsuario(Usuario u, int id) {
        Connection accesoBD = con.getConexion();

        try {
            
            String sql="UPDATE `usuario` SET `nombre` = '"+u.getNombre()+"' WHERE `usuario`.`id_usuario` ="+id;
            Statement st = accesoBD.createStatement();
            st.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error al editar");
            e.printStackTrace();
        }
        return false;
    }
    //DELETE FROM `auto` WHERE `auto`.`id` = 2

    public boolean borrarUsuario(int id) {
        Connection accesoBD = con.getConexion();

        try {
            String sql = "DELETE FROM `usuario` WHERE `usuario`.`id_usuario` = " + id;
            Statement st = accesoBD.createStatement();
            st.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error al borrar");
            e.printStackTrace();
        }
        return false;

    }
}

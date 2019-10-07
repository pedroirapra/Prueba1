/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.MensajeDAO;
import dao.UsuarioDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Mensaje;
import modelo.Usuario;
import vista.NuevoMensaje;

/**
 *
 * @author Pedro Pablo
 */
public class NuevoMensajeController implements ActionListener {

    private NuevoMensaje nm;

    public NuevoMensajeController(NuevoMensaje nm) {
        this.nm = nm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Cancelar")) {
           nm.dispose();
        }
 
        if (e.getActionCommand().equals("Enviar")) {
          
            String mensaje= nm.getCuerpoMensajeTA().getText();
            String receptor=""+nm.getDestinatarioCB().getSelectedItem();
            int id_receptor=nm.getDestinatarioCB().getSelectedIndex()+1;
            
            
            
            MensajeDAO md= new MensajeDAO();
            UsuarioDAO ud= new UsuarioDAO();
            md.insertarMensaje(new Mensaje(ud.getUsuarioById(nm.idUsuarioLogueado),new Usuario(id_receptor, receptor), mensaje));
            JOptionPane.showMessageDialog(nm, "Mensaje Enviado");
            nm.dispose();
            
            
        }
    }
    
}

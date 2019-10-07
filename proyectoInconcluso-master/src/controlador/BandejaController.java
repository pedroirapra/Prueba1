/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.BandejaEntrada;
import vista.NuevoMensaje;

/**
 *
 * @author Pedro Pablo
 */
public class BandejaController implements ActionListener {

    private BandejaEntrada b;

    public BandejaController(BandejaEntrada b) {
        this.b = b;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getActionCommand().equals("nuevoMensaje")) {
        NuevoMensaje nm= new NuevoMensaje(b.idUsuarioLogueado);  
        nm.setVisible(true);
            
        }
        
      
        
        
    }
    
}

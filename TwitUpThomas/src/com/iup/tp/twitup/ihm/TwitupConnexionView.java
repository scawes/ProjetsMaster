package com.iup.tp.twitup.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iup.tp.twitup.action.IGestionConnexion;
import com.iup.tp.twitup.action.IGestionFrame;
import com.iup.tp.twitup.core.Bundle;

public class TwitupConnexionView extends JPanel {

	IGestionFrame gestionFrame;
	IGestionConnexion gestionConnexion;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TwitupConnexionView(IGestionFrame gestionFrame,IGestionConnexion gestionConnexion) {
		this.gestionFrame = gestionFrame;
		this.gestionConnexion	= gestionConnexion;
		initGui();
	}
	
	private void initGui() {
		initChamp();
	}
	
	private void initChamp() {
		this.setLayout(new GridBagLayout());
		JLabel lIdentifiant = new JLabel(Bundle.get("login"));
		JTextField fIdentifiant = new JTextField();
		
		JLabel lPassword = new JLabel(Bundle.get("password"));
		JTextField fPassword = new JTextField();
				
		JButton connection	= new JButton();
		connection.setText(Bundle.get("connect"));
		connection.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e){
            	  if(gestionConnexion.connexion(fIdentifiant.getText(), fPassword.getText())) {
            		  gestionFrame.globalView();
            	  }
              }
          });
		
		JButton inscription	= new JButton();
		inscription.setText(Bundle.get("subscribe"));
		inscription.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e){
            	gestionFrame.inscription();
              }
          });
		add(lIdentifiant,new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL , new Insets(0,0,0,0),0 , 0));
		add(fIdentifiant,new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL , new Insets(0,0,0,0),0 , 0));
		add(lPassword,new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL , new Insets(0,0,0,0),0 , 0));
		add(fPassword,new GridBagConstraints(1, 1, 1, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL , new Insets(0,0,0,0),0 , 0));
		add(connection,new GridBagConstraints(0, 2, 2, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL , new Insets(0,0,0,0),0 , 0));
		add(inscription,new GridBagConstraints(0, 3, 2, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL , new Insets(0,0,0,0),0 , 0));
	}
}

package com.iup.tp.twitup.ihm;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iup.tp.twitup.action.IGestionConnexion;
import com.iup.tp.twitup.action.IGestionFrame;
import com.iup.tp.twitup.core.Bundle;

public class TwitupInscriptionView extends JPanel {

	IGestionFrame gestionFrame;
	IGestionConnexion gestionConnexion;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TwitupInscriptionView(IGestionFrame gestionFrame,IGestionConnexion gestionConnexion) {
		this.gestionFrame = gestionFrame;
		this.gestionConnexion	= gestionConnexion;
		initGui();
	}
	
	private void initGui() {

		initChamp();
	    //pack();
	}
	private void initChamp() {
		GridLayout layoutGlobal = new GridLayout(2,1);
		GridLayout layoutSaisie = new GridLayout(3,2);
		JPanel panelGlobal = new JPanel();
		JPanel panelSaisie = new JPanel();
		panelGlobal.setLayout(layoutGlobal);
		panelSaisie.setLayout(layoutSaisie);
		JLabel lIdentifiant = new JLabel(Bundle.get("login"));
		JTextField fIdentifiant = new JTextField();
		panelSaisie.add(lIdentifiant);
		panelSaisie.add(fIdentifiant);
		JLabel lPassword = new JLabel(Bundle.get("password"));
		JTextField fPassword = new JTextField();
		panelSaisie.add(lPassword);
		panelSaisie.add(fPassword);
		JLabel lPasswordConfirm = new JLabel(Bundle.get("password"));
		JTextField fPasswordConfirm = new JTextField();
		panelSaisie.add(lPasswordConfirm);
		panelSaisie.add(fPasswordConfirm);
		
		JButton button	= new JButton();
		button.setText(Bundle.get("subscribe"));
		button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
          	  gestionConnexion.inscription(fIdentifiant.getText(), fPassword.getText());
          	  gestionConnexion.connexion(fIdentifiant.getText(), fPassword.getText());
          	  gestionFrame.globalView();
            }
        });
		panelGlobal.add(panelSaisie);
		panelGlobal.add(button);
		add(panelGlobal);
	}
}

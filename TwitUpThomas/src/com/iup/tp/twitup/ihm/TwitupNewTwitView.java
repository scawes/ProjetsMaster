package com.iup.tp.twitup.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iup.tp.twitup.action.IGestionFrame;
import com.iup.tp.twitup.action.IGestionTwit;
import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.core.Bundle;

public class TwitupNewTwitView extends JPanel {

	IGestionFrame gestionFrame;
	IGestionTwit gestionTwit;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TwitupNewTwitView(IGestionFrame gestionFrame,IGestionTwit gestionTwit) {
		this.gestionFrame = gestionFrame;
		this.gestionTwit	= gestionTwit;
		initGui();
	}
	
	private void initGui() {
		GridLayout layoutGlobal = new GridLayout(1,1);
		setLayout(layoutGlobal);
		initChamp();
	    //pack();
	}
	private void initChamp() {
		
		//JPanel panelSaisie = new JPanel();
		
		setLayout(new GridBagLayout());
		JLabel lContenu = new JLabel(Bundle.get(Constants.TXT_CONTENT));
		JTextField jContenu = new JTextField();
		
		JButton button	= new JButton();
		button.setText(Bundle.get(Constants.TXT_SEND));
		button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
          	  //gestionConnexion.inscription(fIdentifiant.getText(), fPassword.getText());
            	gestionTwit.newTwit(jContenu.getText());
            }
        });
		add(lContenu,new GridBagConstraints(0, 0, 1, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH , new Insets(0,0,0,15),0 , 0));
		add(jContenu,new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH , new Insets(0,0,0,0),0 , 0));
		add(button,new GridBagConstraints(2, 0, 1, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH , new Insets(0,0,0,0),0 , 0));
		
	}
}

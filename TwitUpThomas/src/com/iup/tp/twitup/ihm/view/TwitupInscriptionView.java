package com.iup.tp.twitup.ihm.view;

import java.awt.Color;
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
import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.core.Bundle;

public class TwitupInscriptionView extends JPanel {

	IGestionFrame gestionFrame;
	IGestionConnexion gestionConnexion;
	JLabel lError;
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
		setLayout(new GridBagLayout());
		initChamp();
	    //pack();
	}
	private void initChamp() {

		JLabel lTag = new JLabel(Bundle.get(Constants.TXT_TAG));
		JTextField fTag = new JTextField();
		
		JLabel lIdentifiant = new JLabel(Bundle.get(Constants.TXT_LOGIN));
		JTextField fIdentifiant = new JTextField();
		
		JLabel lPassword = new JLabel(Bundle.get(Constants.TXT_PASSWORD));
		JTextField fPassword = new JTextField();
		
		JLabel lPasswordConfirm = new JLabel(Bundle.get(Constants.TXT_PASSWORD));
		JTextField fPasswordConfirm = new JTextField();
		
		
		JButton button	= new JButton();
		button.setText(Bundle.get(Constants.TXT_SUBSCRIBE));
		button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	inscription(fTag.getText(),fIdentifiant.getText(), fPassword.getText(),fPasswordConfirm.getText());
            }
        });
		JButton btnRetour	= new JButton();
		btnRetour.setText(Bundle.get(Constants.TXT_BACK));
		btnRetour.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	gestionFrame.globalView();
            }
        });
		
		lError = new JLabel();
		lError.setForeground(Color.RED);
		
		add(lTag,new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		add(fTag,new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		add(lIdentifiant,new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		add(fIdentifiant,new GridBagConstraints(1, 1, 1, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		add(lPassword,new GridBagConstraints(0, 2, 1, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		add(fPassword,new GridBagConstraints(1, 2, 1, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		add(lPasswordConfirm,new GridBagConstraints(0, 3, 2, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		add(fPasswordConfirm,new GridBagConstraints(1, 3, 2, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		add(button,new GridBagConstraints(0, 4, 2, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		add(btnRetour,new GridBagConstraints(0, 5, 2, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		add(lError,new GridBagConstraints(0, 6, 2, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

	}
	
	public void inscription(String tag,String user,String password,String passwordConfirm) {
		if(tag.isEmpty()) {
			lError.setText(Bundle.get(Constants.TXT_INVALIDE_TAG));
			repaint();
			return;
		}
		if(user.isEmpty()) {
			lError.setText(Bundle.get(Constants.TXT_INVALIDE_USER));
			repaint();
			return;
		}
		if(!passwordConfirm.equals(password)) {
			lError.setText(Bundle.get(Constants.TXT_INVALIDE_PASSWORD));
			repaint();
			return;
		}
		if(gestionConnexion.inscription(tag,user, password)) {
      		gestionConnexion.connexion(tag, password);
        	gestionFrame.globalView();
      	 } else {
      		lError.setText(Bundle.get(Constants.TXT_INVALIDE_TAG_USE));
      		repaint();
			return;
      	 }
		
	}
}

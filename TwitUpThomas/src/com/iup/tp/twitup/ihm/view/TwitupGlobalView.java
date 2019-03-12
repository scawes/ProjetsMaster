package com.iup.tp.twitup.ihm.view;

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
import com.iup.tp.twitup.action.IGestionTwit;
import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.core.Bundle;
import com.iup.tp.twitup.core.ITwitChange;
import com.iup.tp.twitup.ihm.components.TwitupListTwitView;
import com.iup.tp.twitup.ihm.components.TwitupNewTwitView;

public class TwitupGlobalView extends JPanel {

	IGestionFrame gestionFrame;
	IGestionConnexion gestionConnexion;
	IGestionTwit gestionTwit;
	ITwitChange twitChange;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TwitupGlobalView(IGestionFrame gestionFrame,IGestionConnexion gestionConnexion,IGestionTwit gestionTwit,ITwitChange twitChange) {
		this.gestionFrame = gestionFrame;
		this.gestionConnexion	= gestionConnexion;
		this.gestionTwit = gestionTwit;
		this.twitChange = twitChange;
		initGui();
	}
	
	private void initGui() {
		setLayout(new GridBagLayout());
		add(initChampEntete(),new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL , new Insets(0,0,0,0),0 , 0));
		add(initChampTwits(),new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH , new Insets(0,0,0,0),0 , 0));
		if(gestionConnexion.isConnected()) {
			add(initChampSaisie(),new GridBagConstraints(0, 2, 1, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL , new Insets(0,0,0,0),0 , 0));
		}
	}
	
	private JPanel initChampEntete() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		//recherche
		JLabel lRecherche = new JLabel(Bundle.get(Constants.TXT_SEARCH));
		
		JTextField fRecherche = new JTextField();
		
		JButton bRecherche	= new JButton();
		bRecherche.setText("->");
		bRecherche.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
          	  gestionTwit.search(fRecherche.getText());
            }
        });
		
		JButton bConnexion	= new JButton();
		bConnexion.setText(Bundle.get(Constants.TXT_CONNECT));
		bConnexion.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	gestionFrame.connexion();
            }
        });
		
		
		
		JButton bInscription	= new JButton();
		bInscription.setText(Bundle.get(Constants.TXT_SUBSCRIBE));
		bInscription.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
          	  gestionFrame.inscription();
            }
        });
		
		JButton bCompte	= new JButton();
		bCompte.setText(Bundle.get(Constants.TXT_ACCOUNT));
		bCompte.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
          	  //gestionFrame.inscription();
            	 gestionFrame.userView();
            }
        });
		
		JButton bDeconnexion	= new JButton();
		bDeconnexion.setText(Bundle.get(Constants.TXT_LOGOUT));
		bDeconnexion.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	gestionConnexion.deconnexion();
            	gestionFrame.globalView();
            }
        });
		
		//bConnexion.setBackground(Color.GREEN.darker());
		//bConnexion.setForeground(Color.LIGHT_GRAY.brighter());
		//bConnexion.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createBevelBorder( BevelBorder.RAISED, Color.BLUE.darker(), Color.BLACK), BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)));
		//bConnexion.setFont(new Font("Arial", Font.BOLD, 14));
		//bConnexion.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createBevelBorder( BevelBorder.RAISED, Color.GREEN, Color.BLACK), BorderFactory.createEtchedBorder(EtchedBorder.RAISED)));

		//bConnexion.setBorder(new Border);
		panel.add(lRecherche,new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL , new Insets(0,0,0,20),0 , 0));
		panel.add(fRecherche,new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH , new Insets(0,0,0,0),0 , 0));
		panel.add(bRecherche,new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL , new Insets(0,0,0,0),0 , 0));
		if(!gestionConnexion.isConnected()) {
			panel.add(bConnexion,new GridBagConstraints(3, 0, 1, 1, 0, 0, GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL , new Insets(0,0,0,0),0 , 0));
			panel.add(bInscription,new GridBagConstraints(4, 0, 1, 1, 0, 0, GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL , new Insets(0,0,0,0),0 , 0));
		} else {
			panel.add(bCompte,new GridBagConstraints(3, 0, 1, 1, 0, 0, GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL , new Insets(0,0,0,0),0 , 0));
			panel.add(bDeconnexion,new GridBagConstraints(4, 0, 1, 1, 0, 0, GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL , new Insets(0,0,0,0),0 , 0));
		}
		
		return panel;
	}
	
	private JPanel initChampTwits() {
		return new TwitupListTwitView(gestionTwit,twitChange,gestionConnexion);
	}
	
	private JPanel initChampSaisie() {
		return new TwitupNewTwitView(gestionFrame,gestionTwit);
	}
}

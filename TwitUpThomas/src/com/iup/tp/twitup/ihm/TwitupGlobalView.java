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
import com.iup.tp.twitup.action.IGestionTwit;
import com.iup.tp.twitup.core.ITwitChange;

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
		//setTitle("TwitterLike Inscription");
		//setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/logoIUP_20.jpg")));
		//new GridBagConstraints(gridx, gridy, gridwidth, gridheight, weightx, weighty, anchor, fill, insets, ipadx, ipady)
		setLayout(new GridBagLayout());
		add(initChampEntete(),new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL , new Insets(0,0,0,0),0 , 0));
		add(initChampTwits(),new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH , new Insets(0,0,0,0),0 , 0));
		if(gestionConnexion.isConnected()) {
			add(initChampSaisie(),new GridBagConstraints(0, 2, 1, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL , new Insets(0,0,0,0),0 , 0));
		}
		
	    //pack();
	}
	
	private JPanel initChampEntete() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		//recherche
		JLabel lRecherche = new JLabel("Recherche");
		
		JTextField fRecherche = new JTextField();
		
		JButton bRecherche	= new JButton();
		bRecherche.setText("->");
		bRecherche.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
          	  gestionTwit.search(fRecherche.getText());
            }
        });
		
		JButton bConnexion	= new JButton();
		bConnexion.setText("Connexion");
		bConnexion.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	gestionFrame.connexion();
            }
        });
		
		
		
		JButton bInscription	= new JButton();
		bInscription.setText("Inscription");
		bInscription.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
          	  gestionFrame.inscription();
            }
        });

		panel.add(lRecherche,new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL , new Insets(0,0,0,20),0 , 0));
		panel.add(fRecherche,new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH , new Insets(0,0,0,0),0 , 0));
		panel.add(bRecherche,new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL , new Insets(0,0,0,0),0 , 0));
		if(!gestionConnexion.isConnected()) {
			panel.add(bConnexion,new GridBagConstraints(3, 0, 1, 1, 0, 0, GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL , new Insets(0,0,0,0),0 , 0));
			panel.add(bInscription,new GridBagConstraints(4, 0, 1, 1, 0, 0, GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL , new Insets(0,0,0,0),0 , 0));
		}
		
		return panel;
	}
	
	private JPanel initChampTwits() {
		return new TwitupListTwitView(gestionTwit,twitChange);
	}
	
	private JPanel initChampSaisie() {
		return new TwitupNewTwitView(gestionFrame,gestionTwit);
	}
}

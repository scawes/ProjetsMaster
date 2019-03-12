package com.iup.tp.twitup.ihm.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.iup.tp.twitup.action.IGestionFrame;
import com.iup.tp.twitup.action.IGestionTwit;
import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.core.Bundle;

public class TwitupNewTwitView extends JPanel {

	IGestionFrame gestionFrame;
	IGestionTwit gestionTwit;
	JTextField jContenu;
	String twitContenu="";
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
	}
	private void initChamp() {
		setLayout(new GridBagLayout());
		JLabel lContenu = new JLabel(Bundle.get(Constants.TXT_CONTENT));
		jContenu = new JTextField();
		jContenu.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						restrictTwit();
					}
				});
				
			}
		});
		JButton button	= new JButton();
		button.setText(Bundle.get(Constants.TXT_SEND));
		button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	gestionTwit.newTwit(jContenu.getText());
            	jContenu.setText("");
            }
        });
		add(lContenu,new GridBagConstraints(0, 0, 1, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH , new Insets(0,0,0,15),0 , 0));
		add(jContenu,new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH , new Insets(0,0,0,0),0 , 0));
		add(button,new GridBagConstraints(2, 0, 1, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH , new Insets(0,0,0,0),0 , 0));
	}
	
	public void restrictTwit() {
		if(jContenu.getText().length()>Constants.CONFIGURATION_MAX_SIZE_TWIT) {
			jContenu.setText(twitContenu);
		}else {
			twitContenu = jContenu.getText();
		}
	}
}

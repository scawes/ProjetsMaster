package com.iup.tp.twitup.ihm;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.iup.tp.twitup.datamodel.Twit;

public class TwitupTwitComponente extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public TwitupTwitComponente(Twit twit) {
		initGui(twit);
	}
	
	private void initGui(Twit twit) {
		
		setLayout(new GridBagLayout());
		setBorder(new LineBorder(new Color(0,102,102), 2));
		setBackground(new Color(0,179,179));
		initChamp(twit);
	}
	
private void initChamp(Twit twit) {
		JLabel lUser = new JLabel(twit.getTwiter().getName());
		lUser.setForeground(new Color(255,255,255));
		JLabel lContent = new JLabel(twit.getText());
		add(lUser,new GridBagConstraints(0, 1, 1, 1, 1,0 , GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL , new Insets(0,0,0,0),0 , 0));
		add(lContent,new GridBagConstraints(0, 2, 1, 1, 1,1 , GridBagConstraints.PAGE_END, GridBagConstraints.BOTH , new Insets(0,0,0,0),0 , 0));
	}

}

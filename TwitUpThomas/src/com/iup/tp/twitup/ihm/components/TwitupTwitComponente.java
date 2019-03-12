package com.iup.tp.twitup.ihm.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import com.iup.tp.twitup.action.IGestionConnexion;
import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.core.Bundle;
import com.iup.tp.twitup.datamodel.Twit;

public class TwitupTwitComponente extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Twit twit;
	private IGestionConnexion user;
	
	public TwitupTwitComponente(Twit twit,IGestionConnexion user) {
		this.twit = twit;
		this.user=user;
		initGui();
	}
	
	private void initGui() {
		
		setLayout(new GridBagLayout());
		setBorder(new LineBorder(new Color(0,102,102), 2));
		setBackground(new Color(0,179,179));
		initChamp();
	}
	
	private void initChamp() {
		ImagePanel imageUser = initImage();
		JTextArea ltwitLong = new JTextArea(twit.getText());
		ltwitLong.setEditable(false);
		ltwitLong.setBackground(new Color(225,225,225));
		ltwitLong.setLineWrap(true); 
		JLabel lUser = new JLabel(twit.getTwiter().getName());
		lUser.setForeground(new Color(255,255,255));
		//JLabel lContent = new JLabel(twit.getText());
		JButton btnFollow = new JButton(Bundle.get(Constants.TXT_FOLLOW));
		btnFollow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	user.follow(twit.getTwiter());
            }
        });
		JButton btnUnfollow = new JButton(Bundle.get(Constants.TXT_UNFOLLOW));
		btnUnfollow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	user.unfollow(twit.getTwiter());
            }
        });
		if(imageUser!=null) {
			add(imageUser,new GridBagConstraints(0, 0, 1, 1, 0,0 , GridBagConstraints.LINE_START, GridBagConstraints.NONE , new Insets(0,0,0,0),0 , 0));

		}
		if(user.isConnected()) {
			if(!user.isFollowing(twit.getTwiter())) {
				add(btnFollow,new GridBagConstraints(2, 0, 1, 1, 0,0 , GridBagConstraints.LINE_END, GridBagConstraints.NONE , new Insets(0,0,0,0),0 , 0));
			}else {
				add(btnUnfollow,new GridBagConstraints(2, 0, 1, 1, 0,0 , GridBagConstraints.LINE_END, GridBagConstraints.NONE , new Insets(0,0,0,0),0 , 0));
			}
		}
		
		add(lUser,new GridBagConstraints(1, 0, 1, 1, 1,0 , GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL , new Insets(0,0,0,0),0 , 0));
		add(ltwitLong,new GridBagConstraints(0, 1, 3, 1, 1,1 , GridBagConstraints.PAGE_END, GridBagConstraints.BOTH , new Insets(0,0,0,0),0 , 0));
	}

	private ImagePanel initImage() {
		File fileImg = new File(twit.getTwiter().getAvatarPath());
		if(fileImg.exists()) {
			return new ImagePanel(fileImg, new Dimension(22, 22));
		}
		return null;
	}

}

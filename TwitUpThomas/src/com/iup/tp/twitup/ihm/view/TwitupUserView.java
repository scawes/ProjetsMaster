package com.iup.tp.twitup.ihm.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageFilter;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.iup.tp.twitup.action.IGestionConnexion;
import com.iup.tp.twitup.action.IGestionFrame;
import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.core.Bundle;
import com.iup.tp.twitup.ihm.components.ImagePanel;

public class TwitupUserView extends JPanel {

	IGestionFrame gestionFrame;
	IGestionConnexion gestionConnexion;
	JLabel lError;
	String imagePath;
	ImagePanel image;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TwitupUserView(IGestionFrame gestionFrame,IGestionConnexion gestionConnexion) {
		this.gestionFrame = gestionFrame;
		this.gestionConnexion	= gestionConnexion;
		initGui();
	}

	private void initGui() {
		setLayout(new GridBagLayout());
		initChamp();
	}
	
	private void initChamp() {
		int positionLigne;
		imagePath = gestionConnexion.getUser().getAvatarPath();
		image = initImage();
		
		
		JLabel lAvatar = new JLabel(Bundle.get(Constants.TXT_AVATAR));
		JButton btnAvatar = new JButton();
		btnAvatar.setText(Bundle.get(Constants.TXT_CHOOSE_FILE));
		btnAvatar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	//update(fIdentifiant.getText(), fPassword.getText(),fAvatar.getText());
            	String resultChooser = getPath();
            	if(!resultChooser.isEmpty()) {
            		imagePath = resultChooser;
            		//image = initImage();
            		//repaint();
            	}
            }
        });
		//JTextField fAvatar = new JTextField(gestionConnexion.getUser().getAvatarPath());
		
		JLabel lTag = new JLabel(Bundle.get(Constants.TXT_TAG));
		JTextField fTag = new JTextField(gestionConnexion.getUser().getUserTag());
		fTag.setEditable(false);
		
		JLabel lIdentifiant = new JLabel(Bundle.get(Constants.TXT_LOGIN));
		JTextField fIdentifiant = new JTextField(gestionConnexion.getUser().getName());
		
		JLabel lPassword = new JLabel(Bundle.get(Constants.TXT_PASSWORD));
		JTextField fPassword = new JTextField(gestionConnexion.getUser().getUserPassword());
		
		JLabel lPasswordConfirm = new JLabel(Bundle.get(Constants.TXT_PASSWORD));
		JTextField fPasswordConfirm = new JTextField(gestionConnexion.getUser().getUserPassword());
		
		
		JButton button	= new JButton();
		button.setText(Bundle.get(Constants.TXT_UPDATE_USER));
		button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	update(fIdentifiant.getText(), fPassword.getText(),imagePath);
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
		positionLigne=0;
		if(image!=null) {
			add(image,new GridBagConstraints(0, positionLigne, 2, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			positionLigne++;
		}
		
		add(lAvatar,new GridBagConstraints(0, positionLigne, 1, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		add(btnAvatar,new GridBagConstraints(1, positionLigne, 1, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		positionLigne++;
		add(lTag,new GridBagConstraints(0, positionLigne, 1, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		add(fTag,new GridBagConstraints(1, positionLigne, 1, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		positionLigne++;
		add(lIdentifiant,new GridBagConstraints(0, positionLigne, 1, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		add(fIdentifiant,new GridBagConstraints(1, positionLigne, 1, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		positionLigne++;
		add(lPassword,new GridBagConstraints(0, positionLigne, 1, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		add(fPassword,new GridBagConstraints(1, positionLigne, 1, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		positionLigne++;
		add(lPasswordConfirm,new GridBagConstraints(0, positionLigne, 2, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		add(fPasswordConfirm,new GridBagConstraints(1, positionLigne, 2, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		positionLigne++;
		add(button,new GridBagConstraints(0, positionLigne, 2, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		positionLigne++;
		add(btnRetour,new GridBagConstraints(0, positionLigne, 2, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		positionLigne++;
		add(lError,new GridBagConstraints(0, positionLigne, 2, 1, 1, 0, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

	}
	
	private void update(String user,String password,String avatar) {
		gestionConnexion.updateUser(user, password,avatar);
		gestionFrame.globalView();
	}
	
	private ImagePanel initImage() {
		File fileImg = new File(imagePath);
		if(fileImg.exists()) {
			return new ImagePanel(fileImg, new Dimension(100, 100));
		}
		return null;
	}
	
	public String getPath() {
		JFileChooser chooser = new JFileChooser();
		FileFilter imageFilter = new FileNameExtensionFilter(
			    "Image files", ImageIO.getReaderFileSuffixes());
		chooser.setFileFilter(imageFilter);
		chooser.updateUI();
	    int returnVal = chooser.showOpenDialog(this);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       	return chooser.getSelectedFile().getPath();
	    }else {
	    	return "";
	    }		
	}
}

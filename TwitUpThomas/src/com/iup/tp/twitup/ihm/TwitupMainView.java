package com.iup.tp.twitup.ihm;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import com.iup.tp.twitup.TwitupLauncher;
import com.iup.tp.twitup.action.IChooseDirectory;
import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.core.Bundle;
import com.iup.tp.twitup.ihm.components.TwitupMenuBar;

/**
 * Classe de la vue principale de l'application.
 */
public class TwitupMainView extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IChooseDirectory chooseDirectory;
	
	public TwitupMainView(IChooseDirectory chooseDirectory) {
		this.chooseDirectory=chooseDirectory;
		initGui();
	}
	
	private void initGui() {
		setTitle(Bundle.get(Constants.TXT_APPLICATION_NAME));
		setIconImage(Toolkit.getDefaultToolkit().getImage(TwitupLauncher.class.getResource(Constants.IMG_ICON_LOGO)));
		setLayout(new GridBagLayout());
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setPreferredSize(new Dimension((screenSize.width - getWidth()) / 6,
				(screenSize.height - getHeight()) / 4));
		setLocation((screenSize.width - getWidth()) / 6,
				(screenSize.height - getHeight()) / 4);
	    JMenuBar jmb = new TwitupMenuBar(this);//getJMenu();
	    setJMenuBar(jmb);
	    pack();
	}
	
	
	public Component getFrame() {
		return this;
	}
	
	public boolean close() {
		setVisible(false);
		dispose(); 
		return true;
	}
	
	public void getPath() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.updateUI();
	    int returnVal = chooser.showOpenDialog(this);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       //System.out.println("You chose to open this file: " +
	           // chooser.getSelectedFile().getPath());
	       	chooseDirectory.chooseDirectory(chooser.getSelectedFile().getPath());
	    }else {
	    	chooseDirectory.chooseDirectory("");
	    }		
	}	
	public void setFrame(Component component) {
		getContentPane().removeAll();
		getContentPane().add(component,new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		revalidate();
		repaint();
	}
	
}

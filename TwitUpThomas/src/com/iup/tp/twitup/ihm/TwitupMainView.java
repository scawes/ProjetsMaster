package com.iup.tp.twitup.ihm;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import com.iup.tp.twitup.TwitupLauncher;
import com.iup.tp.twitup.action.IChooseDirectory;
import com.iup.tp.twitup.core.Bundle;

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
		//TwitupInscriptionView connexion = new TwitupInscriptionView();
		//connexion.setVisible(true);
	}
	
	private void initGui() {
		setTitle("TwitterLike");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TwitupLauncher.class.getResource("/images/logoIUP_20.jpg")));
		
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setPreferredSize(new Dimension((screenSize.width - getWidth()) / 6,
				(screenSize.height - getHeight()) / 4));
		setLocation((screenSize.width - getWidth()) / 6,
				(screenSize.height - getHeight()) / 4);
	    JMenuBar jmb = getJMenu();
	    setJMenuBar(jmb);
	    pack();
	}
	
	public  JMenuBar getJMenu(){
		 JMenuBar jmb = new JMenuBar();
		 
	      JMenu jm1 = new JMenu();
	      jm1.setText(Bundle.get("file"));
	      JMenuItem quitter = new JMenuItem();
	      //quit
	      quitter.setText("Quitter");
	      quitter.setIcon(new ImageIcon(TwitupLauncher.class.getResource("/images/exitIcon_20.png")));
	      quitter.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e){
            	close();
              }
          });
	      jm1.add(quitter);
	      //file chooser
	      JMenuItem repChooser = new JMenuItem();
	      repChooser.setText("Set directory");
	      //repChooser.setIcon(new ImageIcon(TwitupLauncher.class.getResource("/images/exitIcon_20.png")));
	      repChooser.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e){
            	  getPath();
              }
          });
	      jm1.add(repChooser);
	      
	      jmb.add(jm1);
	      JMenu jm2 = new JMenu();
	      jm2.setText("?");
	      JMenuItem propos = new JMenuItem();
	      //bg1.add(quitter);
	      propos.setText("A propos");
	      propos.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e){
            	  JOptionPane.showMessageDialog(getFrame(),
      	    		    "UBO M2-TIIL\nDépartement informatique",
      	    		    "A propos",
      	    		    JOptionPane.INFORMATION_MESSAGE,
      	    		    new ImageIcon(TwitupLauncher.class.getResource("/images/logoIUP_50.jpg")));
              }
          });
	      jm2.add(propos);
	      jmb.add(jm2);
	      return jmb;
	   }
	private Component getFrame() {
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
	       System.out.println("You chose to open this file: " +
	            chooser.getSelectedFile().getPath());
	       	chooseDirectory.chooseDirectory(chooser.getSelectedFile().getPath());
	    }else {
	    	chooseDirectory.chooseDirectory("");
	    }		
	}	
	public void setFrame(Component component) {
		getContentPane().removeAll();
		getContentPane().add(component);
		revalidate();
		repaint();
	}
	
}

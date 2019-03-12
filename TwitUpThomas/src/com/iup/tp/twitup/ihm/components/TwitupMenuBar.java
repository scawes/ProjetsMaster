package com.iup.tp.twitup.ihm.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.iup.tp.twitup.TwitupLauncher;
import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.core.Bundle;
import com.iup.tp.twitup.ihm.TwitupMainView;

public class TwitupMenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TwitupMainView mainVue;
	
	public TwitupMenuBar(TwitupMainView mainVue) {
		this.mainVue=mainVue;
		constructMenu();
	}
	
	private  void constructMenu(){		 
	      
	      
	      this.add(constructFile());
	      
	      this.add(constructAbout());
	   }
	
	private JMenu constructFile() {
		JMenu jm = new JMenu();
	      jm.setText(Bundle.get(Constants.TXT_FILE));
	      JMenuItem quitter = new JMenuItem();
	      //quit
	      quitter.setText(Bundle.get(Constants.TXT_QUIT));
	      quitter.setIcon(new ImageIcon(TwitupLauncher.class.getResource(Constants.IMG_ICON_EXIT)));
	      quitter.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
         	mainVue.close();
           }
       });
	      
	      //file chooser
	      JMenuItem repChooser = new JMenuItem();
	      repChooser.setText(Bundle.get(Constants.TXT_SET_DIRECTORY));
	      repChooser.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
          	 mainVue.getPath();
           }
       });
	      jm.add(repChooser);
	      jm.add(quitter);
	      return jm;
	}
	
	private JMenu constructAbout() {
		JMenu jm = new JMenu();
	      jm.setText("?");
	      JMenuItem propos = new JMenuItem();
	      //bg1.add(quitter);
	      propos.setText(Bundle.get(Constants.TXT_ABOUT));
	      propos.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
         	  JOptionPane.showMessageDialog(mainVue.getFrame(),
   	    		    "UBO M2-TIIL\nDépartement informatique",
   	    		    Bundle.get(Constants.TXT_ABOUT),
   	    		    JOptionPane.INFORMATION_MESSAGE,
   	    		    new ImageIcon(TwitupLauncher.class.getResource(Constants.IMG_LARGE_LOGO)));
           }
       });
	      jm.add(propos);
	      return jm;
	}

}

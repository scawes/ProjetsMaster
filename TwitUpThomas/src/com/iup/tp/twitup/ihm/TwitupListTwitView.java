package com.iup.tp.twitup.ihm;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.iup.tp.twitup.action.IGestionTwit;
import com.iup.tp.twitup.core.ITwitChange;
import com.iup.tp.twitup.core.ITwitChangeObserver;
import com.iup.tp.twitup.datamodel.Twit;

public class TwitupListTwitView extends JPanel implements ITwitChangeObserver {

	JPanel contener;
	IGestionTwit gestionTwit;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TwitupListTwitView(IGestionTwit gestionTwit,ITwitChange twitchange) {
		this.gestionTwit = gestionTwit;
		twitchange.addObserver(this);
		contener = new JPanel(new GridBagLayout());
		initGui();
	}
	
	private void initGui() {
		setLayout(new GridBagLayout());
		JScrollPane pane = new JScrollPane();
		contener.setBackground(Color.WHITE);
		pane.getViewport().add(contener,GridBagConstraints.PAGE_START);
		add(pane,new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH , new Insets(0,0,0,0),0 , 0));
		refresh();
	}
	
	public void refresh() {
		Set<Twit> twits = gestionTwit.refresh();
		contener.removeAll();
		int indice = 0;
		for(Twit twit:twits) {
			contener.add(new TwitupTwitComponente(twit),
					new GridBagConstraints(0, indice, 1, 1, 1,0 , GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL , new Insets(5,0,0,0),0 , 0));
			indice++;
		}
		contener.revalidate();
		contener.repaint();
	}

	@Override
	public void notifyTwitChange() {
		refresh();
	}

}

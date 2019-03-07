package com.iup.tp.twitup.core;

import com.iup.tp.twitup.action.IGestionFrame;
import com.iup.tp.twitup.ihm.TwitupConnexionView;
import com.iup.tp.twitup.ihm.TwitupGlobalView;
import com.iup.tp.twitup.ihm.TwitupInscriptionView;
import com.iup.tp.twitup.ihm.TwitupListTwitView;
import com.iup.tp.twitup.ihm.TwitupNewTwitView;

public class TwitupFrame implements IGestionFrame{
	protected Twitup twitup;

	public TwitupFrame(Twitup twitup) {
		this.twitup = twitup;
	}

	@Override
	public void connexion() {
		twitup.getmMainView().setFrame(new TwitupConnexionView(this,twitup.getUser()));
	}

	@Override
	public void inscription() {
		twitup.getmMainView().setFrame(new TwitupInscriptionView(this,twitup.getUser()));
	}

	@Override
	public void viewTwit() {
		TwitupListTwitView vue = new TwitupListTwitView(twitup.getTwit(),twitup.getTwit());
		twitup.getmMainView().setFrame(vue);
		twitup.getTwit().setVue(vue);
	}

	@Override
	public void newTwit() {
		twitup.getmMainView().setFrame(new TwitupNewTwitView(this,twitup.getTwit()));
	}

	@Override
	public void globalView() {
		twitup.getmMainView().setFrame(new TwitupGlobalView(this, twitup.getUser(), twitup.getTwit(),twitup.getTwit()));
	}

}

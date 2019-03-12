package com.iup.tp.twitup.core;

import java.util.HashSet;
import java.util.Set;

import com.iup.tp.twitup.action.IGestionTwit;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.components.TwitupListTwitView;

public class TwitupTwit implements IGestionTwit,IDatabaseObserver,ITwitChange{

	/**
	 * Liste des observateurs de modifications de la base.
	 */
	protected final Set<ITwitChangeObserver> mObservers;
	protected Twitup twitup;
	protected TwitupListTwitView vue;
	protected String search = "";
	
	public TwitupTwit(Twitup twitup) {
		mObservers = new HashSet<ITwitChangeObserver>();
		this.twitup = twitup;
	}
	
	public void setVue(TwitupListTwitView vue) {
		this.vue=vue;
	}
	
	@Override
	public void newTwit(String content) {
		generateTwit(twitup.getUser().getUser(),content);
	}
	
	/**
	 * Génération d'un twit fictif.
	 */
	protected Twit generateTwit(User user,String content) {
		Twit newTwit = new Twit(user, content);
		
		twitup.getmDatabase().addTwit(newTwit);
		twitup.getmEntityManager().sendTwit(newTwit);
		return newTwit;
	}

	@Override
	public Set<Twit> refresh() {
		if(search.isEmpty()) {
			return twitup.getmDatabase().getTwits();
		}else {
			return refresh(search);
		}
		
	}
	
	@Override
	public Set<Twit> refresh(String search) {
		Set<Twit> twitTag;
		if(search.contains("#")) {
			search=search.replace("#", "");
			twitTag= twitup.getmDatabase().getTwitsWithTag(search);
		} else if(search.contains("@")) {
			search=search.replace("@", "");
			twitTag= twitup.getmDatabase().getTwitsWithUserTag(search);
		} else {
			twitTag = twitup.getmDatabase().getTwitsWithTag(search);
			twitTag.addAll(twitup.getmDatabase().getTwitsWithUserTag(search));
		}
		return twitTag;
	}

	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		// TODO Auto-generated method stub
//		if(vue!=null) {
//			vue.refresh();
//		}
		// Notification des observateurs
		for (ITwitChangeObserver observer : mObservers) {
			observer.notifyTwitChange();
		}
	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {
		// TODO Auto-generated method stub
		for (ITwitChangeObserver observer : mObservers) {
			observer.notifyTwitChange();
		}
	}

	@Override
	public void notifyTwitModified(Twit modifiedTwit) {
		// TODO Auto-generated method stub
		for (ITwitChangeObserver observer : mObservers) {
			observer.notifyTwitChange();
		}
	}

	@Override
	public void notifyUserAdded(User addedUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @{inheritDoc
	 */
	@Override
	public void addObserver(ITwitChangeObserver observer) {
		this.mObservers.add(observer);

		// Notification pour le nouvel observateur
		/*for (Twit twit : this.getTwits()) {
			observer.notifyTwitAdded(twit);
		}*/

		// Notification pour le nouvel observateur
		/*for (User user : this.getUsers()) {
			// Pas de notification pour l'utilisateur inconnu
			if (user.getUuid().equals(Constants.UNKNONWN_USER_UUID) == false) {
				observer.notifyUserAdded(user);
			}
		}*/
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	public void deleteObserver(ITwitChangeObserver observer) {
		this.mObservers.remove(observer);
	}

	/**
	 * Retourne une liste clonées des observateurs de modifications.
	 */
	protected Set<ITwitChangeObserver> getObservers() {
		return new HashSet<ITwitChangeObserver>(this.mObservers);
	}

	@Override
	public void search(String content) {
		// TODO Auto-generated method stub
		this.search =  content;
		for (ITwitChangeObserver observer : mObservers) {
			observer.notifyTwitChange();
		}
	}

}

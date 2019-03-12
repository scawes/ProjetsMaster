package com.iup.tp.twitup.core;

import java.util.HashSet;
import java.util.UUID;

import com.iup.tp.twitup.action.IGestionConnexion;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

public class TwitupUser implements IGestionConnexion, IDatabaseObserver,ITwitFollow{

	protected User userConnect;
	protected Twitup twitup;
	
	public TwitupUser(Twitup twitup) {
		this.twitup = twitup;
		this.twitup.getmDatabase().addObserver(this);
	}

	@Override
	public boolean connexion(String user, String password) {
		userConnect = twitup.getmDatabase().getUser(user, password);
		if(userConnect==null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean inscription(String tag,String name, String password) {
		if(userExist(tag)){
			return false;
		}else {
			// Création d'un utilisateur fictif
			User user = this.generateUser(tag,name,password);
			//addUserInDatabase(user);
			sendUser(user);
			return true;
		}
		
	}
	
	protected User generateUser(String tag,String user, String password) {
		User newUser = new User(UUID.randomUUID(), tag, password, user, new HashSet<String>(), "");

		return newUser;
	}
	
	protected User generateUser(String user, String password) {
		User newUser = new User(UUID.randomUUID(), user, password, user, new HashSet<String>(), "");

		return newUser;
	}
	
	/**
	 * Ajoute un utilisateur fictif à la base de donnée.
	 */
	protected void addUserInDatabase(User user) {
		
		// Ajout de l'utilisateur à la base
		twitup.getmDatabase().addUser(user);
	}
	
	protected boolean userExist(String tag) {
		for(User user:twitup.getmDatabase().getUsers()) {
			if(user.getUserTag().equals(tag)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Génération et envoi d'un fichier utilisateur
	 */
	protected void sendUser(User user) {
		// Génération du fichier utilisateur
		twitup.getmEntityManager().sendUser(user);
	}
	
	public User getUser() {
		return userConnect;
	}

	@Override
	public boolean isConnected() {
		
		return userConnect!=null;
	}

	@Override
	public void deconnexion() {
		userConnect=null;
	}

	@Override
	public void updateUser(String user, String password,String avatar) {
		userConnect.setName(user);
		userConnect.setUserPassword(password);
		userConnect.setAvatarPath(avatar);
		//twitup.getmDatabase().modifiyUser(userConnect);
		twitup.getmEntityManager().sendUser(userConnect);
	}

	@Override
	public void follow(User user) {
		this.userConnect.addFollowing(user.getUserTag());
		this.twitup.getmEntityManager().sendUser(userConnect);
	}

	@Override
	public void unfollow(User user) {
		this.userConnect.removeFollowing(user.getUserTag());
		this.twitup.getmEntityManager().sendUser(userConnect);
	}

	@Override
	public boolean isFollowing(User user) {
		return userConnect.isFollowing(user);
	}

	@Override
	public void notifyTwitAdded(Twit addedTwit) {
	if(userConnect==null) {
		return ;
	}
		boolean notifie = false;
		if(userConnect.isFollowing(addedTwit.getTwiter())){
			notifie=true;
		}
		if(addedTwit.getUserTags().contains(userConnect.getUserTag())) {
			notifie=true;
		}
		if(notifie) {
			System.out.println("twit follow");
			//TODO
		}
	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyTwitModified(Twit modifiedTwit) {
		// TODO Auto-generated method stub
		
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

	@Override
	public void addObserver(ITwitChangeObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteObserver(ITwitChangeObserver observer) {
		// TODO Auto-generated method stub
		
	}

}

package com.iup.tp.twitup.core;

import java.util.HashSet;
import java.util.UUID;

import com.iup.tp.twitup.action.IGestionConnexion;
import com.iup.tp.twitup.datamodel.User;

public class TwitupUser implements IGestionConnexion{

	protected User userConnect;
	protected Twitup twitup;
	
	public TwitupUser(Twitup twitup) {
		this.twitup = twitup;
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
	public void inscription(String user, String password) {
		addUserInDatabase(user,password);
		sendUser(user,password);
	}
	
	protected User generateUser(String user, String password) {
		User newUser = new User(UUID.randomUUID(), user, password, user, new HashSet<String>(), "");

		return newUser;
	}
	
	/**
	 * Ajoute un utilisateur fictif à la base de donnée.
	 */
	protected void addUserInDatabase(String user, String password) {
		// Création d'un utilisateur fictif
		User newUser = this.generateUser(user,password);
		// Ajout de l'utilisateur à la base
		twitup.getmDatabase().addUser(newUser);
	}

	/**
	 * Génération et envoi d'un fichier utilisateur
	 */
	protected void sendUser(String user, String password) {
		// Création d'un utilisateur fictif
		User newUser = this.generateUser(user,password);

		// Génération du fichier utilisateur
		twitup.getmEntityManager().sendUser(newUser);
	}
	
	public User getUser() {
		return userConnect;
	}

	@Override
	public boolean isConnected() {
		
		return userConnect!=null;
	}

}

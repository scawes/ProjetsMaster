package com.iup.tp.twitup.core;

public interface ITwitChange {
	/**
	 * Ajoute un observateur sur les modifications de la base de données.
	 * 
	 * @param observer
	 */
	void addObserver(ITwitChangeObserver observer);

	/**
	 * Supprime un observateur sur les modifications de la base de données.
	 * 
	 * @param observer
	 */
	void deleteObserver(ITwitChangeObserver observer);

}

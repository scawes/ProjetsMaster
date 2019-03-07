package com.iup.tp.twitup.core;

public interface ITwitChangeObserver {
	/**
	 * Notification lorsqu'un Twit est ajouté en base de données.
	 * 
	 * @param addedTwit
	 */
	void notifyTwitChange();
}

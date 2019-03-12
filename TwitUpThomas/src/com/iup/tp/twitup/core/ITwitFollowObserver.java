package com.iup.tp.twitup.core;

public interface ITwitFollowObserver {
	/**
	 * Notification lorsqu'un Twit est ajouté en base de données.
	 * 
	 * @param addedTwit
	 */
	void notifyTwitChange();
}

package com.iup.tp.twitup.action;

import com.iup.tp.twitup.datamodel.User;

public interface IGestionConnexion {
	public boolean connexion(String user,String password);
	
	public boolean inscription(String tag,String user,String password);
	
	public boolean isConnected();
	
	public void deconnexion();
	
	public void updateUser(String user,String password,String avatar);
	
	public User getUser();
	
	public void follow(User user);
	
	public void unfollow(User user);
	
	public boolean isFollowing(User user);
}

package com.iup.tp.twitup.action;

public interface IGestionConnexion {
	public boolean connexion(String user,String password);
	
	public void inscription(String user,String password);
	
	public boolean isConnected();
}

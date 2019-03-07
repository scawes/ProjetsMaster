package com.iup.tp.twitup.action;

import java.util.Set;

import com.iup.tp.twitup.datamodel.Twit;

public interface IGestionTwit {
	void newTwit(String content);
	
	void search(String content);
	
	Set<Twit> refresh();
	
	Set<Twit> refresh(String search);
}

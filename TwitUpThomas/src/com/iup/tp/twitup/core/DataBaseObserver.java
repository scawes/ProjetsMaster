package com.iup.tp.twitup.core;

import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

public class DataBaseObserver implements IDatabaseObserver {

	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		System.out.print("add twit :");
		System.out.println(addedTwit.getText());

	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {
		System.out.println("Delete twit");

	}

	@Override
	public void notifyTwitModified(Twit modifiedTwit) {
		System.out.println("update twit");

	}

	@Override
	public void notifyUserAdded(User addedUser) {
		System.out.print("add user :");
		System.out.println(addedUser.getName());

	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		System.out.println("Delete user");

	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		System.out.println("update user");

	}

}

package com.iup.tp.twitup.core;

import java.io.File;
import java.util.Properties;

import com.iup.tp.twitup.action.IChooseDirectory;
import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.common.PropertiesManager;

public class TwitupDirectory implements IChooseDirectory{
	
	Twitup twitup;
	
	public TwitupDirectory(Twitup twitup) {
		this.twitup = twitup;
	}

	@Override
	public void chooseDirectory(String path) {
		Properties props = PropertiesManager.loadProperties(Constants.CONFIGURATION_FILE);
		File destination = new File(path);
		if(isValideExchangeDirectory(destination)) {
			//System.out.println(Constants.CONFIGURATION_FILE);
		    props.setProperty(Constants.CONFIGURATION_KEY_EXCHANGE_DIRECTORY,  path);
		    PropertiesManager.writeProperties(props, Constants.CONFIGURATION_FILE);
		    twitup.initDirectory(path);
		}else {
			if(props.getProperty(Constants.CONFIGURATION_KEY_EXCHANGE_DIRECTORY).isEmpty()) {
				twitup.close();
			}
		}
	}
	
	protected boolean isValideExchangeDirectory(File directory) {
		// Valide si répertoire disponible en lecture et écriture
		return directory != null && directory.exists() && directory.isDirectory() && directory.canRead()
				&& directory.canWrite();
	}
	
	/**
	 * Initialisation du répertoire d'échange (depuis la conf ou depuis un file
	 * chooser). <br/>
	 * <b>Le chemin doit obligatoirement avoir été saisi et être valide avant de
	 * pouvoir utiliser l'application</b>
	 */
	protected void initDirectory() {
		String path = definePath();
		if(path.length()>0) {
			twitup.initDirectory(path);
		}else {
			
		}
	}
	
public String definePath() {
		
		Properties props = PropertiesManager.loadProperties(Constants.CONFIGURATION_FILE);
		String path = props.getProperty(Constants.CONFIGURATION_KEY_EXCHANGE_DIRECTORY);
		File destination = new File(path);
		if(!isValideExchangeDirectory(destination)) {
			//System.out.println(Constants.CONFIGURATION_FILE);
			twitup.getmMainView().getPath();
			props = PropertiesManager.loadProperties(Constants.CONFIGURATION_FILE);
			return  path;
		}else {
			return props.getProperty(Constants.CONFIGURATION_KEY_EXCHANGE_DIRECTORY);
		}
	}

}

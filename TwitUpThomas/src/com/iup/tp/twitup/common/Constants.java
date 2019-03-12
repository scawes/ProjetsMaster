package com.iup.tp.twitup.common;

import java.util.UUID;

/**
 * Classe de contantes de l'appli.
 * 
 * @author S.Lucas
 */
public interface Constants {
	/**
	 * Extension des fichiers XML des User
	 */
	public static final String USER_FILE_EXTENSION = "usr";

	/**
	 * Extension des fichiers XML des Twit
	 */
	public static final String TWIT_FILE_EXTENSION = "twt";

	/**
	 * Extension des fichiers XML des DB utilisateur
	 */
	public static final String DB_FILE_EXTENSION = "db";

	/**
	 * Répertoire des fichiers temporaires du système.
	 */
	public static final String SYSTEM_TMP_DIR = System.getProperty("java.io.tmpdir");

	/**
	 * Séparateur de fichier du système.
	 */
	public static final String SYSTEM_FILE_SEPARATOR = System.getProperty("file.separator");

	/**
	 * Caractère pour délimiter les tags référencant des utilisateurs.
	 */
	public static final String USER_TAG_DELIMITER = "@";

	/**
	 * Caractère pour délimiter les tags référencant des mots-clés.
	 */
	public static final String WORD_TAG_DELIMITER = "#";

	/**
	 * Identifiant de l'utilisateur inconnu.
	 */
	public static final UUID UNKNONWN_USER_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");

	/**
	 * Fichier de configuration de l'application.
	 */
	public static final String CONFIGURATION_FILE = "src/resources/configuration.properties";

	/**
	 * Clé de configuration pour la sauvegarde du répertoire d'échange.
	 */
	public static final String CONFIGURATION_KEY_EXCHANGE_DIRECTORY = "EXCHANGE_DIRECTORY";

	/**
	 * Clé de configuration pour l'UI
	 */
	public static final String CONFIGURATION_KEY_UI_CLASS_NAME = "UI_CLASS_NAME";

	/**
	 * Clé de configuration pour le mode bouchoné
	 */
	public static final String CONFIGURATION_KEY_MOCK_ENABLED = "MOCK_ENABLED";
	
	/**
	 * Clé de configuration pour le mode bouchoné
	 */
	public static final String CONFIGURATION_KEY_LOOK_AND_FEEL = "LOOK_AND_FEEL";
	
	/**
	 * Clé de configuration pour le language de l'application
	 */
	public static final String CONFIGURATION_KEY_LANGUAGE = "LANGUAGE";
	
	public static final String CONFIGURATION_KEY_COUNTRY = "COUNTRY";
	
	public static final Integer CONFIGURATION_MAX_SIZE_TWIT = 150;
	
	public static final String CONFIGURATION_LOOK_AND_FEEL_METALLOOKANDFEEL = "javax.swing.plaf.metal.MetalLookAndFeel";

	public static final String IMG_ICON_EXIT = "/images/exitIcon_20.png";
	
	public static final String IMG_ICON_LOGO = "/images/logoIUP_20.jpg";
	
	public static final String IMG_LARGE_LOGO ="/images/logoIUP_50.jpg";
	
	public static final String TXT_APPLICATION_NAME = "applicationName";
	
	public static final String TXT_SEND = "Send";

	public static final String TXT_CONTENT = "content";
	
	public static final String TXT_LOGIN = "login";
	
	public static final String TXT_PASSWORD = "password";
	
	public static final String TXT_CONNECT = "connect";
	
	public static final String TXT_FILE = "file";
	
	public static final String TXT_SUBSCRIBE = "subscribe";
	
	public static final String TXT_SEARCH = "search";
	
	public static final String TXT_QUIT = "quit";

	public static final String TXT_SET_DIRECTORY = "setDirectory";

	public static final String TXT_ABOUT = "about";

	public static final String TXT_TAG = "tag";

	public static final String TXT_INVALIDE_TAG = "invalideTag";

	public static final String TXT_INVALIDE_USER = "invalideUser";

	public static final String TXT_INVALIDE_PASSWORD = "invalidePassword";

	public static final String TXT_INVALIDE_TAG_USE = "invalideTagUse";
	
	public static final String TXT_BACK = "back";

	public static final String TXT_ACCOUNT = "account";

	public static final String TXT_LOGOUT = "logout";

	public static final String TXT_UPDATE_USER = "updateUser";

	public static final String TXT_AVATAR = "avatar";

	public static final String TXT_CHOOSE_FILE = "chooseFile";
	
	public static final String TXT_FOLLOW = "follow";
	
	public static final String TXT_UNFOLLOW = "unfollow";
	
	
	
	//public static final String TXT_FILE = "content";
}

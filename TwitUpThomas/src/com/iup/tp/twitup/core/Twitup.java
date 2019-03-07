package com.iup.tp.twitup.core;

import java.util.Properties;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.common.PropertiesManager;
import com.iup.tp.twitup.datamodel.Database;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.events.file.IWatchableDirectory;
import com.iup.tp.twitup.events.file.WatchableDirectory;
import com.iup.tp.twitup.ihm.TwitupMainView;
import com.iup.tp.twitup.ihm.TwitupMock;

/**
 * Classe principale l'application.
 * 
 * @author S.Lucas
 */
public class Twitup{
	/**
	 * Base de données.
	 */
	protected IDatabase mDatabase;

	/**
	 * Gestionnaire des entités contenu de la base de données.
	 */
	protected EntityManager mEntityManager;

	/**
	 * Vue principale de l'application.
	 */
	protected TwitupMainView mMainView;
	
	/**
	 * Classe de surveillance de la dataBase
	 */
	protected DataBaseObserver mBaseObserver;

	/**
	 * Classe de surveillance de répertoire
	 */
	protected IWatchableDirectory mWatchableDirectory;

	/**
	 * Répertoire d'échange de l'application.
	 */
	protected String mExchangeDirectoryPath;

	/**
	 * Idnique si le mode bouchoné est activé.
	 */
	protected boolean mIsMockEnabled = true;

	/**
	 * Nom de la classe de l'UI.
	 */
	protected String mUiClassName;
	
	
	
	protected TwitupDirectory directory;
	
	protected TwitupFrame frame;
	
	protected TwitupUser user;
	
	protected TwitupTwit twit;

	/**
	 * Constructeur.
	 */
	public Twitup() {
		
		// Init du look and feel de l'application
		this.initLookAndFeel();

		// Initialisation de la base de données
		this.initDatabase();
		//initialisation du comportement d'evenement
		this.initBaseObserver();

		if (this.mIsMockEnabled) {
			// Initialisation du bouchon de travail
			this.initMock();
		}
		initControlerDirectory();
		initControlerUser();
		initControlerTwit();
		
		// Initialisation de l'IHM
		this.initGui();
		
		// Initialisation des controler
		this.initControlerFrame();

		// Initialisation du répertoire d'échange
		directory.initDirectory();
	}

	/**
	 * Initialisation du look and feel de l'application.
	 */
	protected void initLookAndFeel() {
		Properties props = PropertiesManager.loadProperties(Constants.CONFIGURATION_FILE);
		String lookandfeel = props.getProperty(Constants.CONFIGURATION_KEY_LOOK_AND_FEEL);
		try {
			if(!lookandfeel.isEmpty()) {
				UIManager.setLookAndFeel(lookandfeel);
			}else {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e ) {
			e.printStackTrace();
		} 
	}

	/**
	 * Initialisation des controleur
	 */
	protected void initControlerDirectory() {
		directory = new TwitupDirectory(this);
	}
	
	protected void initControlerFrame() {
		frame = new TwitupFrame(this) ;
	}
	
	protected void initControlerUser() {
		user = new TwitupUser(this) ;
	}
	
	protected void initControlerTwit() {
		twit = new TwitupTwit(this) ;
		mDatabase.addObserver(twit);
	}
	
	/**
	 * Initialisation de l'interface graphique.
	 */
	protected void initGui() {
		mMainView = new TwitupMainView(directory);
	}

	/**
	 * Initialisation du répertoire d'échange (depuis la conf ou depuis un file
	 * chooser). <br/>
	 * <b>Le chemin doit obligatoirement avoir été saisi et être valide avant de
	 * pouvoir utiliser l'application</b>
	 */
	/*protected void initDirectory() {
		String path = definePath();
		if(path.length()>0) {
			initDirectory(path);
		}else {
			
		}
	}*/
	
	/**
	 * Initialisation du de l'observer de la base
	 */
	protected void initBaseObserver() {
		mBaseObserver	= new DataBaseObserver();
		mDatabase.addObserver(mBaseObserver);

	}

	/**
	 * Initialisation du mode bouchoné de l'application
	 */
	protected void initMock() {
		TwitupMock mock = new TwitupMock(this.mDatabase, this.mEntityManager);
		mock.showGUI();
	}

	/**
	 * Initialisation de la base de données
	 */
	protected void initDatabase() {
		mDatabase = new Database();
		mEntityManager = new EntityManager(mDatabase);
	}

	/**
	 * Initialisation du répertoire d'échange.
	 * 
	 * @param directoryPath
	 */
	public void initDirectory(String directoryPath) {
		mExchangeDirectoryPath = directoryPath;
		mWatchableDirectory = new WatchableDirectory(directoryPath);
		mEntityManager.setExchangeDirectory(directoryPath);

		mWatchableDirectory.initWatching();
		mWatchableDirectory.addObserver(mEntityManager);
	}

	public void show() {
		//if(!mExchangeDirectoryPath.isEmpty()) {
			frame.globalView();
			mMainView.setVisible(true);
			
		//}
		
		
	}

	public IDatabase getmDatabase() {
		return mDatabase;
	}

	public TwitupMainView getmMainView() {
		return mMainView;
	}

	public TwitupDirectory getDirectory() {
		return directory;
	}

	public TwitupFrame getFrame() {
		return frame;
	}

	public TwitupUser getUser() {
		return user;
	}

	public TwitupTwit getTwit() {
		return twit;
	}

	public EntityManager getmEntityManager() {
		return mEntityManager;
	}

	public String getmExchangeDirectoryPath() {
		return mExchangeDirectoryPath;
	}
}

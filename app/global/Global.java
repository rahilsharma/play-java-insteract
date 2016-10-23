package global;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;


import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.cache.Cache;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Application global settings.
 */
public class Global extends GlobalSettings {

	private static final String INSTERACT_FILE = "C://test1.txt";
	
	public void onStart(Application app) {
        Logger.info("Application has started");
        try {
			
		} catch (Exception e) {
			Logger.error("Could not load the " + INSTERACT_FILE + " file. Error: "
					+ e.getLocalizedMessage());
		}
    }

	
    
    public void onStop(Application app) {
        Logger.info("Application shutdown...");
    }
}
package global;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import model.Insteract;
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
			loadInsteractData();
		} catch (Exception e) {
			Logger.error("Could not load the " + INSTERACT_FILE + " file. Error: "
					+ e.getLocalizedMessage());
		}
    }

	/**
     * Loads in the cache the insteract from the insteract file.
     * 
     * @throws Exception
     * 		If was not possible to load the insteract, for whatever reason.
     */
     private void loadInsteractData() throws Exception{
    	Logger.info("Loading " + INSTERACT_FILE);
//    	InputStream is = Global.class.getResourceAsStream(INSTERACT_FILE);
    	InputStream is = new FileInputStream(INSTERACT_FILE);
    	ObjectMapper mapper = new ObjectMapper();
    	List<Insteract> insteract = mapper.readValue(is, new TypeReference<List<Insteract>>(){});
    	Cache.set("insteract", insteract,0);
    	Logger.info(insteract.size() + " insteract loaded");
//    	for(Example example:insteract){
//    		Logger.info(example.getId() + " " +  example.getActive());
//    	}
    	
    	
    }
    
    public void onStop(Application app) {
        Logger.info("Application shutdown...");
    }
}
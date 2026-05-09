package utils;
import java.io.*;
import java.util.Properties;

public class ConfigManager {
	
	private static final String FILE = "config.properties";
	
	public static void saveLastDirectory(String path) 
		throws IOException{
			Properties props = new Properties();
			props.setProperty("lastDirectory", path);
			
			props.store(new FileOutputStream(FILE), null);

	
	}
	public static String loadLastDirectory()
            throws IOException {

        Properties props = new Properties();

        File file = new File(FILE);

        if(!file.exists())
            return null;

        props.load(new FileInputStream(file));

        return props.getProperty("lastDirectory");
	}
}

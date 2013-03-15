package common;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {

    private Properties properties;
    private String fileName;
//    LoggingSelenium selenium;

    public PropertyReader(String fileName){ //LoggingSelenium selenium
        this.fileName = fileName;
        this.properties = new Properties();
        //this.selenium=selenium;
        init();
    }

    private void init(){
        try {
            properties.load(new FileInputStream(new File(fileName)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String get(String key) throws NullValueExceptionHandler{

        if(properties.getProperty(key)==null)
            throw new NullValueExceptionHandler(key+"|You are reading invalid property from the properties files." +       //selenium,
                    "Please check the passed spelling argument / check the availability for argument in messages properties files");
        else
            return properties.getProperty(key);
    }
}

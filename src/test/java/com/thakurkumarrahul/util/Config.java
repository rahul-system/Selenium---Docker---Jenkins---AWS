package com.thakurkumarrahul.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Logger log = LoggerFactory.getLogger(Config.class);
    private static Properties properties;


    public static void initialize() {

//        load default properties
        properties = loadProperties();

//        override default properties
        for(String key: properties.stringPropertyNames()) {
            if(System.getProperties().containsKey(key)){
                properties.setProperty(key, System.getProperty(key));
            }
        }

//        print
        log.info("Test Properties");
        for(String key: properties.stringPropertyNames()) {
            log.info("{} = {}", key, properties.getProperty(key));
        }
        log.info("----------------");
    }

    public static String get(String key){
        return properties.getProperty(key);
    }



    public static Properties loadProperties() {
        Properties properties = new Properties();
        try ( InputStream stream = ResourceLoader.readResource(Constants.DEFAULT_PROPERTIES)) {
              properties.load(stream);
        } catch (Exception e) {
            log.error("Unable to load properties file {}", Constants.DEFAULT_PROPERTIES, e);
        }
        return properties;

    }
}

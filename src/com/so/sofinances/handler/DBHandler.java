package com.so.sofinances.handler;

import java.io.IOException;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.so.sofinances.model.User;

/**a mostly static class that facilitates access to the database.
 * 
 * @author kodyPC
 *
 */
public class DBHandler {
    /**
     * the container for the db.
     */
    private static ObjectContainer objectContainer = null;
    /**
     * the path of the db.
     */
    private static String path;
    /**
     * the ending porting of the path to the db.
     */
    private static final String FILENAME = "/androidDB.db4o";
    
    /**creates the static path to the database from the resource directory as a parameter.
     * and the filename
     * 
     * @param dir the resource directory of the application
     */
    public static void setPath(String dir) {
        path = dir + FILENAME;
    }
    
    /**Creates access to the database.
     * 
     * @return returns the object container, which is 
     * the object representation of the database
     */
    public static ObjectContainer db() {
    	ObjectContainer container = null;
        if (path == null) {
            //System.out.println("path not set");
            container = null;
        } else {
            try {
                if (objectContainer == null || objectContainer.ext().isClosed()) {
                    objectContainer = Db4oEmbedded.openFile(dbConfig(), path);
                }
                container = objectContainer;
            } catch (RuntimeException ie) {
                container = null;
            } catch (Exception e) {
            	container = null;
            }
        }
        
        return container;
    }
    
    /**Sets attributes for the configuration of the database.
     * 
     * @return the configuration for the database
     * @throws IOException thrown if an error occurs
     */
    private static EmbeddedConfiguration dbConfig() throws IOException {
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().objectClass(User.class).updateDepth(60);
        config.common().activationDepth(7);
        return config;
    }
    
    /**Closes the database.
     * 
     */
    public static void close() {
        if (objectContainer != null) {
            objectContainer.close();
        }
    }
    
    /**Clears the database.
     * 
     */
    public static void clear() {
        ObjectSet<Object> res = db().queryByExample(null);
        for (Object obj : res) {
            db().delete(obj);
        }
    }
    
    /**Commits the current user to the database.
     * 
     */
    public static void update() {
        db().store(UserHandler.getCU());
        db().commit();
    }
}

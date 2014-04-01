package com.so.sofinances.handler;

import java.io.IOException;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.so.sofinances.model.User;

public class DBHandler {
    private static ObjectContainer oc = null;
    private static String path;
    private static final String FILENAME = "/androidDB.db4o";
    
    public static void setPath(String dir){
        path = dir + FILENAME;
    }
    
    public static ObjectContainer db(){
        if (path == null){
            System.out.println("path not set");
            return null;
        } else {
            try {
                if (oc == null || oc.ext().isClosed()){
                    oc = Db4oEmbedded.openFile(dbConfig(), path);
                }
                return oc;
            } catch (Exception ie){
                return null;
            }
        }
    }
    
    private static EmbeddedConfiguration dbConfig() throws IOException{
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().objectClass(User.class).updateDepth(60);
        config.common().activationDepth(7);
        return config;
    }
    
    public static void close(){
        if (oc != null){
            oc.close();
        }
    }
    
    public static void clear(){
        ObjectSet<Object> res = db().queryByExample(null);
        for (Object a : res){
            db().delete(a);
        }
    }
    
    public static void update(){
        db().store(UserHandler.getCU());
        db().commit();
    }
}
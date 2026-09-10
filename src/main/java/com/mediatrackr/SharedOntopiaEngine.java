package com.mediatrackr;

import java.io.IOException;
import java.net.URL;

import com.mediatrackr.dao.Media;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import net.ontopia.topicmaps.core.TopicMapStoreIF;
import net.ontopia.topicmaps.impl.rdbms.RDBMSStoreFactory;
import net.ontopia.topicmaps.impl.rdbms.RDBMSTopicMapStore;
import net.ontopia.topicmaps.xml.XTMTopicMapReader;


@ApplicationScoped
public class SharedOntopiaEngine {
    private TopicMapStoreIF store;

    @PostConstruct
    public void init(){
        try {
            store = new RDBMSTopicMapStore("db.props");
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

            String resourcePath = "com/mediatrackr/schema.xtm";
            URL resourceUrl = classLoader.getResource(resourcePath);
            
            XTMTopicMapReader reader = new XTMTopicMapReader(resourceUrl);
            reader.setStoreFactory(new RDBMSStoreFactory("db.props"));

            reader.read();
            store.commit();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean addMedia(Media media){
        media.toTopicMap(store);
        try{
            store.commit();
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public Media getMedia(String tolog){
        
    }
}

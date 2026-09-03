package com.mediatrackr;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.mediatrackr.dao.Media;

import jakarta.enterprise.context.ApplicationScoped;
import net.ontopia.topicmaps.core.TopicMapBuilderIF;
import net.ontopia.topicmaps.core.TopicMapIF;
import net.ontopia.topicmaps.core.TopicMapStoreIF;
import net.ontopia.topicmaps.impl.rdbms.RDBMSStoreFactory;
import net.ontopia.topicmaps.impl.rdbms.RDBMSTopicMapStore;
import net.ontopia.topicmaps.xml.XTMTopicMapReader;


@ApplicationScoped
public class SharedOntopiaEngine {
    private final TopicMapIF topicMap;
    private final TopicMapStoreIF store;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public SharedOntopiaEngine(){
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

    }
}

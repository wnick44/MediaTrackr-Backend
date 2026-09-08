package com.mediatrackr.dao;

import jakarta.xml.bind.annotation.XmlRootElement;
import net.ontopia.topicmaps.core.TopicMapIF;
import net.ontopia.topicmaps.core.TopicMapStoreIF;

@XmlRootElement(name = "comics")
public class Comics extends Media{
    private String SKU;
    private String artist;

    private String author;

    @Override
    public void toTopicMap(TopicMapStoreIF store) {
       
    }

    
}

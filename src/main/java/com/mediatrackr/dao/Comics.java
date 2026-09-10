package com.mediatrackr.dao;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import net.ontopia.topicmaps.core.TopicIF;
import net.ontopia.topicmaps.core.TopicMapBuilderIF;
import net.ontopia.topicmaps.core.TopicMapIF;
import net.ontopia.topicmaps.core.TopicMapStoreIF;

@XmlRootElement(name = "comics")
public class Comics extends Media{
    private String SKU;
    private String artist;

    private String author;

    @XmlElement(name = "SKU")
    public void setSKU(String SKU){
        this.SKU = SKU;
    }

    public String getSKU(){
        return this.SKU;
    }
    
    @XmlElement(name = "Artist")
    public void setArtist(String artist){
        this.artist = artist;
    }

    public String getArtist(){
        return this.artist;
    }

    @XmlElement(name = "Author") 
    public void setAuthor(String author){
        this.author = author;
    }

    public String getAuthor(){
        return this.author;
    }

    @Override
    public void toTopicMap(TopicMapStoreIF store) {
       TopicMapIF map = store.getTopicMap();
        TopicMapBuilderIF builder = map.getBuilder();

        TopicIF topic = builder.makeTopic();
    }
    
}

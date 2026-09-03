package com.mediatrackr.dao;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import net.ontopia.topicmaps.core.TopicIF;
import net.ontopia.topicmaps.core.TopicMapBuilderIF;
import net.ontopia.topicmaps.core.TopicMapIF;
import net.ontopia.topicmaps.core.TopicMapStoreIF;

@XmlRootElement(name = "book")
public class Books extends Media {
    private String ISBN;
    private String author;

    @XmlElement(name = "Author")
    public void setAuthor(String author){
        this.author = author;
    }

    public String getAuthor(){
        return this.author;
    }

    @XmlElement(name = "ISBN")
    public void setISBN(String ISBN){
        this.ISBN = ISBN;
    }

    public String getISBN(){
        return ISBN;
    }

    @Override
    public void toTopicMap(TopicMapStoreIF store) {
        TopicMapIF map = store.getTopicMap();
        TopicMapBuilderIF builder = map.getBuilder();

        TopicIF topic = builder.makeTopic();

    }
}
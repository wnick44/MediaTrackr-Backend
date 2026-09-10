package com.mediatrackr.dao;

import jakarta.xml.bind.annotation.XmlElement;
import net.ontopia.topicmaps.core.TopicMapStoreIF;

public abstract class Media {
    private String title;

    private String publisher;
    private FormatType format;

    @XmlElement(name = "Title")
    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    @XmlElement(name = "Publisher")
    public String getPublisher(){
        return this.publisher;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    @XmlElement(name = "Format")
    public String getFormat(){
        return this.format.toString();
    }

    public void setFormat(String format){
        this.format.type = format;
    }

    public abstract void toTopicMap(TopicMapStoreIF store);

}

enum FormatType{
    CD("CD"), VINYL("Vinyl"), PRINT("Print"), 
    DIGITAL("Digital"), TRADE_PAPERBACK("Trade Paperback");
    String type;
    FormatType(String type){
        this.type = type;
    }
    @Override
    public String toString() {
        return type;
    }
}

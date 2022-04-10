package br.dev.yann.rssreader.model;



import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeName;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonTypeName("rss")
@JsonTypeInfo(include=As.WRAPPER_OBJECT, use=Id.NAME)
public class Rss {

  public Channel channel;

  public Rss() {
  }

  public Rss(Channel channel) {
    this.channel = channel;
  }

  @Override
  public String toString() {
    return channel.toString();
  }

  public String getTitle() {
    return channel.getTitle();
  }

  public String getDescription() {
    return channel.getDescription();
  }

  public String getLink() {
    return channel.getLink();
  }

  public Channel getChannel() {
    return channel;
  }

  public void setChannel(Channel channel) {
    this.channel = channel;
  }

}

package br.com.yann.rssreader.model;

import br.com.yann.rssreader.model.dto.Channel;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Rss {

  @XmlElement(name="channel")
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

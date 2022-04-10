package br.dev.yann.rssreader.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Channel {

  private String title;
  private String description;
  private String link;

  @XmlElement(name = "item")
  public List<Item> items = new ArrayList<>();


  public Channel() {
  }


  public String getLink() {
    return link;
  }

  public List<Item> getItems() {
    return items;
  }

  @Override
  public String toString() {
    return "title= " + getTitle() + System.lineSeparator() +
        "link = " + getLink() + System.lineSeparator() +
        "description = " + getDescription() + System.lineSeparator() +
        "itens = " + getItems()+
        "";
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
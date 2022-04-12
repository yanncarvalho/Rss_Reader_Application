package br.dev.yann.rssreader.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Item {

    private String title;
    private String link;
    private String descript;
    private String author;
    private String comments;


  public Item() {
    }


  public Item(String title, String link, String descript, String author, String comments) {
      this.title = title;
      this.link = link;
      this.descript = descript;
      this.author = author;
      this.comments = comments;
    }


  @Override
  public String toString() {
    return "{" +
      " title='" + getTitle() + "'" +
      ", link='" + getLink() + "'" +
      ", descript='" + getDescript() + "'" +
      ", author='" + getAuthor() + "'" +
      ", comments='" + getComments() + "'" +
      "}";
  }


  public String getTitle() {
    return this.title;
  }

  public String getLink() {
    return this.link;
  }

  public String getDescript() {
    return this.descript;
  }

  public String getAuthor() {
    return this.author;
  }

  public String getComments() {
    return this.comments;
  }


}

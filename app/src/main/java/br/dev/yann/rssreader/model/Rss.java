package br.dev.yann.rssreader.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import br.dev.yann.rssreader.model.rss_elements.Channel;

import com.fasterxml.jackson.annotation.JsonTypeName;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.Data;

@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonTypeName("rss")
@JsonTypeInfo(include=As.WRAPPER_OBJECT, use=Id.NAME)
@JsonPropertyOrder({"originalLink", "content"})
public class Rss {

  @XmlTransient
  private String originalLink;

  @JsonProperty ("content")
  private Channel channel;

}

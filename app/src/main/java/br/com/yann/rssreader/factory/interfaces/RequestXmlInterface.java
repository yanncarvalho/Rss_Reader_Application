package br.com.yann.rssreader.factory.interfaces;

import java.io.IOException;

import javax.enterprise.inject.Model;

import jakarta.xml.bind.JAXBException;


@Model
public interface RequestXmlInterface{

  public String getXml (String uri) throws IOException, JAXBException;

}


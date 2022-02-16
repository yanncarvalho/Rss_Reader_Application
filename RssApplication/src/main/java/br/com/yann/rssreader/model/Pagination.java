package br.com.yann.rssreader.model;

import java.util.Set;

import javax.enterprise.inject.Model;

@Model
public class Pagination<T> {

  private int offset; //a partir desse elemento
  private Set<T> content; //elementos
  private boolean last; //se é ultima pagina
  private boolean first; //se é primeira pagina
  private int page; //paginaAtual
  private int size; //quantidade por pagina -- campo pageble
  private int totalElements; //quantidade de elementos
  private int numberElements; //numero de elementos na pagina
  private int totalPage; //total de paginas

  public int getOffset() {
    return offset;
  }
  public int getNumberElements() {
    return numberElements;
  }
  public void setNumberElements(int numberElements) {
    this.numberElements = numberElements;
  }
  public int getTotalPage() {
    return totalPage;
  }
  public void setTotalPage(int totalPage) {
    this.totalPage = totalPage;
  }
  public void setOffset(int offset) {
    this.offset = offset;
  }
  public Set<T> getContent() {
    return content;
  }
  public void setContent(Set<T> content) {
    this.content = content;
  }
  public boolean isLast() {
    return last;
  }
  public void setLast(boolean last) {
    this.last = last;
  }
  public boolean isFirst() {
    return first;
  }
  public void setFirst(boolean first) {
    this.first = first;
  }
  public int getPage() {
    return page;
  }
  public void setPage(int page) {
    this.page = page;
  }
  public int getSize() {
    return size;
  }
  public void setSize(int size) {
    this.size = size;
  }
  public int getTotalElements() {
    return totalElements;
  }
  public void setTotalElements(int totalElements) {
    this.totalElements = totalElements;
  }
}

package br.com.yann.rssreader.model;

import java.util.List;

public class Pagination<T> {

  private int offset; //a partir desse elemento
  private List<T> content; //elementos
  private int page; //paginaAtual
  private int size; //numero de elementos na pagina
  private int totalElements; //quantidade de elementos
  private int totalPage; //total de paginas
  private boolean lastPage; //se é ultima pagina
  private boolean firstPage; //se é primeira pagina

  public Pagination( List<T> content, int page, int size, int offset) {
    this.offset = offset;
    this.content = content;
    this.page = page;
    page = page++;
    this.size = size;
    this.totalElements = content.size();
    this.totalPage = this.totalElements/1;
    this.firstPage = (page == 1);
    this.lastPage = (page == totalPage);
  }

  public int getOffset() {
    return offset;
  }

  public List<T> getContent() {
    return content;
  }

  public void setContent(List<T> content) {
    this.content = content;
  }

  public int getPage() {
    return page;
  }

  public int getSize() {
    return size;
  }

  public int getTotalElements() {
    return totalElements;
  }

  public int getTotalPage() {
    return totalPage;
  }

  public boolean isLastPage() {
    return lastPage;
  }

  public boolean isFirstPage() {
    return firstPage;
  }



}

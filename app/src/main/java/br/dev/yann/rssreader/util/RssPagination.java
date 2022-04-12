package br.dev.yann.rssreader.util;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.dev.yann.rssreader.model.Rss;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class RssPagination{

  private List<Rss> content;
  private int page;
  private int size;
  private int offset;
  private boolean firstPage;
  private boolean lastPage;
  private int totalPages;
  private int totalElements;

  public RssPagination(List<String> urls, int page, int size, int offset) {
    this.offset = offset;
    this.page = page;
    this.size = size;
    this.setContent(this.convertToContent(urls, page, size, offset));
    this.totalElements = urls.size();

    if(offset > this.totalElements) {
      this.totalPages = 1;
    } else {
      if (size == 0) size = 1;
      this.totalPages =  (int) Math.ceil(Math.abs(this.totalElements - offset)/(double) size);
    }
    this.firstPage = (page == 0);
    this.lastPage = (page >= totalPages-1);
  }

  public List<Rss> getContent() {
    return content;
  }

  public void setContent(List<Rss> content) {
    this.content = content;
  }

  private List<Rss> convertToContent(List<String> urls, int page, int size, int offset){

    int firstSublistIndex = (page * size) + offset;
    int lastSublistIndex = firstSublistIndex + size;
    int lastIndex = urls.size();
    if (firstSublistIndex < 0)  {
      lastSublistIndex = firstSublistIndex = 0;
    }
    else if (firstSublistIndex > lastIndex)   {
      lastSublistIndex = firstSublistIndex = lastIndex;
    }
    else if (lastSublistIndex > lastIndex)   {
      lastSublistIndex = lastIndex;
    }

    return urls.subList(firstSublistIndex, lastSublistIndex)
               .stream()
               .map(url -> RssConvertor.get(url))
               .collect(Collectors.toList());
  }

  public int getPage() {
    return page;
  }

  public int getSize() {
    return size;
  }


  public int getOffset() {
    return offset;
  }

  public boolean isFirstPage() {
    return firstPage;
  }


  public boolean isLastPage() {
    return lastPage;
  }


  public int getTotalPages() {
    return totalPages;
  }

  public int getTotalElements() {
    return totalElements;
  }


}

package br.com.yann.rssreader.util;

import java.util.List;
import java.util.stream.Collectors;

import br.com.yann.rssreader.model.Rss;

public class RssPagination{
//TODO EXCEÇÕES

  private int offset;
  private List<Rss> content;
  private int page;
  private int size;
  private int totalElements;
  private int totalPages;
  private boolean lastPage;
  private boolean firstPage;

  public RssPagination(List<String> urls, int page, int size, int offset) {
    this.offset = offset;
    this.page = page;
    this.size = size;
    this.content = this.convertToContent(urls, page, size, offset);
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
               .map(url -> RssConvertor.getRss(url))
               .collect(Collectors.toList());
  }
  public int getOffset() {
    return offset;
  }

  public List<Rss> getContent() {
    return content;
  }

  public void setContent(List<Rss> content) {
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

  public int getTotalPages() {
    return totalPages;
  }

  public boolean isLastPage() {
    return lastPage;
  }

  public boolean isFirstPage() {
    return firstPage;
  }

  @Override
  public String toString() {
    return "RssPagination [content=" + content + ", firstPage=" + firstPage + ", lastPage=" + lastPage + ", offset="
        + offset + ", page=" + page + ", size=" + size + ", totalElements=" + totalElements + ", totalPages="
        + totalPages + "]";
  }



}

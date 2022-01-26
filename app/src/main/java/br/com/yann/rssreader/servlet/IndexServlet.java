package br.com.yann.rssreader.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.yann.rssreader.util.RssConvertor;

@WebServlet("/rss")
public class IndexServlet extends HttpServlet{

  private static final long serialVersionUID = 1L;

  RssConvertor convertor = new RssConvertor(); 

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String url = "https://www.fashionlady.in/category/beauty-tips/feed";
    PrintWriter pw = resp.getWriter();
    pw.println(convertor.getRss(url));
  }
  
}

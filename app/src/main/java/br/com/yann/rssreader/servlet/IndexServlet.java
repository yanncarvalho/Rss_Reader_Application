package br.com.yann.rssreader.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import br.com.yann.rssreader.util.RssConvertor;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("rss")
public class IndexServlet extends HttpServlet{

  private static final long serialVersionUID = 1L;

  @Inject
  RssConvertor convertor;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String url = "https://www.fashionlady.in/category/beauty-tips/feed";
    PrintWriter pw = resp.getWriter();
    pw.println(convertor.getRss(url));
  }
  
}

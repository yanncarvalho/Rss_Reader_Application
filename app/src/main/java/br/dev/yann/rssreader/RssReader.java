package br.dev.yann.rssreader;


import java.util.Objects;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.google.common.primitives.Ints;

@ApplicationPath("")
public class RssReader extends Application{


      public static void main(String[] args) {
            Integer hashcode = Ints.tryParse(Objects.toString(null));
            if(hashcode !=null){
                  int i =  hashcode;
                  System.out.println("i: " + i);
            }


            System.out.println("hashcode " +  hashcode );


      }

}






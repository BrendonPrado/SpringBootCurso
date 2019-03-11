package br.com.gsw.springBootCurso.SpringBootCurso.resource.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {



    public static String decodeParam(String s) throws UnsupportedEncodingException {
      return URLDecoder.decode( s,"UTF-8" );
    }

    public static List<Integer> decodeIntList(String n){
        String[] vet = n.split( "," );
        List<Integer> categorias = new ArrayList<Integer>();
        for (String cate: vet){
            categorias.add(Integer.parseInt( cate ));
        }
        return categorias;
    }
}

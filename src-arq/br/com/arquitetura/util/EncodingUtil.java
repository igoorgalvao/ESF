package br.com.arquitetura.util;

import org.jsoup.Jsoup;

public class EncodingUtil {

	public static String parseHtml(String texto) throws Exception {
		return Jsoup.parse(texto).text();
	}
}

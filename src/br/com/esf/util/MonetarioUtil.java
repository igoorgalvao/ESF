package br.com.esf.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Classe que formata um Double ou um Float e retorna a String formatada
 * 
 * @author bruno melo
 * 
 */
public class MonetarioUtil {
	private static final Locale BRAZIL = new Locale("pt", "BR");
	private static final DecimalFormatSymbols REAIS = new DecimalFormatSymbols(BRAZIL);

	private static final DecimalFormat DINHEIRO_REAIS = new DecimalFormat("###,###,##0.00", REAIS);
	private static final DecimalFormat DINHEIRO_REAIS_COM_RS = new DecimalFormat("¤ ###,###,##0.00", REAIS);

	public static String formatComRS(Double dinheiro) {
		return DINHEIRO_REAIS_COM_RS.format(dinheiro);
	}

	public static String formatComRS(Float dinheiro) {
		return DINHEIRO_REAIS_COM_RS.format(dinheiro);
	}

	public static String format(Double dinheiro) {
		return DINHEIRO_REAIS.format(dinheiro);
	}

	public static String format(BigDecimal dinheiro) {
		return DINHEIRO_REAIS.format(dinheiro);
	}

	public static String format(Float dinheiro) {
		return DINHEIRO_REAIS.format(dinheiro);
	}

}

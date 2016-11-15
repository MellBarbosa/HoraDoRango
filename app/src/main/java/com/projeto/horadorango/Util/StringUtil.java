package com.projeto.horadorango.Util;

/**
 * Created by Mell on 27/10/2016.
 */

public class StringUtil {

    public static final String APP_TAG = "HORA_DO_RANGO";
    public static String preencher(String palavra,char preenchimento,int tamanho, boolean direito)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(palavra);
        while(sb.toString().length()<tamanho)
        {
            if(direito)
                sb.append(preenchimento);
            else
                sb.insert(0, preenchimento);
        }
        return sb.substring(0,tamanho);
    }
}

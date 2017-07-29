package br.com.betoalves.carcrud.utils;

/**
 * Created by Trovata on 27/06/2017.
 */

public final class StringUtils {
    public static boolean isNotNullOrEmpty(String string){
        return string != null && string.length() > 0;
    }

    public static boolean isNullOrEmpty(String string){
        return !isNotNullOrEmpty(string);
    }
}

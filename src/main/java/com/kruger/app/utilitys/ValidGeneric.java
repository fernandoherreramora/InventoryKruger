package com.kruger.app.utilitys;

import lombok.extern.log4j.Log4j2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
public class ValidGeneric {

    public Boolean validStrings(String str){
        Pattern pat = Pattern.compile(new String("^[A-Za-z\\s]+$"));
        Matcher mat = pat.matcher(str);
        return mat.matches();
    }

    public Boolean validNumber(String str){
        try {
            Long number = Long.parseLong(str);
            return  true;
        }catch (Exception e){
            log.info(e.getMessage());
            return false;
        }
    }
}

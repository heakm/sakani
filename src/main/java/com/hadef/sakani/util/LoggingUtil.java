package com.hadef.sakani.util;

import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.util.Calendar;


@NoArgsConstructor
public class LoggingUtil {
    private static Logger log = LoggerFactory.getLogger(LoggingUtil.class);
    @Value("${application.name}")
    private static String application;
    public static void logInfo(String source, String message,Object... args){
        log.info("Timestamp: { %s } | { %s } : "+String.format(message,args),getTimestamp(),source,application);
    }
    public static void logError(String ex, Throwable cause){
        log.error("Timestamp: {"+ex+"}");
    }
    public static void logError(String ex, Throwable cause,HttpHeaders headers, HttpStatus status, WebRequest request){
        log.error("Timestamp: {%s} | {%s} | {%s} : "+ex,getTimestamp(),status.value(),request.toString());
    }
    public static void logError(String source, String message) {
        log.error("Timestamp: {%s} | {%s} | {%s} "+message ,getTimestamp(),source,application);
    }
    public static void logError(String source, String message,Object... args){
        log.info("Timestamp: { %s } | { %s } : "+String.format(message,args),getTimestamp(),source,application);
    }
    public static String getTimestamp(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return formatter.format(calendar.getTime());
    }
}

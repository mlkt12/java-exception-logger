package com.company;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    protected static final Logger logger = Logger.getLogger("LOG");

    public static void main(String[] args) {

        try {
            String s = null;
            s.toUpperCase();
        } catch (Exception e) {
            writeLogException(e);
        }

    }

    public enum LogLevel {
        SEVERE,
        WARNING,
        INFO,
        FINE,
        FINER,
        FINEST,
        CONFIG
    }

    private static void writeLogException(Exception e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String sStackTrace = sw.toString(); // stack trace as a string
        log(e,LogLevel.WARNING,sStackTrace);
    }

    public static void log(Exception ex, LogLevel level, String msg){

        FileHandler fh = null;
        try {
            fh = new FileHandler("log.xml",true);
            logger.addHandler(fh);
            switch (level) {
                case SEVERE:
                    logger.log(Level.SEVERE, msg, ex);
                    break;
                case WARNING:
                    logger.log(Level.WARNING, msg, ex);
                    break;
                case INFO:
                    logger.log(Level.INFO, msg, ex);
                    break;
                case CONFIG:
                    logger.log(Level.CONFIG, msg, ex);
                    break;
                case FINE:
                    logger.log(Level.FINE, msg, ex);
                    break;
                case FINER:
                    logger.log(Level.FINER, msg, ex);
                    break;
                case FINEST:
                    logger.log(Level.FINEST, msg, ex);
                    break;
                default:
                    logger.log(Level.CONFIG, msg, ex);
                    break;
            }
        } catch (IOException | SecurityException ex1) {
            logger.log(Level.SEVERE, null, ex1);
        } finally{
            if(fh!=null)fh.close();
        }
    }
}

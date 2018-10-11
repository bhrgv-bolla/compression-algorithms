package org.bbolla.algorithms.compression.lossless;

import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Random;

/**
 * Simple string compression.
 * Default charset java uses: utf-8?
 */
@Slf4j
public class DemoStringCompression {

    private static final String input;

    static {
        log.info("Default charset that java uses :" + Charset.defaultCharset().toString());
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        Random rand = new Random();
        for(int i=0; i<10000; i++) {
            for(int j=0; j<1000; j++) {
                pw.println((char) rand.nextInt(5000));
            }
        }
        pw.flush();
        pw.close();
        input = sw.toString();
        int bytes = input.getBytes().length;
        //43 MB string.
        log.info("Input String size : " + bytes + " bytes => " + bytes / (1024 * 1024) +" MB");
    }

    /**
     * Returns a compressed string
     * @param uncompressed
     * @return
     */
    public static String compress(String uncompressed) {
        return "";
    }


    /**
     * Returns an uncompressed string
     * @param compressed
     * @return
     */
    public static String uncompress(String compressed) {
        return "";
    }

    public static void main(String[] args) {
        String compressed = compress(input);
        log.info("Compressed string size is : {} bytes => {} MB", compressed.getBytes().length, compressed.getBytes().length / (1024 * 1024));
        String original = uncompress(compressed);
        assert original.equals(input);
    }

}

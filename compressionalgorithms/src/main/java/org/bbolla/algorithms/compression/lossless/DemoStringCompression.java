package org.bbolla.algorithms.compression.lossless;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Simple string compression.
 * Default charset java uses: utf-8?
 */
@Slf4j
public class DemoStringCompression {

    private static final String input;

    static {
        log.info("Default charset that java uses : " + Charset.defaultCharset().toString());
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 1000; j++) {
                sb.append((char) rand.nextInt(100));
            }
            pw.println(sb.toString());
        }
        pw.flush();
        pw.close();
        input = sw.toString();
        int bytes = input.getBytes().length;
        //43 MB string.
        log.info("Input String size : " + bytes + " bytes => " + bytes / (1024 * 1024) + " MB");
    }

    /**
     * Returns a compressed string
     *
     * @param uncompressed
     * @return
     */
    public static String compress(String uncompressed) {

        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(input.getBytes(StandardCharsets.UTF_8));
            gzipOutputStream.finish();
            gzipOutputStream.flush();
            gzipOutputStream.close();
            return Base64.getUrlEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        } catch (Exception ex) {
            throw new RuntimeException("Error while compressing! cannot compress : ", ex);
        }
    }


    /**
     * Returns an uncompressed string
     *
     * @param compressed
     * @return
     */
    public static String uncompress(String compressed) {
        try {
            log.info("preview compressed string: {}", compressed.substring(0, 20)); //end index >>>>> greater than 20
            byte[] decoded = Base64.getUrlDecoder().decode(compressed);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decoded); //uses default charset.
            GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);
            return IOUtils.toString(gzipInputStream, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            throw new RuntimeException("Error while uncompressing, cannot uncompress", ex);
        }
    }

    public static void main(String[] args) {
        long current = System.currentTimeMillis();
        String compressed = compress(input);
        log.info("Compressed string size is : {} bytes => {} MB", compressed.getBytes().length
                , compressed.getBytes().length / (1024 * 1024));
        log.info("compression took: {}", System.currentTimeMillis() - current);
        logCompressionStats(input, compressed);
        current = System.currentTimeMillis();
        String uncompressed = uncompress(compressed);
        log.info("de-compression took: {}", System.currentTimeMillis() - current);
        log.info("uncompressed {} = original {} ?", uncompressed.length()
                , input.length());
        assert uncompressed.equals(input);
    }

    private static void logCompressionStats(String input, String compressed) {
        Integer inputSize = input.getBytes().length;
        Integer compressedSize = compressed.getBytes().length;
        double ratio = ((double) inputSize) / ((double) compressedSize);
        log.info("Compression ratio : {}", ratio);
    }

}

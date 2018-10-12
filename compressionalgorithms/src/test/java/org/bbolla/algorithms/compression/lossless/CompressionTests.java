package org.bbolla.algorithms.compression.lossless;


import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.reflections.Reflections;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Set;

@Slf4j
public class CompressionTests {

    @Test
    public void testsCompressionAlgorithm() {
        Reflections reflections = new Reflections(CompressionTests.class.getPackage());
        Set<Class<? extends CompressionTechnique>> subTypes = reflections.getSubTypesOf(CompressionTechnique.class);
        for(Class<? extends CompressionTechnique> impl: subTypes) {
            log.info("Implementation : {}", impl.getName());
            try {
                CompressionTechnique instance = impl.newInstance();
                //TODO write code for testing compress, decompress methods; compression ratios; and other stats like savings.
                //TODO for different inputs log stats.
                String input = "";
                Reader inputReader = new StringReader(input);
                Writer compressedOutput = new StringWriter();
                instance.compress(inputReader, compressedOutput);
                Reader compressedInput = new StringReader(compressedOutput.toString());
                Writer deCompressedOutput = new StringWriter();
                instance.deCompress(compressedInput, deCompressedOutput);

                Assert.assertEquals("input length should be equal to decompressed length", input.getBytes().length, deCompressedOutput.toString().getBytes().length);
                logStats(compressedOutput.toString(), deCompressedOutput.toString());
            } catch (InstantiationException e) {
                throw new RuntimeException(impl.getSimpleName()+ " Need to have a default no args constructor", e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(impl.getSimpleName()+ " Constructor needs to be accessible", e);
            }
        }
    }

    /**
     * Log compression statistics
     * @param compressed
     * @param deCompressed
     */
    private void logStats(String compressed, String deCompressed) {
        byte[] compressedBytes = compressed.getBytes();
        byte[] deCompressedBytes = deCompressed.getBytes();
        log.info("Compression ratio: {}", deCompressedBytes.length / compressedBytes.length);
    }

}

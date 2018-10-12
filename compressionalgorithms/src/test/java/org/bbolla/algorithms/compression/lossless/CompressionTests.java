package org.bbolla.algorithms.compression.lossless;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.reflections.Reflections;

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
            } catch (InstantiationException e) {
                throw new RuntimeException(impl.getSimpleName()+ " Need to have a default no args constructor", e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(impl.getSimpleName()+ " Constructor needs to be accessible", e);
            }
        }
    }

}

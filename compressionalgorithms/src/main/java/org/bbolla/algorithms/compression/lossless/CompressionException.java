package org.bbolla.algorithms.compression.lossless;

public class CompressionException extends Exception {

    public CompressionException(String message) {
        super(message);
    }

    public CompressionException(String message, Exception ex) {
        super(message, ex);
    }
}

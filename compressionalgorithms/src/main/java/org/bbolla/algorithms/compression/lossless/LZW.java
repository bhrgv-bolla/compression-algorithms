package org.bbolla.algorithms.compression.lossless;

/**
 *  LZW compression
 *  Uses a string table to assign code words to sequences.
 *  The first 8 bits (0-255) use for the code words.
 */
public class LZW implements CompressionTechnique {

    @Override
    public String compress(String uncompressed) {
        return null;
    }

    @Override
    public String deCompress(String compressed) {
        return null;
    }
}

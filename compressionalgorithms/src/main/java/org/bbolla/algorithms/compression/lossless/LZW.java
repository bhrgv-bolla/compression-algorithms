package org.bbolla.algorithms.compression.lossless;

import java.io.Reader;
import java.io.Writer;

/**
 *  LZW compression
 *  Uses a string table to assign code words to sequences.
 *  The first 8 bits (0-255) use for the code words.
 */
public class LZW implements CompressionTechnique {

    @Override
    public void compress(Reader uncompressed, Writer writer) {

    }

    @Override
    public void deCompress(Reader compressed, Writer writer) {

    }
}

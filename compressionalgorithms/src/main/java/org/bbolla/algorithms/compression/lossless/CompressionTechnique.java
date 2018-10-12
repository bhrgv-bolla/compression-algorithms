package org.bbolla.algorithms.compression.lossless;

import java.io.Reader;
import java.io.Writer;

/**
 * General interface to be used by any compression program
 */
public interface CompressionTechnique {

    /**
     * Compress plain text.
     * @param uncompressed
     * @return
     */
    void compress(Reader uncompressed, Writer writer);

    /**
     * Decompress raw text.
     * @param compressed
     * @return
     */
    void deCompress(Reader compressed, Writer writer);
}

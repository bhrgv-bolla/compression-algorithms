package org.bbolla.algorithms.compression.lossless;

/**
 * General interface to be used by any compression program
 */
public interface CompressionTechnique {

    /**
     * Compress plain text.
     * @param uncompressed
     * @return
     */
    String compress(String uncompressed);

    /**
     * Decompress raw text.
     * @param compressed
     * @return
     */
    String deCompress(String compressed);
}

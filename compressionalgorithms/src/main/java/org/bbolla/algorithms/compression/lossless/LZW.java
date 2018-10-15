package org.bbolla.algorithms.compression.lossless;

import lombok.extern.slf4j.Slf4j;

import java.io.Reader;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LZW compression
 * Uses a string table to assign code words to sequences.
 * The first 8 bits (0-255) use for the code words.
 * <p>
 * Lempel Ziff Welch
 * <p>
 * Implementation Details:
 * https://www.geeksforgeeks.org/lzw-lempel-ziv-welch-compression-technique/
 *
 * ASCII table:
 * https://www.cs.cmu.edu/~pattis/15-1XX/common/handouts/ascii.html
 */
@Slf4j
public class LZW implements CompressionTechnique {

    private final static int totalBits = 12;
    private final static int sizeOfStringTable = (int) Math.pow(2, 12);
    private static int currentCodeIdx = 0;
    private final static Map<String, Integer> stringTable = new LinkedHashMap<>(sizeOfStringTable);

    static { //Add the first ascii code points. (0-255)
        for(currentCodeIdx=0; currentCodeIdx<256; currentCodeIdx++) stringTable.put("" + (char) currentCodeIdx, currentCodeIdx);
        log.info("Sample string table ({}) : {}", stringTable.size(), stringTable.toString());
    }


    @Override
    public void compress(Reader uncompressed, Writer writer) {


    }

    @Override
    public void deCompress(Reader compressed, Writer writer) {

    }
}

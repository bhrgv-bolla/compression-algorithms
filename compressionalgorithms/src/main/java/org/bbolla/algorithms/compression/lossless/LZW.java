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


    /**
     * Compression happens by replacing subsequences of repeated strings by code points in the new symbol table.
     *
     * PSEUDOCODE
     *   1     Initialize table with single character strings
     *   2     P = first input character
     *   3     WHILE not end of input stream
     *   4          C = next input character
     *   5          IF P + C is in the string table
     *   6            P = P + C
     *   7          ELSE
     *   8            output the code for P
     *   9          add P + C to the string table
     *   10           P = C
     *   11         END WHILE
     *   12    output code for P
     * @param uncompressed
     * @param writer
     */
    @Override
    public void compress(Reader uncompressed, Writer writer) throws CompressionException {
        try {
            //Initialize the single characters table.
            int currentCodeIdx = 0;
            Map<String, Integer> stringTable = new LinkedHashMap<>(sizeOfStringTable);
            for(currentCodeIdx=0; currentCodeIdx<256; currentCodeIdx++) stringTable.put("" + (char) currentCodeIdx, currentCodeIdx);

            String p = Character.valueOf((char) uncompressed.read()).toString();
            int read;
            while((read = uncompressed.read()) != -1) { //As long as their is input.
                char c = (char) read;
                if(stringTable.containsKey(p + c)) { //write the
                    p = p + c;
                } else {
                    writer.write(stringTable.get(p));
                    stringTable.put(p + c, currentCodeIdx++);
                    p = String.valueOf(c);
                }
            }
            writer.write(stringTable.get(p)); //last remaining p value.
        } catch (Exception ex) {
            throw new CompressionException("Exception occured while compressing", ex);
        }

    }

    /**
     * Decompression happens *NOT* by storing the stringTable, But by building one from the compressed stream. That's Brilliant!!!
     * 1    Initialize table with single character strings
     * 2    OLD = first input code
     * 3    output translation of OLD
     * 4    WHILE not end of input stream
     * 5        NEW = next input code
     * 6        IF NEW is not in the string table
     * 7               S = translation of OLD
     * 8               S = S + C
     * 9       ELSE
     * 10              S = translation of NEW
     * 11       output S
     * 12       C = first character of S
     * 13       OLD + C to the string table
     * 14       OLD = NEW
     * 15   END WHILE
     * @param compressed
     * @param writer
     * @throws CompressionException
     */
    @Override
    public void deCompress(Reader compressed, Writer writer) throws CompressionException {
        try {
            //Initialize the single characters table.
            int currentCodeIdx = 0;
            Map<Integer, String> stringTable = new LinkedHashMap<>(sizeOfStringTable);
            for(currentCodeIdx=0; currentCodeIdx<256; currentCodeIdx++) stringTable.put(currentCodeIdx, "" + (char) currentCodeIdx);


            //algorithm
            int old = compressed.read();
            int read;
            while((read = compressed.read()) != -1) {
                if(!stringTable.containsKey(read)) {
                    String s = stringTable.get(old); //translate old
                } else { //if that code word is not in the table? when would it not be in the table. When it is a sequence of strings number it wouldn't be there.

                }
            }

            char p = (char) compressed.read();
        } catch (Exception ex) {
            throw new CompressionException("Exception occured while de-compressing", ex);
        }
    }
}

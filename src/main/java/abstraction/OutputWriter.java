package abstraction;

/**
 *The OutputWriter interface represents the device where report will be rendered.
 * An implementation may be a writer of .doc file types, printing on paper or a simple writer
 * to console.
 *
 *
 * @author Todian Mishtaku
 *
 *
 *
 */

public interface OutputWriter {

    /**
     * Prints a text a device picked up in implementation
     * @param text
     */
    void write(String text);
}

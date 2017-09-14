package implementation;

import abstraction.OutputWriter;


/**
 *The OutputWriterImpl class fully implements interface OutputWriter interface by picking up console
 * as display device.
 *
 *
 * @author Todian Mishtaku
 *
 *
 *
 */
public class OutputWriterImpl implements OutputWriter {

    /**
     * Prints a text to console.
     * @param lines represents a bunch of lines to be printed out to console.
     */
    @Override
    public void write(String lines) {

        System.out.println(lines);
    }
}

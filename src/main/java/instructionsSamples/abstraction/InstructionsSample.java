package instructionsSamples.abstraction;

import abstraction.Instruction;

import java.util.Set;

/**
 * The interface InstructionsSample defines the set of methods to be implemented by any sample class
 * which include a sample set of instructions
 *
 *
 * @author Todian Mishtaku
 *
 */
public interface InstructionsSample {

    void createSamples();

    Set<Instruction> getInstructions();

    void display();
}

package implementation;

import abstraction.Instruction;
import abstraction.InstructionsReceiver;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * The InstructionsReceiverImpl class implements InstructionsReceiver interface. It is a simulation of an entry point of application where every
 * instruction sent by clients get received and recorded. To allow client send one or multiple instructions
 * this interface declares two methods for processing data: one for single received instruction and the other one for
 * a chunk of instructions.
 * The other method returns the set of all gathered instructions. The Set type is selected to avoid duplications of
 * already treated instructions for instance because of some network fault or whatever reason exactly same instruction
 * is issued more than once.
 *
 *
 *
 * @author Todian Mishtaku
 *
 *
 */
public class InstructionsReceiverImpl implements InstructionsReceiver {


    private Set<Instruction> instructions;

    public InstructionsReceiverImpl()
    {
        instructions=new HashSet<>();
    }


    /**
     * Adds the received instruction to the set which will keep all received instructions so far.
     * Note:
     * If settlement date would not have been adjusted in Instruction constructor, here would have been
     * an alternative place. Please have a look at files in archive folder. Their implementation is based on
     * the alternative that settlement date is not adjusted in Instruction constructor.
     * @param instruction
     */

    @Override
    public void ProcessInstruction(Instruction  instruction)
    {
        instructions.add(instruction);
    }

    /**
     * Adds the received instructions to the set which will keep all received instructions so far.
     * @param instructions
     */
    @Override
    public void ProcessInstructionsChunk(Set<Instruction> instructions)
    {
        instructions.stream().forEach(i->ProcessInstruction(i));
    }

    /**
     * Returns all received instructions so far.
     * @return set which includs all received instructions so far.
     */
    public Set<Instruction> getAllAccumulatedInstructions() {
        return instructions;
    }
}

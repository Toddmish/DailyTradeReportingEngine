package abstraction;

import java.util.Set;


/**
 *
 * The InstructionsReceiver interface defines a simple simulation of an entry point of application where every
 * instruction sent by clients get received and recorded. To allow client send one or multiple instructions
 * this interface declares two methods for processing data: one for single received instruction and the other one for
 * a chunk of instructions.
 * The other method returns the set of all gathered instructions. The Set type is selected to avoid duplications of
 * already treated instructions for instance because of some network fault or whatever reason exactly same instruction
 * is issued more than once.
 * Different implementations can decide to adjust settlement date at different stages of application cycle.
 * One can do it in ProcessInstruction(s) method when adding instruction one at earlier stage and one at later.
 * Notice: Implementation.InstructionImpl does it at earlier stage. With this implementation of Instruction interface
 * is assured that settlement date is done before instruction is passed to ProcessInstruction(s) method which further
 * assures that module of statistics will operate on instructions which already have Settlement date set appropriately.
 * Another scenario which assures the same, but in different way is placed
 * in Archive folder. There you can find another implementation of InstructionsReceiver which does adjustment of
 * settlement date of object of Instance type and returns an object of a type similar to Instruction with the only
 * difference that this similar interface assures that settlement date is already set and only objects of
 * this second type are accepted by module of statistics and printing.
 *
 *
 *
 * @author Todian Mishtaku
 *
 *
 */
public interface InstructionsReceiver {

    /**
     * Adds the received instruction to memory and eventually does some operations
     * on it based on kind of implementation.
     * @param instruction
     */
    void ProcessInstruction(Instruction instruction);

    /**
     * Adds the received instructions to memory and eventually does some operations
     * on them based on kind of implementation.
     * @param instructions
     */
    void ProcessInstructionsChunk(Set<Instruction> instructions);

    /**
     * Returns all received instructions
     * @return set including all received instructions
     */
    Set<Instruction> getAllAccumulatedInstructions();
}

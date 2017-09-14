package instructionsSamples.abstraction;

import abstraction.Instruction;

import java.util.Set;

/**
 * The abstract class AbstractInstructionsSample implements shared  methods by any sample class
 * which include a sample set of instructions and has to implement InstructionsSample interface.
 *
 *
 * @author Todian Mishtaku
 *
 */
public abstract class AbstractInstructionsSample implements InstructionsSample {

    protected Set<Instruction> instructions;

    /**
     * Returns all instructions saved in the class
     * @return set of instructions
     */
    public Set<Instruction> getInstructions() {return instructions;};

    /**
     * It simply prints all instructions
     */
    public void display()
    {
        getInstructions().forEach((inst->System.out.println(inst.toString())));
    }
}

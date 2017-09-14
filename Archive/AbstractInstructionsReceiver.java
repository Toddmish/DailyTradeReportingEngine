package implementation;

import abstraction.Instruction;
import abstraction.InstructionsReceiver;

import java.util.List;

/**
 *This class is responsible for determining the order of methods calls: first set settlment
 * date then call the method which will add it to memory. Template method pattern is used to achieve this.
 *
 *
 * @author Todian Mishtaku
 *
 *
 *
 * */

public abstract class AbstractInstructionsReceiver implements InstructionsReceiver {

    // Template Method which will determine the skeleton of processing a new incoming instruction
    // from client
    @Override
    public void ProcessInstruction(Instruction  instruction)
    {
        InstructionWithSettlDateAdjusted instructionWithSettlDateAdjusted=setInstructionSettlementDate(instruction);
        addInstruction(instructionWithSettlDateAdjusted);
    }

    // Template Method which will determine the skeleton of processing a new incoming instruction
    // from client
    @Override
    public void ProcessInstructionsChunk(List<Instruction> instructions)
    {
        instructions.stream().forEach(i->ProcessInstruction(i));
    }

    public abstract void setInstructionSettlementDate(Instruction instruction);

    public abstract void addInstruction(InstructionWithSettlDateAdjusted instruction);






}

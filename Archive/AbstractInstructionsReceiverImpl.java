package implementation;

import abstraction.GlobalWorkingWeekSystem;
import abstraction.Instruction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AbstractInstructionsReceiverImpl extends AbstractInstructionsReceiver {

    private List<InstructionWithSettlDateAdjusted> instructions;

    private GlobalWorkingWeekSystem globalWorkingWeekSystem;

    public AbstractInstructionsReceiverImpl()
    {
        globalWorkingWeekSystem=new GlobalWorkingWeekSystemImpl();
        instructions=new ArrayList<>();
    }

    @Override
    public InstructionWithSettlDateAdjusted setInstructionSettlementDate(Instruction instruction) {

      LocalDate date= globalWorkingWeekSystem.AdjustSettlementdateIfIsNotWorkingDay(instruction.getSettlementDate(),instruction.getCurrency());
      instruction.setSettlementDate(date);
      return new InstructionWithSettlDateAdjusted(instruction);
    }

    @Override
    public void addInstruction(InstructionWithSettlDateAdjusted instruction) {
        instructions.add(instruction);
    }



    public List<InstructionWithSettlDateAdjusted> getAllAccumulatedInstructions() {
        return instructions;
    }
}

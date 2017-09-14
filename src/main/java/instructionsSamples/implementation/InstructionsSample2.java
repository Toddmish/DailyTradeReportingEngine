package instructionsSamples.implementation;

import abstraction.GlobalWorkingWeekSystem;
import abstraction.Instruction;
import abstraction.OperationType;
import implementation.EntityImpl;
import implementation.GlobalWorkingWeekSystemImpl;
import implementation.InstructionImpl;
import instructionsSamples.abstraction.AbstractInstructionsSample;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.HashSet;


/**
 * A class which simply includes a sample set of simulated instructions.
 * It extends AbstractInstructionsSample which implements InstructionsSample interface.
 *
 *
 * @author Todian Mishtaku
 *
 */
public class InstructionsSample2 extends AbstractInstructionsSample {


    /*Itentionally I left Monday in the end, so that previous non-working day (for non AED/SAR areas) will fall onto this Monday).
     It should be remembered the very simple fact that Mondays of different week are not the same date!
     Considering the same reason(date when non-working day falls onto) it is assured that the order of the days
     above is good selection testing with SAR/AED areas as well*/
    private final LocalDate TUESDAY= LocalDate.of(2017,8,29);
    /*Uncoment and/or adjust if needed
    private final LocalDate WEDNESDAY= LocalDate.of(2017,8,30);
    private final LocalDate THURSDAY= LocalDate.of(2017,8,31);
    private final LocalDate FRIDAY =LocalDate.of(2017,9,01);
    private final LocalDate SATURDAY= LocalDate.of(2017,9,02);
    private final LocalDate SUNDAY= LocalDate.of(2017,9,03);*/
    private final LocalDate MONDAY= LocalDate.of(2017,9,04);

    public void createSamples() {

        GlobalWorkingWeekSystem globalWorkingWeekSystem=new GlobalWorkingWeekSystemImpl();

        Instruction instrFoo=new InstructionImpl(
                new EntityImpl("EntityFoo"),
                OperationType.BUY,
                BigDecimal.valueOf(1.5),
                Currency.getInstance("GBP"),
                MONDAY,
                MONDAY,  /*will remain on Monday*/
                3,
                BigDecimal.valueOf(50),
                globalWorkingWeekSystem);

        Instruction instrZoo=new InstructionImpl(
                new EntityImpl("EntityZoo"),
                OperationType.SELL,
                BigDecimal.valueOf(1.0),
                Currency.getInstance("EUR"),
                MONDAY,
                TUESDAY,  /*will remain on Monday*/
                3,
                BigDecimal.valueOf(10),
                globalWorkingWeekSystem);

        instructions = new HashSet<>();

        instructions.add(instrFoo);
        instructions.add(instrZoo);

    }

    public InstructionsSample2() {

        createSamples();
    }


}

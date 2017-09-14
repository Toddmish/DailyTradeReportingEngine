package instructionsSamples.implementation;

import abstraction.GlobalWorkingWeekSystem;
import abstraction.OperationType;
import implementation.EntityImpl;
import implementation.GlobalWorkingWeekSystemImpl;
import implementation.InstructionImpl;
import instructionsSamples.abstraction.AbstractInstructionsSample;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashSet;

public class InstructionsSample1 extends AbstractInstructionsSample {


    /*Intentionally I left Monday in the end, so that previous non-working day (for non AED/SAR areas) will fall onto this Monday).
     It should be remembered the very simple fact that Mondays of different week are not the same date!
     Considering the same reason(date when non-working day falls onto) it is assured that the order of the days
     above is good selection testing with SAR/AED areas as well*/
    private final LocalDate TUESDAY= LocalDate.of(2017,8,29);
    private final LocalDate WEDNESDAY= LocalDate.of(2017,8,30);
    private final LocalDate THURSDAY= LocalDate.of(2017,8,31);
    private final LocalDate FRIDAY =LocalDate.of(2017,9,01);
    private final LocalDate SATURDAY= LocalDate.of(2017,9,02);
    private final LocalDate SUNDAY= LocalDate.of(2017,9,03);
    private final LocalDate MONDAY= LocalDate.of(2017,9,04);

    public void createSamples() {

        GlobalWorkingWeekSystem globalWorkingWeekSystem=new GlobalWorkingWeekSystemImpl();
        instructions = new HashSet<>(Arrays.asList(

                //////////////////////////////////BUY/////////////////////////////////////
                //////////////////////////////////////////////////////////////////////////
                /*A chunk of instructions with trade Operation BUY and final setllementDate = Monday */
                new InstructionImpl(
                        new EntityImpl("Entity1"),
                        OperationType.BUY,
                        BigDecimal.valueOf(1),
                        Currency.getInstance("USD"),
                        MONDAY,
                        MONDAY,
                        3,
                        BigDecimal.valueOf(10),
                        globalWorkingWeekSystem),

                new InstructionImpl(
                        new EntityImpl("Entity1"),
                        OperationType.BUY,
                        BigDecimal.valueOf(1.5),
                        Currency.getInstance("USD"),
                        MONDAY,
                        SATURDAY, // must fall onto Monday
                        3,
                        BigDecimal.valueOf(20),
                        globalWorkingWeekSystem),

                new InstructionImpl(
                        new EntityImpl("Entity1"),
                        OperationType.BUY,
                        BigDecimal.valueOf(2),
                        Currency.getInstance("GBP"),
                        MONDAY,
                        SUNDAY, // must fall onto Monday
                        3,
                        BigDecimal.valueOf(5),
                        globalWorkingWeekSystem),

                /*A chunk of instructions with trade Operation BUY and final setllementDate = Monday */

                new InstructionImpl(
                        new EntityImpl("Entity7"),
                        OperationType.BUY,
                        BigDecimal.valueOf(0.80),
                        Currency.getInstance("JPY"),
                        MONDAY,
                        TUESDAY, // must fall onto Tuesday
                        3,
                        BigDecimal.valueOf(10),
                        globalWorkingWeekSystem),

                new InstructionImpl(
                        new EntityImpl("Entity7"),
                        OperationType.BUY,
                        BigDecimal.valueOf(0.90),
                        Currency.getInstance("AED"),
                        MONDAY,
                        TUESDAY, // must fall onto Tuesday
                        3,
                        BigDecimal.valueOf(10),
                        globalWorkingWeekSystem),

                // trade amount =51
                /*A chunk of instructions with with trade Operation BUY and setllementDate = Wednesday*/

                new InstructionImpl(
                        new EntityImpl("Entity2"),
                        OperationType.BUY,
                        BigDecimal.valueOf(1.5),
                        Currency.getInstance("EUR"),
                        MONDAY,
                        WEDNESDAY,
                        3,
                        BigDecimal.valueOf(10),
                        globalWorkingWeekSystem),

                new InstructionImpl(
                        new EntityImpl("Entity2"),
                        OperationType.BUY,
                        BigDecimal.valueOf(1.5),
                        Currency.getInstance("EUR"),
                        MONDAY,
                        WEDNESDAY,
                        3,
                        BigDecimal.valueOf(10),
                        globalWorkingWeekSystem),

                // trade amount 90

                /*A chunk of instructions with final setllementDate = Thursday. */

                new InstructionImpl(
                        new EntityImpl("Entity3"),
                        OperationType.BUY,
                        BigDecimal.valueOf(0.40),
                        Currency.getInstance("AED"),
                        MONDAY,
                        THURSDAY,
                        3,
                        BigDecimal.valueOf(20),
                        globalWorkingWeekSystem),
                //trade amount 24

                /*A chunk of instructions with final setllementDate = Friday. */

                new InstructionImpl(
                        new EntityImpl("Entity4"),
                        OperationType.BUY,
                        BigDecimal.valueOf(1.50),
                        Currency.getInstance("GBP"),
                        MONDAY,
                        FRIDAY,
                        3,
                        BigDecimal.valueOf(30),
                        globalWorkingWeekSystem),


                new InstructionImpl(
                        new EntityImpl("Entity4"),
                        OperationType.BUY,
                        BigDecimal.valueOf(1.0),
                        Currency.getInstance("SEK"),
                        MONDAY,
                        FRIDAY,
                        3,
                        BigDecimal.valueOf(30),
                        globalWorkingWeekSystem),

                //trade amount 225

                /*A chunk of instructions with final setllementDate = Saturday =Empty set as it is holiday for all currency areas */

                /*A chunk of instructions with final setllementDate = Sunday - there may fall only instructions with currency area AED/SAR */

                new InstructionImpl(
                        new EntityImpl("Entity5"),
                        OperationType.BUY,
                        BigDecimal.valueOf(0.40),
                        Currency.getInstance("AED"),
                        MONDAY,
                        FRIDAY,
                        3,
                        BigDecimal.valueOf(30),
                        globalWorkingWeekSystem),

                new InstructionImpl(
                        new EntityImpl("Entity5"),
                        OperationType.BUY,
                        BigDecimal.valueOf(0.50),
                        Currency.getInstance("SAR"),
                        MONDAY,
                        SATURDAY,
                        3,
                        BigDecimal.valueOf(30),
                        globalWorkingWeekSystem),


                // trade amount=36+45 =81


                //////////////////////////////////SELL/////////////////////////////////////
                //////////////////////////////////////////////////////////////////////////
                /*A chunk of instructions with trade Operation SELL and setllementDate = Tuesday*/

                new InstructionImpl(
                        new EntityImpl("Entity6"),
                        OperationType.SELL,
                        BigDecimal.valueOf(1.5),
                        Currency.getInstance("USD"),
                        MONDAY,
                        TUESDAY,
                        3,
                        BigDecimal.valueOf(10),
                        globalWorkingWeekSystem),

                new InstructionImpl(
                        new EntityImpl("Entity6"),
                        OperationType.SELL,
                        BigDecimal.valueOf(2),
                        Currency.getInstance("GBP"),
                        MONDAY,
                        TUESDAY,
                        3,
                        BigDecimal.valueOf(1),
                        globalWorkingWeekSystem),

                // Trade Amount  51


              /*A chunk of instructions with trade Operation SELL and setllementDate = Sunday */

                new InstructionImpl(
                        new EntityImpl("Entity8"),
                        OperationType.SELL,
                        BigDecimal.valueOf(1.5),
                        Currency.getInstance("AED"),
                        MONDAY,
                        SUNDAY,  /*will remain Sunday*/
                        3,
                        BigDecimal.valueOf(100),
                        globalWorkingWeekSystem),

                new InstructionImpl(
                        new EntityImpl("Entity9"),
                        OperationType.SELL,
                        BigDecimal.valueOf(1.5),
                        Currency.getInstance("AED"),
                        MONDAY,
                        SATURDAY,  /*must fall on Sunday*/
                        3,
                        BigDecimal.valueOf(10),
                        globalWorkingWeekSystem),

                new InstructionImpl(
                        new EntityImpl("Entity9"),
                        OperationType.SELL,
                        BigDecimal.valueOf(1.5),
                        Currency.getInstance("AED"),
                        MONDAY,
                        FRIDAY,  /*must fall on Sunday*/
                        3,
                        BigDecimal.valueOf(1000),
                        globalWorkingWeekSystem),

              /*Trade amount for Sunday for sell 4995*/

              /*A chunk of instructions with trade Operation SELL and setllementDate = Monday */

                new InstructionImpl(
                        new EntityImpl("Entity9"),
                        OperationType.SELL,
                        BigDecimal.valueOf(2),
                        Currency.getInstance("GBP"),
                        MONDAY,
                        SUNDAY,  /*must fall on Monday*/
                        3,
                        BigDecimal.valueOf(1000),
                        globalWorkingWeekSystem),

                new InstructionImpl(
                        new EntityImpl("Entity10"),
                        OperationType.SELL,
                        BigDecimal.valueOf(2),
                        Currency.getInstance("GBP"),
                        MONDAY,
                        MONDAY,  /*will remain on Monday*/
                        3,
                        BigDecimal.valueOf(100),
                        globalWorkingWeekSystem)

                // Trade Amount  6600


        ));
    }

    public InstructionsSample1() {

        createSamples();
    }



}

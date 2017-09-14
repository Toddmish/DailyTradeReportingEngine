import abstraction.Instruction;
import abstraction.InstructionsReceiver;
import abstraction.ReportPrinter;
import abstraction.StatisticsGenerator;
import implementation.InstructionsReceiverImpl;
import implementation.ReportPrinterImpl;
import implementation.StatisticsGeneratorImpl;
import instructionsSamples.implementation.InstructionsSample1;
import instructionsSamples.implementation.InstructionsSample2;
import implementation.OutputWriterImpl;

import java.util.Set;

public class Main {
    public static void main(String[] args) {

        System.out.println("Start Daily Trade Reporting Engine...\n\n\n");

        InstructionsReceiver instructionsReceiver =new InstructionsReceiverImpl();



        InstructionsSample1 instructionsSamples1=new InstructionsSample1();

        instructionsReceiver.ProcessInstructionsChunk(instructionsSamples1.getInstructions());


        InstructionsSample2 instructionsSample2=new InstructionsSample2();

        instructionsReceiver.ProcessInstructionsChunk(instructionsSample2.getInstructions());

        Set<Instruction> allInstructions=instructionsReceiver.getAllAccumulatedInstructions();

        //instructionsSamples1.display();
        //System.out.println(allInstructions);


        StatisticsGenerator statisticsGenerator=new StatisticsGeneratorImpl();

        ReportPrinter reportPrinter = new ReportPrinterImpl(statisticsGenerator, new OutputWriterImpl());

        reportPrinter.printWholeReport(allInstructions);


    }
}

package implementation;

import abstraction.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

/**
 *  The ReportPrinterImpl class fully implements ReportPrinter interface. It provides implementation for methods
 *  whose respective tasks are:
 *
 *  1) daily reports for incoming amount in USD
 *  2) outgoing amount in USD
 *  3) entity ranking based on incoming amount
 *  4) entity ranking based on outgoing amount
 *
 *  The method printWholeReport() will print report which will include
 *  reports for each of four tasks.In this interface the four individual methods are placed in
 *  as eventually user may ask for printing any of individual reports.
 *
 *
 *  Note for methods which display rankings: If you want to see amount next to ranks. please uncomment
 *  commented parts in composeReportOnEntityRanking method.
 *
 *
 *  @author Todian Mishtaku
 *
 *
 */
public class ReportPrinterImpl implements ReportPrinter {


    private StatisticsGenerator statisticsGenerator;
    private OutputWriter outputWriter;

    public ReportPrinterImpl(StatisticsGenerator statisticsGenerator, OutputWriter outputWriter)
    {
        this.statisticsGenerator=statisticsGenerator;
        this.outputWriter=outputWriter;
    }

    /**
     * Prints a whole report which contains:
     * 1) report on daily incoming amount in USD
     * 2) report on daily outgoing amount in USD
     * 3) report on ranking of entities based incoming amount in USD
     * 4) report on ranking of entities based outgoing amount in USD
     *
     * @param instructions
     */
    @Override
    public void printWholeReport(Set<Instruction> instructions) {
        printAmountInUSDsettledIncomingEveryday(instructions);
        printAmountInUSDsettledOutgoingEveryday(instructions);
        printRankingOfEntitiesBasedOnOutgoingAmount(instructions);
        printRankingOfEntitiesBasedOnIncomingAmount(instructions);

    }

    /**
     * Prints report on daily incoming amount in USD.
     * @param instructions set of instructions for which report is printed.
     */
    @Override
    public void printAmountInUSDsettledIncomingEveryday(Set<Instruction> instructions) {
        Map<LocalDate,BigDecimal>  result= statisticsGenerator.findAmountInUSDsettledIncomingEveryday(instructions);
        composeReportOnAmountSettled(result, "Incoming");
    }


    /**
     * Prints report on daily outgoing amount in USD.
     * @param instructions set of instructions for which report is printed.
     */
    @Override
    public void printAmountInUSDsettledOutgoingEveryday(Set<Instruction> instructions) {
        Map<LocalDate,BigDecimal>  result= statisticsGenerator.findAmountInUSDsettledOutgoingEveryday(instructions);
        composeReportOnAmountSettled(result, "Outgoing");
    }

    /**
     * Prints report on ranking of entities based outgoing amount in USD.
     * @param instructions set of instructions for which report is printed.
     */
    @Override
    public void printRankingOfEntitiesBasedOnOutgoingAmount(Set<Instruction> instructions) {
        Map<String, EntityRank>  result= statisticsGenerator.findRankingOfEntitiesBasedOnOutgoingAmount(instructions);
        composeReportOnEntityRanking(result, "Outgoing");
    }


    /**
     * Prints report on ranking of entities based incoming amount in USD.
     * @param instructions set of instructions for which report is printed.
     */
    @Override
    public void printRankingOfEntitiesBasedOnIncomingAmount(Set<Instruction> instructions) {
        Map<String, EntityRank>  result= statisticsGenerator.findRankingOfEntitiesBasedOnIncomingAmount(instructions);
        composeReportOnEntityRanking(result, "Incoming");
    }

    private void composeReportOnAmountSettled(Map<LocalDate, BigDecimal> result, String tradeDirection) {
        StringBuilder  output=new StringBuilder();
        output.append("\n");
        output.append("/////////// ").append(tradeDirection).append(" Amount In USD settled Daily //////////\n");
        output.append("***********************************************************\n");
        output.append("DATE").append("               |         ").append("AMOUNT\n");
        output.append("***********************************************************\n");
        result.forEach((key,val)->output.append(key).append("         |         ").append(val).append("\n")) ;
        outputWriter.write(output.toString());
    }

    private void composeReportOnEntityRanking(Map<String, EntityRank> result, String tradeDirection) {
        StringBuilder  output=new StringBuilder();
        output.append("\n");
        output.append("                                             ").append(String.format("%-110s", "Entities Ranked for " + tradeDirection)).append("\n");
        output.append("****************************************************************************************************************************\n");
        output.append(String.format("%-50s", "Entity")).append("|").append(String.format("%-30s","RANK"))/*.append("|         ").append("AMOUNT")*/.append("\n");
        output.append("****************************************************************************************************************************\n");
        result.forEach((key,val)->output.append(String.format("%-50s", key)).append("|").append(String.format("%-30s", val.getRank()))/*.append("|         ").append(val.getAmount())*/.append("\n")) ;
        outputWriter.write(output.toString());
    }

    @Override
    public StatisticsGenerator getStatisticsGenerator() {
        return statisticsGenerator;
    }

    @Override
    public OutputWriter getOutputWriter() {
        return outputWriter;
    }
}

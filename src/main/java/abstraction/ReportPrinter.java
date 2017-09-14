package abstraction;

import java.util.Set;



/**
 *  The ReportPrinter interface  defines the set of methods to be implemented for printing:
 *  -daily reports for incoming amount in USD
 *  -outgoing amount in USD
 *  -entity ranking based on incoming amount
 *  -entity ranking based on outgoing amount
 *
 *  The method printWholeReport() will print report which will include
 *  reports for each of four tasks.In this interface the four individual methods are placed in
 *  as eventually user may ask for printing any of individual reports.
 *
 *
 *  @author Todian Mishtaku
 *
 *
 */
public interface ReportPrinter {


    /**
     * Prints a whole report which contains:
     * 1) report on daily incoming amount in USD
     * 2) report on daily outgoing amount in USD
     * 3) report on ranking of entities based incoming amount in USD
     * 4) report on ranking of entities based outgoing amount in USD
     *
     * @param instructions
     */
    void printWholeReport(Set<Instruction> instructions);

    /**
     * Prints report on daily incoming amount in USD
     * @param instructions set of instructions for which report is printed
     */
    void printAmountInUSDsettledIncomingEveryday(Set<Instruction> instructions);

    /**
     * Prints report on daily outgoing amount in USD
     * @param instructions set of instructions for which report is printed
     */
    void printAmountInUSDsettledOutgoingEveryday(Set<Instruction> instructions);

    /**
     * Prints report on ranking of entities based incoming amount in USD
     * @param instructions set of instructions for which report is printed
     */
    void printRankingOfEntitiesBasedOnIncomingAmount(Set<Instruction> instructions);

    /**
     * Prints report on ranking of entities based outgoing amount in USD
     * @param instructions set of instructions for which report is printed
     */
    void printRankingOfEntitiesBasedOnOutgoingAmount(Set<Instruction> instructions);

    /**
     *
     * @return an object of StatisticsGenerator type which will be used in each printer method
     * for getting the required relevant data.
     */
    StatisticsGenerator getStatisticsGenerator();

    /**
     *
     * @return the output object where report will be written in
     */
    OutputWriter getOutputWriter();


}

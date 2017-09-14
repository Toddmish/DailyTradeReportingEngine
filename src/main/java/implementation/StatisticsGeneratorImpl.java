package implementation;

import abstraction.EntityRank;
import abstraction.Instruction;
import abstraction.OperationType;
import abstraction.StatisticsGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


import static java.util.stream.Collectors.*;



/**
 * The StatisticsGeneratorImpl class implements all methods of StatisticsGenerator interface:
 *  These methods generate statistics on:
 *
 *  1) daily incoming amount in USD
 *  2) daily outgoing amount in USD
 *  3) entity ranking based on incoming amount
 *  4) entity ranking based on outgoing amount
 *
 *
 * Note: Incoming/Outgoing amount are seen from entity point of view which means: 'BUY' instructions
 * is money out from entity and 'SELL' instructions means money in to the Entity.
 * Eg: If entity foo instructs the highest
 * amount for a buy instruction, then foo is rank 1 for outgoing.
 *
 *
 *
 *
 *  @author Todian Mishtaku
 *
 *
 *
 *
 *
 */
public class StatisticsGeneratorImpl implements StatisticsGenerator {


    /**
     * Generates stats on total incoming amount in USD per each date.
     * @param instructions set of instructions for which report is generated.
     * @return a map from date to  sum of all incoming amounts in USD settled on that date from all
     * entities.
     */
    @Override
    public Map<LocalDate, BigDecimal> findAmountInUSDsettledIncomingEveryday(Set<Instruction> instructions) {
        return findAmountInUSDSettledEverydayBasedOnTradeOperation(instructions, OperationType.SELL);
    }


    /**
     * Generates stats on total outgoing amount in USD per each date.
     * @param instructions set of instructions for which report is generated.
     * @return a map from date to  sum of all outgoing amounts in USD settled on that date from all
     * entities.
     */
    @Override
    public Map<LocalDate, BigDecimal> findAmountInUSDsettledOutgoingEveryday(Set<Instruction> instructions) {
        return findAmountInUSDSettledEverydayBasedOnTradeOperation(instructions, OperationType.BUY);
    }


    /**
     * Generates ranking of entities based on their incoming amount in USD.
     * @param instructions set of instructions for which ranking is generated.
     * @return a map from entity name to a tuple made of rank and sum of all incoming amount in USD settled
     * by that entity which determines that rank.
     */
    @Override
    public Map<String, EntityRank> findRankingOfEntitiesBasedOnIncomingAmount(Set<Instruction> instructions) {

        return getRankingOfEntitiesBasedOnTradeOperation(instructions, OperationType.SELL);
    }


    /**
     * Generates ranking of entities based on their outgoing amount in USD.
     * @param instructions set of instructions for which ranking is generated.
     * @return a map from entity name to a tuple made of rank and sum of all outgoing amount in USD settled
     * by that entity which determines that rank.
     */
    @Override
    public Map<String, EntityRank> findRankingOfEntitiesBasedOnOutgoingAmount(Set<Instruction> instructions) {

        return getRankingOfEntitiesBasedOnTradeOperation(instructions, OperationType.BUY);
    }


    private Map<LocalDate, BigDecimal> findAmountInUSDSettledEverydayBasedOnTradeOperation(Set<Instruction> instructions, OperationType operationType) {

        Map<LocalDate, BigDecimal>  result=instructions.stream().
                filter(inst-> inst.getOperation() == operationType).
                collect(groupingBy(Instruction::getSettlementDate,
                        mapping(inst->inst.getTradeAmount(),reducing(BigDecimal.ZERO, BigDecimal::add) ) )
                );
        /*For better presentation result is ordered by date*/
        HashMap<LocalDate, BigDecimal> sortedResults=result.entrySet().stream().sorted((x,y)->x.getKey().compareTo(y.getKey())).
                collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue , (e1, e2) -> e1, LinkedHashMap::new ));
        return sortedResults;

    }


    private HashMap<String, EntityRank> getRankingOfEntitiesBasedOnTradeOperation(Set<Instruction> instructions, OperationType operationType) {

        Map<String, BigDecimal> result= instructions.stream().
                filter(inst-> inst.getOperation() == operationType).collect
                (
                        groupingBy(inst->inst.getEntity().getName(),
                                mapping(Instruction::getTradeAmount,reducing(BigDecimal.ZERO, BigDecimal::add) )
                        )
                );

        LinkedHashMap<String, BigDecimal> sortedGroupedResults=result.entrySet().stream().sorted((x,y)->y.getValue().compareTo(x.getValue())).
                collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue , (e1, e2) -> e1, LinkedHashMap::new ));
        LinkedHashMap<String, EntityRank>  finalResult=new LinkedHashMap<String, EntityRank>();
        int[]         rank={1};
        BigDecimal[]  prevAomunt= {(BigDecimal)sortedGroupedResults.values().toArray()[0]};
        sortedGroupedResults.forEach((key,val)-> {
            if(val.compareTo(prevAomunt[0])<0) rank[0]++;
            finalResult.put(key, new EntityRankImpl(rank[0], val));
            prevAomunt[0]=val;
        } );

        return  finalResult;

    }
}

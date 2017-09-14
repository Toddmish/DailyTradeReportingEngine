package implementation;

import abstraction.EntityRank;

import java.math.BigDecimal;


/**
 * The EntityRankImpl provides implementations for EntityRank. The tuple required by the interface
 * is implemented through the EntityRankImpl class which will have two fields: amount which is sum of all
 * incoming(outgoing) amount settled in USD by entity and corresponding rank which in fact is determined
 * by the amount. Higher amount means higher rank which further means lower value of rank field. Highest rank
 * value is 1. EntityRankImpl will be needed while generating and printing statistics. Task' spec asks only ranks, but data structure
 * provided in project leaves open the eventual option of printing amount beside rank.
 *
 *
 * @author Todian Mishtaku
 *
 *
 **/
public class EntityRankImpl implements EntityRank {

    private int rank;

    private BigDecimal amount;

    public EntityRankImpl(int rank, BigDecimal amount)
    {
        this.amount=amount;
        this.rank=rank;
    }


    @Override
    public int getRank() {
        return rank;
    }

    @Override
    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public BigDecimal getAmount() { return amount; }

    @Override
    public void setAmount(BigDecimal amount) {
        this.amount =amount;
    }
}


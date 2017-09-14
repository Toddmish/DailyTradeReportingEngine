package abstraction;

/**
 * The OperationType enum represents all possible trade operation which can be requested
 * through instructions.
 *
 *
 * @author Todian Mishtaku
 *
 *
 */
public enum OperationType {

    SELL("S"),
    BUY("B");

    public String getOperationName() {
        return operationName;
    }

    String operationName;

    OperationType(String name)
    {
        this.operationName =name;
    }
}

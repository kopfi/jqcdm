package at.mostlychaotic.jqcdm.parameters.helpers;

import at.mostlychaotic.jqcdm.parameters.Operation;

/**
 * Counts the number of bytes based upon a given {@code ByteDecision} or (if no decision is given) every byte.
 *
 * @author Kopfi
 */
public class CounterOperation implements Operation {
    private int mCounter;
    private ByteDecision mDecisionStrategy;


    /**
     * Initializes the counter with 0 and without any {@code ByteDecision} strategy. This leads to a simple byte
     * counter.
     */
    public CounterOperation() {
        this(0, null);
    }


    /**
     * Initializes the counter with 0 and the given {@code ByteDecision} implementation.
     *
     * @param strategy a {@code ByteDecision} implementation, to decide if a byte should be counted
     */
    public CounterOperation(ByteDecision strategy) {
        this(0, strategy);
    }


    /**
     * Initializes the counter with the given {@code start} value and the given {@code ByteDecision} strategy.
     *
     * @param start the start value for the counter; normally this would be 0 (zero).
     * @param strategy a {@code ByteDecision} implementation, to decide if a byte should be counted
     */
    public CounterOperation(int start, ByteDecision strategy) {
        mCounter = start;
        mDecisionStrategy = strategy;
    }


    /**
     * Checks the given byte and increases the internal counter depending on the set {@code ByteDecision} strategy.
     * This method is intended to be called by a class that needs the {@code Operation} interface.
     * @param b the byte to be checked
     */
    @Override
    public void doOperation(byte b) {
        if (mDecisionStrategy != null) {
            if (mDecisionStrategy.decide(b)) {
                mCounter++;
            }
        } else mCounter++;  //if no decisions should be taken, count every byte
    }


    /**
     * Returns the counter value at call time.
     * @return the counter value
     */
    public int getCounter() {
        return mCounter;
    }
}
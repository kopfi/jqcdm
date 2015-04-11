package at.mostlychaotic.jqcdm.parameters.helpers;

/**
 * Provides a simple interface to implement yes/no decisions.
 *
 * @author Kopfi
 */
public interface ByteDecision {

    /**
     * Decides positive or negative based on the given data  byte.
     * @param b the date byte that should used on basis for the decision
     * @return {@code true} for a positive and {@code false} for a negative decision
     */
    public boolean decide(byte b);
}

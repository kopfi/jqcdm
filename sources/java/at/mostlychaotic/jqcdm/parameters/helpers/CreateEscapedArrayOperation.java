package at.mostlychaotic.jqcdm.parameters.helpers;

import at.mostlychaotic.jqcdm.parameters.AbstractParameter;
import at.mostlychaotic.jqcdm.parameters.Operation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Creates an byte array, where bytes with special meaning in the DM protocol are escaped. Whether a byte should be
 * escaped or not could be controlled by an {@code ByteDecision}.
 *
 * @author Kopfi
 */
public class CreateEscapedArrayOperation implements Operation {
    final private Logger mLogger = LogManager.getLogger(CreateEscapedArrayOperation.class);
    private byte[] mArray;
    private int mIndex;
    private ByteDecision mDecisionStrategy;


    public CreateEscapedArrayOperation(int size) {
        this(size, null);
    }

    public CreateEscapedArrayOperation(int size, ByteDecision strategy) {
        if (size < 0) {
            mLogger.warn("got a negative array size during initialization");
            throw new IllegalArgumentException("array size must be a positive integer");
        }

        mArray = new byte[size];
        mIndex = 0;
        mDecisionStrategy = strategy;
        if (mDecisionStrategy == null) {
            mLogger.debug("didn't get any decision strategy at initialization");
        }
    }

    @Override
    public void doOperation(byte b) {
        if (mDecisionStrategy != null) {
            if (mDecisionStrategy.decide(b)) {
                mLogger.debug("Decided positive for byte 0x{}.", Long.toHexString(b));
                add(AbstractParameter.escapeByte(b));

            } else mLogger.debug("Decided negative for byte 0x{}.", Long.toHexString(b));
        } else add(b);
    }

    /**
     * Returns a reference to the internal array.
     * @return the internal resulting array.
     */
    public byte[] getArray() {
        return mArray;
    }

    /**
     * Adds the given byte to the internal array, if enough space is left.
     *
     * @param b the byte to add
     */
    private void add(byte b) {
        if (mIndex < mArray.length) {
            mArray[mIndex] = b;
            mIndex++;
        } else mLogger.warn("Wanted to add byte 0x{} to array, but array is already full. Discarding byte!", Long.toHexString(b));
    }

    /**
     * Adds the given byte array to the internal result array, if enough space is left. This method copies the array as
     * long as space is sufficient and stops, when the internal array is full. Hence, this method might stop in the
     * middle of the copy process.
     *
     * This method is normally used to add escaped bytes, who are returned from the escape method as array of two bytes.
     *
     * @param b the byte array to adds
     */
    private void add(byte[] b) {
        for(int i = 0; i < b.length; i++) {
            add(b[i]);
        }
    }
}
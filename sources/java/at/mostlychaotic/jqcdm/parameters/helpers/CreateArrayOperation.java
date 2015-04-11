package at.mostlychaotic.jqcdm.parameters.helpers;

import at.mostlychaotic.jqcdm.parameters.Operation;

/**
 * Creates a byte array.
 *
 * @author Kopfi
 */
public class CreateArrayOperation implements Operation {
    private byte[] mResult;
    private int mIndex;

    public CreateArrayOperation(int size) {
        mResult = new byte[size];
        mIndex = 0;
    }

    @Override
    public void doOperation(byte b) {
        mResult[mIndex] = b;

        mIndex++;
    }

    public byte[] getArray() {
        return mResult;
    }
}
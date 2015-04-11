package at.mostlychaotic.jqcdm.parameters;

import at.mostlychaotic.jqcdm.QcdmMessage;
import at.mostlychaotic.jqcdm.parameters.helpers.ByteDecision;
import at.mostlychaotic.jqcdm.parameters.helpers.CounterOperation;
import at.mostlychaotic.jqcdm.parameters.helpers.CreateArrayOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implements a 32bit memory address parameter.
 * @author Kopfi
 */
public class Address32Parameter extends AbstractParameter {
    final private Logger mLogger = LogManager.getLogger(AbstractParameter.class);
    private long mAddress;
    private long mMinAddress;
    private long mMaxAddress;
    private boolean mIsLimited;

    final public static String DEFAULT_NAME = "memory address";
    final public static long DEFAULT_ADDRESS = 0L;
    final public static int RESULT_SIZE = 4;

    public Address32Parameter() {
        super(Address32Parameter.DEFAULT_NAME);
        mAddress = Address32Parameter.DEFAULT_ADDRESS;

        mIsLimited = false;
        mMinAddress = 0;
        mMaxAddress = (long) Math.pow(2.0, 32.0) - 1;
    }

    public Address32Parameter(long address) {
        this(Address32Parameter.DEFAULT_NAME, address);
    }

    public Address32Parameter(String name, long address) {
        super(Address32Parameter.DEFAULT_NAME);
        mAddress = address;

        mIsLimited = false;
        mMinAddress = 0;
        mMaxAddress = Long.MAX_VALUE;
    }

    /**
     * This method sets a new value for the address. If the address was successfully set, this method returns
     * {@code true}. If the address couldn't be set, because it is out of the allowed limits, this method returns
     * {@code false}. If the given address is negativ, an {@code IllegalArgumentException} is thrown. A given address
     * must not be negative.
     * @param address the new address as positive integer
     * @return {@code true} if the address was set correctly
     * @throws IllegalArgumentException if the given address was negative
     */
    public boolean setAddress(long address) throws IllegalArgumentException {
        if (address > 0L) {
            if (checkAdress(address)) {
                mAddress = address;
            } return false; //soft-fail
        } else {
            //hard-fail
            mLogger.error("Got a new memory address of less than zero.");
            throw new IllegalArgumentException("Got negative integer as address value. Memory addresses must always be positive integers!");
        }
    }

    public void setMaxAddress(long max) {
        if (!mIsLimited)
            mIsLimited =true;

        if (max < mMaxAddress) {
            if (max >= mMinAddress) {
                mLogger.debug("Set new allowed maximum address: {}", Long.toHexString(max));
                mMaxAddress = max;
            } else mLogger.warn("Doesn't set new minimun address because it is smaller than min address.");
        } else mLogger.warn("Doesn't set new minimum address because it is less restrictive than old maximum address.");
    }

    public void setMinAddress(long min) {
        if (!mIsLimited)
            mIsLimited =true;

        if (min > mMinAddress) {
            if (min <= mMaxAddress) {
                mLogger.debug("Set new allowed minimum address: {}", Long.toHexString(min));
                mMinAddress = min;
            } else mLogger.warn("Doesn't set new minimun address because it is greater than max address.");
        } else mLogger.warn("Doesn't set new minimum address because it is less restrictive than old minimum address.");
    }

    private int countEscapes() {
        CounterOperation op = new CounterOperation(new ByteDecision() {
            @Override
            public boolean decide(byte b) {
                return ((b == AbstractParameter.ESCAPE_BYTE) || (b == QcdmMessage.MARKER));
            }
        });

        performOperationOnAddresBytes(op);

        return op.getCounter();
    }

    //TODO check if really needed
    private byte[] getAddressBytes() {
        CreateArrayOperation op = new CreateArrayOperation(RESULT_SIZE);

        performOperationOnAddresBytes(op);
        return op.getArray();
    }

    private void performOperationOnAddresBytes(Operation op) {
        long tmpAddress = mAddress;

        for (int i = 0; i < RESULT_SIZE; i++) {
            byte result = (byte) (tmpAddress & 0xFF);

            op.doOperation(result);
            tmpAddress = tmpAddress >>> 8;
        }
    }

    @Override
    public int getSize() {
        return RESULT_SIZE + countEscapes();
    }

    //TODO do it
    @Override
    public byte[] getBytes() {
        long tmpAddress = mAddress;
        byte[] result = new byte[RESULT_SIZE];

        for (int i = 0; i < result.length; i++) {
            result[i] = (byte) (tmpAddress & 0xFF);
            tmpAddress = tmpAddress >>> 8;
        }

        return result;
    }

    private boolean checkAdress(long addr) {
        if (addr >= 0) {
            if (mIsLimited) {
                if ((addr >= mMinAddress) && (addr <= mMaxAddress)) {
                    return true;
                } else return false;
            } else return true;
        } else return false;
    }

    @Override
    public boolean isValid() {
        return checkAdress(mAddress);
    }
}

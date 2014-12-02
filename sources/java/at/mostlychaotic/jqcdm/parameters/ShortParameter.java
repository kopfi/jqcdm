package at.mostlychaotic.jqcdm.parameters;

/**
 * Created by mkopfensteiner on 02.12.14.
 */
public class ShortParameter extends AbstractParameter {
    private int mValue;
    private int mMinValue;
    private int mMaxValue;

    final public static String DEFAULT_NAME = "unsigned 2byte value";
    final public static int BYTE_COUNT = 2;

    public ShortParameter(int value) {
        super(DEFAULT_NAME);

        mMinValue = 0;
        mMaxValue = (int) Math.pow(2.0, 16.0) - 1;

        if (checkRange(value)) {
            this.mValue = value;
        }
    }

    @Override
    public int getSize() {
        return ShortParameter.BYTE_COUNT;
    }

    @Override
    public byte[] getBytes() {
        int tmpValue = mValue;
        byte[] result = new byte[ShortParameter.BYTE_COUNT];

        for(int i = 0; i < result.length; i++) {
            result[i] = (byte) (tmpValue & 0xFF);
            tmpValue = tmpValue >>> 8;
        }

        return result;
    }

    @Override
    public boolean isValid() {
        return checkRange(mValue);
    }

    private boolean checkRange(int value) {
        if ((value >= mMinValue) && (value <= mMaxValue)) {
            return true;
        } else return false;
    }
}

package at.mostlychaotic.jqcdm.structure.impl;

import at.mostlychaotic.jqcdm.QcdmMessage;
import at.mostlychaotic.jqcdm.structure.impl.QcdmCrc;

/**
 * This class is a very simple implementation of the {@code QcdmMessage}. It uses a {@code byte} array, that is
 * given through the constructor, as body and the default CRC16 checksum provided by {@code QcdmCrc}.
 *
 * @author Kopfi
 */
public class SimpleMessage extends QcdmMessage {
    private byte[] mBody;
    private byte[] mCrc16;

    final public static int CHECKSUM_BYTES = 2;

    /**
     * This constructor initializes a new instance, that uses the given {@code byte} array as its body data. The
     * array is going to be copied to prevent later changes. If you need to modify the body, you need to create
     * another instance or use an other, feature richer implementation of {@code QcdmMessage}.
     * Keep in mind, that (at least the Huawei E220) expects the data of possible parameters to start with the
     * least significant byte first.
     * @param body the body data to use.
     */
    public SimpleMessage(byte[] body) {
        super();

        int crc;
        mBody = body.clone();
        mCrc16 = QcdmCrc.getInstance().calculate(mBody);
    }

    /**
     * This constructor initializes a new instance, that uses the given {@code byte} command as body data. Apparently
     * this constructor can only be used for commands that do not expect any parameters.
     * @param command the body command to use.
     */
    public SimpleMessage(byte command) {
        this(new byte[]{command});
    }

    @Override
    public byte[] getBody() {
        return mBody.clone();
    }

    public int getBodySize() {
        return mBody.length;
    }

    /**
     * This method returns the bodies CRC16 checksum. The byte array will contain the two bytes in LSB order.
     * @return the checksum for the bytes of the body
     */
    public byte[] getBodyChecksum() {
        return mCrc16.clone();
    }
}

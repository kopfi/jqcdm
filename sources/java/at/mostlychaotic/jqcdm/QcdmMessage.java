package at.mostlychaotic.jqcdm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;

/**
 * This class models a Qualcomm DIAG Message. According to various sources on the internet, these messages consist
 * out of a start marker ({@literl 0x7e}), one byte as command, an arbitrary number of parameters, an crc16-ccitt
 * checksum and an end marker (again {@literal 0x7e}).
 * This abstract class uses this structure to model the fixed parts and define methods to obtain the variable ones.
 *
 * @author Kopfi
 */
public abstract class QcdmMessage {
    final private Logger mLogger = LogManager.getLogger(QcdmMessage.class);

    /**
     * This array contains the values for the Hex {@code String} created by the {@code getHexString} method. This method
     * was written by <a href="http://stackoverflow.com/users/1284661/maybewecouldstealavan">maybeWeCouldStealAVa</a>.
     */
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    /**
     * This marker byte is sent at the beginning and at the end of every command from the computer to the qualcomm
     * device.
     */
    final public static byte MARKER = 0x7e;

    /**
     * This method returns the bytes of the body. Every value should be written in LSB.
     * @return
     */
    abstract public byte[] getBody();

    /**
     * This method returns the number of bytes, that are contained within the body.
     * @return
     */
    abstract public int getBodySize();

    /**
     * This method returns the checksum that should be appended after the body in LSB notation.
     * <b>Cave:</b> If the device evaluates the checksum to be wrong, it might not respond to a command at all. This
     * happened at least with my Huawei E220.
     * @return the checksum for the bytes of the body
     */
    abstract public byte[] getBodyChecksum();

    /**
     * This method returns the message as array of {@code byte}, ready to be sent to the device.
     * @return
     */
    public byte[] getBytes() {
        byte[] checksum = getBodyChecksum();
        ByteBuffer result = ByteBuffer.allocate(2 + checksum.length + getBodySize());
        mLogger.debug("starting byte array creation for {}. It contains {} bytes.", this.toString(),
                Integer.toString(result.capacity()));

        result.put(QcdmMessage.MARKER); //beginning of the message
        result.put(getBody());
        result.put(getBodyChecksum());
        result.put(QcdmMessage.MARKER); //end of the message
        return result.array();
    }

    /**
     * This method writes the array returned by {@code getBytes()} to the given {@code OutputStream}. This method
     * is mainly for convenience. The method expects the stream to be opened and ready. The stream will <b>not</b>
     * be closed by this method.
     *
     * @param out the {@code OutputStream} that data should be written to
     * @throws IOException  if there occures a problem while writing to the {@code OutputStream}
     */
    public void writeTo(OutputStream out) throws IOException {
        byte[] message = this.getBytes();

        mLogger.debug("writing Message " + QcdmMessage.getHexString(message));
        out.write(message);
    }

    /**
     * This method creates a pretty-printed hex {@code String} from a given {@code byte} array. This method was written
     * by <a href="http://stackoverflow.com/users/1284661/maybewecouldstealavan">maybeWeCouldStealAVa</a> on this
     * <a href="http://stackoverflow.com/questions/9655181/convert-from-byte-array-to-hex-string-in-java">Stackoverflow
     * Thread: Convert from byte array to hex string in java</a>.
     * I am really sorry for beeing to lazy to make this up my own.
     * @param bytes the byte array, whos values should be printed as hex string
     * @return the hex string of the values of the given array
     */
    public static String getHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;            //to avoid that negative byte values fill the int value with fff values
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
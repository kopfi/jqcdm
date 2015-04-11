package at.mostlychaotic.jqcdm.parameters;

import at.mostlychaotic.jqcdm.structure.Parameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Implements the naming-part of the {@code Parameter} interface. This class is used as base for other
 * {@code Parameter} implementations in this package.
 *
 * @author Kopfi
 */
public abstract class AbstractParameter implements Parameter {
    final private Logger mLogger = LogManager.getLogger(AbstractParameter.class);
    private String mName;

    final public static byte ESCAPE_BYTE = 0x7D;
    final public static byte ESCAPE_MASK = 0x20;

    final public static int ESCAPED_ESC_INDEX = 0;
    final public static int ESCAPED_DATA_INDEX = 1;

    public AbstractParameter(String name) {
        mName = name;
    }

    /**
     * Returns the name of this {@code Parameter}. The name is mainly used to generate meaningful log messages and for
     * representing the parameter in front of a user.
     * @return the name of this parameter as {@code String}
     */
    @Override
    public String getName() {
        return mName;
    }

    /**
     * Sets the name of this {@code Parameter}. The name is mainly used to generate meaningful log messages and for
     * representing the parameter in front of a user.
     * @param newname a new name for this {@code Parameter}
     */
    protected void setName(String newname) {
        mLogger.debug("Paramter '{}' is renamed to '{}'", mName, newname);
        mName = newname;
    }

    /**
     * Escapes the given byte according the to the DM protocol. This method does not check if the given byte really
     * needs to be escaped. This decision should be made by the calling method.
     *
     * The escape character itself will be located at {@code AbstractParameter.ESCAPE_ESC_INDEX} and the escaped byte
     * will be located at {@code AbstractParamter.ESCAPED_DATA_INDEX}.
     * According to libqcdm the escaping is done by combining the data byte and the escape mask with an
     * <i>exclusive-or</i> operation:{@code [ ESCAPE_BYTE,  (DATA_BYTE ^ ESCAPE_MASK) ]} .
     *
     * @param b the data byte that should be escaped
     * @return an array with two elements that holds the (resulting) escaped byte sequence
     */
    public static byte[] escapeByte(byte b) {
        byte[] escapedSequence = new byte[2];

        escapedSequence[AbstractParameter.ESCAPED_ESC_INDEX] = ESCAPE_BYTE;
        escapedSequence[AbstractParameter.ESCAPED_DATA_INDEX] = (byte) (b ^ ESCAPE_MASK);

        return escapedSequence;
    }

    /**
     * Unescapes the given byte according the the DM protocol. This method doesn't check if the given byte really needs
     * unescaping. This decision (the same as with the {@code escapeByte(byte)} method) should be made by the calling
     * method. Hence this method doesn't need the signal-byte, that starts the escape sequence, to be passed.
     *
     * The unescaping is done by combining the byte and with the escape pattern with an <i>exclusive-or</i> operation.
     *
     * @param b the byte that should be unescaped
     * @return the unescaped resulting byte
     */
    public static byte unescapeByte(byte b) {
        return (byte) (b ^ ESCAPE_MASK);
    }
}
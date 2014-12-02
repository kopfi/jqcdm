package at.mostlychaotic.jqcdm.structure.impl;

import at.mostlychaotic.jqcdm.QcdmMessage;
import at.mostlychaotic.jqcdm.parameters.AbstractParameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

/**
 * A parameter is an additional bit of information, that is handed to the device within the body of a QCDM
 * command. A parameter has a name, and {@code byte} data of a fixed length.
 *
 * @author Kopfi
 */
public class SimpleParameter extends AbstractParameter {
    final private Logger mLogger = LogManager.getLogger(SimpleParameter.class);
    final private Marker mVerboseMarker = MarkerManager.getMarker("VERBOSE");
    private byte[] mValue;


    public SimpleParameter(String name, int size) {
        super(name);

        if (size <= 1) {
            mLogger.warn("Parameter '{}': The application tried to create a new instance with a size of {}",
                    name, Integer.toString(size));
            throw new IllegalArgumentException("The given size must be a positive integer.");
        }

        mValue = new byte[size];

        mLogger.debug("intialized Paramter {} with {} bytes of empty data", getName(), Integer.toString(mValue.length));
    }

    @Override
    public int getSize() {
        return mValue.length;
    }

    @Override
    public void setName(String newname) {
        super.setName(newname);
    }

    public void setValue(byte[] val) throws IllegalArgumentException {
        if (val == null) {
            mValue = new byte[mValue.length];
            mLogger.debug("Parameter {}: The application gave me a 'null' reference as new data value. " +
                    "I discarded my old data and replaced it with an byte array of the correct size.", getName());
        } else if (val.length == mValue.length) {
            for(int i = 0; i < val.length; i++) {
                mValue[i] = val[i];
                if (mValue.length < 10) {
                    mLogger.debug("Parameter{}: Set new value to (hex) '{}'.", getName(),
                            QcdmMessage.getHexString(mValue));

                } else mLogger.debug("Parameter{}: new value set!", getName());
            }
        } else {
            mLogger.warn("Parameter {}: The application tried to set a value of the wrong size, got {} byte, expected {} byte",
                    getName() ,Integer.toString(val.length), Integer.toString(mValue.length));
            throw new IllegalArgumentException("The given value array is of the wrong size.");
        }
    }

    @Override
    public byte[] getBytes() {
        return mValue.clone();
    }

    /**
     * This method checks, if the given parameter data is valid. Because there are no restrictions on the parameter data
     * in this implementation, this method almost always returns {@code true}. It only renders to {@code false} if the
     * data array inside is {@code null}. This should never happen as the checks on the setter method should
     * prevent that.
     *
     * @return always {@codt true} in this implementation
     */
    @Override
    public boolean isValid() {
        if (mValue != null) {
            return true;
        } else {
            mLogger.warn("Paramter {} got checked and is not valid. This shouldn't happen!", getName());
            return false;
        }
    }
}
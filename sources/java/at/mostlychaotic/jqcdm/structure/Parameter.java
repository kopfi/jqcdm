package at.mostlychaotic.jqcdm.structure;

/**
 * This interface defines the basic methods of a parameter, to be used by the {@code  MessageBody}.
 *
 * @author Kopfi
 */
public interface Parameter {

    /**
     * This method returns the {@code byte} count of this parameter inside the body
     * @return the number of bytes needed for this parameter
     */
    public int getSize();

    /**
     * This method returns the name of the parameter. This should help a to identify the parameter within user
     * interactions and log messages.
     * @return
     */
    public String getName();

    /**
     * This method returns the data of this {@code Parameter} as {@code byte} array. This array should be sorted
     * correctly (least significant byte first). This method is likely called to get the data that should be written
     * to the device.
     * @return the data of this {@code Parameter}, ready to be written to the device
     */
    public byte[] getBytes();

    /**
     * This method should return whether the data for this parameter is valid or not. If there are no restrictions
     * to be checked, it should return {@code true}.
     * @return {@code true} if the data is valid and could possibly be send to the device without forcing an error
     */
    public boolean isValid();
}
package at.mostlychaotic.jqcdm.structure;

/**
 * This interface defines methods for calculating arbitrary checksums. This interface is simple and its main
 * purpose is to be able to hand an checksum-calculation method to a {@code MessageBody} or any other object that
 * might want's to use it, without the hassle of deciding what to use and to initialize everything.
 *
 * @author Kopfi
 */
public interface ChecksumCalc {

    /**
     * This method should return a name that identifies the checksum beneath different implementations.
     * @return a name
     */
    public String getName();

    /**
     * This method calculates the checksum for the given data and returns it a {@code byte} array. The bytes
     * of this array should already be sorted with least-significant byte first.
     * @param data the data, to calculate the checksum on
     * @return the checksum as byte array in LSB byte-order
     */
    public byte[] calculate(byte[] data);
}

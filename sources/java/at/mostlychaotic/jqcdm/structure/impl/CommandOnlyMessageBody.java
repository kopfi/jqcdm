package at.mostlychaotic.jqcdm.structure.impl;

import at.mostlychaotic.jqcdm.structure.DiagCommandCodes;
import at.mostlychaotic.jqcdm.structure.MessageBody;

/**
 * This {@code MessageBody} is the lightest possible form. It only wraps around the command-code itself, no parameters
 * are needed.
 *
 * @author Kopfi
 */
public class CommandOnlyMessageBody implements MessageBody {
    private DiagCommandCodes mCommand;

    final public static int ONE_BYTE_LENGTH = 1;

    public CommandOnlyMessageBody(DiagCommandCodes cmd) {
        mCommand = cmd;
    }

    @Override
    public byte[] getBytes() {
        return new byte[] { getCommandCode() };
    }

    @Override
    public byte getCommandCode() {
        return mCommand.getByte();
    }

    @Override
    public int getLength() {
        return ONE_BYTE_LENGTH;
    }

    @Override
    public SimpleParameter[] getParameters() {
        return new SimpleParameter[0];
    }


    /**
     * This method returns always {@code true} within this implementation. There are no parameters
     * to be checked and the command code is correct by itself.
     * @return {@code true} in this implementation
     */
    @Override
    public boolean isValid() {
        return true;
    }
}
package at.mostlychaotic.jqcdm.structure;

import at.mostlychaotic.jqcdm.structure.impl.SimpleParameter;

/**
 */
public interface MessageBody {

    public byte[] getBytes();

    public byte getCommandCode();

    public int getLength();

    public Parameter[] getParameters();

    public boolean isValid();

}

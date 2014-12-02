package at.mostlychaotic.jqcdm.commands;

import at.mostlychaotic.jqcdm.parameters.Address32Parameter;
import at.mostlychaotic.jqcdm.parameters.ShortParameter;
import at.mostlychaotic.jqcdm.structure.MessageBody;
import at.mostlychaotic.jqcdm.structure.Parameter;
import at.mostlychaotic.jqcdm.structure.impl.SimpleParameter;

/**
 * Created by mkopfensteiner on 02.12.14.
 */
//TODO
public class PeekDataBody implements MessageBody {
    private Address32Parameter mAddress;
    private ShortParameter mLenght;

    @Override
    public byte[] getBytes() {
        return new byte[0];
    }

    @Override
    public byte getCommandCode() {
        return 0;
    }

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public Parameter[] getParameters() {
        return new SimpleParameter[0];
    }

    @Override
    public boolean isValid() {
        return false;
    }
}

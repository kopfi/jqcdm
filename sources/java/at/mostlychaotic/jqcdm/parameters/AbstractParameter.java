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
}

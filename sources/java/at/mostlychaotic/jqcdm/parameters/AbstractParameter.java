package at.mostlychaotic.jqcdm.parameters;

import at.mostlychaotic.jqcdm.structure.Parameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Created by mkopfensteiner on 02.12.14.
 */
public abstract class AbstractParameter implements Parameter {
    final private Logger mLogger = LogManager.getLogger(AbstractParameter.class);
    private String mName;

    public AbstractParameter(String name) {
        mName = name;
    }

    @Override
    public String getName() {
        return mName;
    }

    protected  void setName(String newname) {
        mLogger.debug("Paramter '{}' is renamed to '{}'", mName, newname);
        mName = newname;
    }
}

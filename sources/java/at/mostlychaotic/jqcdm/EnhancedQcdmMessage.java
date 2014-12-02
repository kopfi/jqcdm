package at.mostlychaotic.jqcdm;

import at.mostlychaotic.jqcdm.structure.ChecksumCalc;
import at.mostlychaotic.jqcdm.structure.MessageBody;
import at.mostlychaotic.jqcdm.structure.impl.QcdmCrc;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by mkopfensteiner on 01.12.14.
 */
public abstract class EnhancedQcdmMessage extends QcdmMessage {
    final private Logger mLogger = LogManager.getLogger(EnhancedQcdmMessage.class);
    private MessageBody mBody;
    private ChecksumCalc mChecksumCalculator;


    public EnhancedQcdmMessage(MessageBody body) {
        super();

        mBody = body;
        mChecksumCalculator = QcdmCrc.getInstance();
    }


    public EnhancedQcdmMessage(MessageBody body, ChecksumCalc crcCalc) {
        super();

        mBody = body;
        mChecksumCalculator = crcCalc;
    }


    public MessageBody getStructuredBody() {
        return mBody;
    }

    void setStructuredBody(MessageBody body) {
        mBody = body;
    }

    void setChecksumCalculator(ChecksumCalc newcalc) {
        mChecksumCalculator = newcalc;
    }

    ChecksumCalc getChecksumCalculator() {
        return mChecksumCalculator;
    }
}
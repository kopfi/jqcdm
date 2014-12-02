package at.mostlychaotic.jqcdm.commandline;

import at.mostlychaotic.jqcdm.structure.impl.SimpleMessage;
import jssc.SerialPort;
import jssc.SerialPortException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by mkopfensteiner on 01.12.14.
 */
public class SendCommand {
    final private Logger mLogger = LogManager.getLogger(SendCommand.class);
    private SerialPort mPort;
    private SimpleMessage mCommand;

    public SendCommand(SerialPort port, byte[] commandbody) {
        mPort = port;

        mCommand = new SimpleMessage(commandbody);
    }

    public byte[] execute() throws SerialPortException {
        byte[] result;

        try {
            mPort.writeBytes(mCommand.getBytes());

            Thread.sleep(40);

            result = mPort.readBytes();

            return result;
        } catch (InterruptedException e) {
            mLogger.warn("got interrupted while waiting 40 milliseconds for a response. Returning without" +
                    "a result.");

            return new byte[0];
        }
    }
}
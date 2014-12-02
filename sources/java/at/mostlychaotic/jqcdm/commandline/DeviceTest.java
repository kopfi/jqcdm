package at.mostlychaotic.jqcdm.commandline;

import at.mostlychaotic.jqcdm.QcdmMessage;
import at.mostlychaotic.jqcdm.structure.impl.SimpleMessage;
import jssc.SerialPort;
import jssc.SerialPortException;

/**
 * This class implements a simple device test. It tries to switch the device to QCDM mode by sending the
 * AT$QCDMG command. Afterwards it asks for version information via the Qualcomm DIAG protocol (command 0x00).
 * If the device supports Qualcomm's DIAG protocol without any vendor specialities, this should work quite well.
 * Tested with a Huawei E220 device.
 *
 * The main method expects one commandline argument: the serial device to use.
 *
 * @author Kopfi
 */
public class DeviceTest {
    private SerialPort mPort;

    final static private String AT_QCDMG ="AT$QCDMG\r";

    public DeviceTest(SerialPort port) {
        mPort = port;
    }

    public void execute() throws SerialPortException {
        byte[] lastRead;
        byte[] dmCommand;
        System.out.println("=== Starting device test. ================================ ");
        System.out.println("port: " + mPort.getPortName());
        System.out.println("port opened: " + mPort.isOpened());

        System.out.println("\n\nlistening to port for data");
        lastRead = mPort.readBytes();
        if (lastRead != null) {
            System.out.println("------------------------------------------");
            System.out.println("READ: " + new String(lastRead));
            System.out.println("------------------------------------------");
        }

        System.out.println("switching to DM mode...");
        System.out.println("------------------------------------------");
        mPort.writeString(AT_QCDMG);
        System.out.println(AT_QCDMG);
        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            System.out.println("got interrupted, stopping execution");
            return;
        }

        lastRead = mPort.readBytes();
        if (lastRead != null) {
            System.out.println(new String(lastRead));
        }

        System.out.println("------------------------------------------");
        System.out.println("expecting to be in DM mode now");

        SimpleMessage cmdVersionInfo = new SimpleMessage(new byte[]{0});
        dmCommand = cmdVersionInfo.getBytes();

        System.out.println("writing QCDM Command: " + QcdmMessage.getHexString(dmCommand));
        mPort.writeBytes(dmCommand);
        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            System.out.println("got interrupted, stopping execution");
            return;
        }

        lastRead = mPort.readBytes();

        if (lastRead != null) {
            System.out.println("DM Response (Hex): " + QcdmMessage.getHexString(lastRead));
            System.out.println("DM Response: " + new String(lastRead));
        } else {
            System.out.println("Got no response? CRC wrong? Device to slow?");
        }

        System.out.println("Execution finished!");
    }


    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: DeviceTest <ttydevice>");
            return;
        }

        SerialPort port = new SerialPort(args[0]);

        try {
            port.openPort();
        } catch(SerialPortException e) {
            e.printStackTrace();
            System.err.println("Cannot open Serial Port! No device connected? Device not ready? Missing permission?");
            System.exit(1);
        }

        DeviceTest task = new DeviceTest(port);

        try {
            task.execute();
        } catch (SerialPortException e) {
            e.printStackTrace();
            System.err.println("problems while writing/reading from serial port. Device suddenly disconnected?");
            System.exit(1);
        } finally {
            try {
                port.closePort();
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
        }
    }
}
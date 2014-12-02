#How To Get Started

If you want to send QCDM Messages with this Framework, you have
to connect to your device and initialize it properly. This procedure might be
diffrent from device to device. It is currently not in the scope of this library
to manage these connections.


After you successfully connected to your device, you can start initializing a
`QcdmMessage` object. The easiest way to achieve this for simple commands
is by instanciating a new `SimpleMessage` object.

```
import at.mostlychaotic.jqcdm.structure.DiagCommandCodes;
import at.mostlychaotic.jqcdm.structure.impl.SimpleMessage;



SimpleMessage msg = new SimpleMessage(DiagCommandCodes.VERSION_INFO.getByte());
byte[] data = msg.getBytes();
    //this byte array can now be written to the connection/device
```

In this example, the QCDM command to retrieve a Version info is prepared for sending. Technically you can create
every neede message through the `SimpleMessage` class. It does all the encapsulation and crc calculation.
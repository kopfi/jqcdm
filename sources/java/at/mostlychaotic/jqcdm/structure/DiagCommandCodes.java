package at.mostlychaotic.jqcdm.structure;

/*
        DIAG_CMD_TAGRAPH = 25, /* Info for TA power and voice graphs
        DIAG_CMD_MARKOV = 26, /* Markov stats
        DIAG_CMD_MARKOV_RESET = 27, /* Reset Markov stats
        DIAG_CMD_DIAG_VER = 28, /* Diagnostic Monitor version
        DIAG_CMD_TIMESTAMP = 29, /* Return a timestamp
        DIAG_CMD_TA_PARM = 30, /* Set TA parameters
        DIAG_CMD_MESSAGE = 31, /* Request for msg report
        DIAG_CMD_HS_KEY = 32, /* Handset emulation -- keypress
        DIAG_CMD_HS_LOCK = 33, /* Handset emulation -- lock or unlock
        DIAG_CMD_HS_SCREEN = 34, /* Handset emulation -- display request
        DIAG_CMD_PARM_SET = 36, /* Parameter download

        DIAG_CMD_SER_RESET = 44, /* Symbol error rate counter reset
        DIAG_CMD_SER_REPORT = 45, /* Symbol error rate counter report
        DIAG_CMD_TEST = 46, /* Run a specified test
        DIAG_CMD_GET_DIPSW = 47, /* Retreive the current DIP switch setting
        DIAG_CMD_SET_DIPSW = 48, /* Write new DIP switch setting
        DIAG_CMD_VOC_PCM_LB = 49, /* Start/Stop Vocoder PCM loopback
        DIAG_CMD_VOC_PKT_LB = 50, /* Start/Stop Vocoder PKT loopback
        DIAG_CMD_ORIG = 53, /* Originate a call
        DIAG_CMD_END = 54, /* End a call

        DIAG_CMD_TMOB = 59, /* Test Mode Commands and FTM commands
        DIAG_CMD_STATE = 63, /* Current state of the phone
        DIAG_CMD_PILOT_SETS = 64, /* Return all current sets of pilots
        DIAG_CMD_SPC = 65, /* Send the Service Programming Code to unlock
        DIAG_CMD_BAD_SPC_MODE = 66, /* Invalid NV read/write because SP is locked
        DIAG_CMD_PARM_GET2 = 67, /* (obsolete)
        DIAG_CMD_SERIAL_CHG = 68, /* Serial mode change


        DIAG_CMD_FEATURE_QUERY = 81,
        DIAG_CMD_SMS_READ = 83, /* Read SMS message out of NV memory
        DIAG_CMD_SMS_WRITE = 84, /* Write SMS message into NV memory
        DIAG_CMD_SUP_FER = 85, /* Frame Error Rate info on multiple channels
        DIAG_CMD_SUP_WALSH_CODES = 86, /* Supplemental channel walsh codes
        DIAG_CMD_SET_MAX_SUP_CH = 87, /* Sets the maximum # supplemental channels
        DIAG_CMD_PARM_GET_IS95B = 88, /* Get parameters including SUPP and MUX2
        DIAG_CMD_FS_OP = 89, /* Embedded File System (EFS) operations
        DIAG_CMD_AKEY_VERIFY = 90, /* AKEY Verification
        DIAG_CMD_HS_BMP_SCREEN = 91, /* Handset Emulation -- Bitmap screen
        DIAG_CMD_CONFIG_COMM = 92, /* Configure communications
        DIAG_CMD_EXT_LOGMASK = 93, /* Extended logmask for > 32 bits
        DIAG_CMD_EVENT_REPORT = 96, /* Static Event reporting /
        DIAG_CMD_STREAMING_CONFIG = 97, /* Load balancing etc
        DIAG_CMD_PARM_RETRIEVE = 98, /* Parameter retrieval
        DIAG_CMD_STATUS_SNAPSHOT = 99, /* Status snapshot
        DIAG_CMD_RPC = 100, /* Used for RPC
        DIAG_CMD_GET_PROPERTY = 101,
        DIAG_CMD_PUT_PROPERTY = 102,
        DIAG_CMD_GET_GUID = 103, /* GUID requests
        DIAG_CMD_USER_CMD = 104, /* User callbacks
        DIAG_CMD_GET_PERM_PROPERTY = 105,
        DIAG_CMD_PUT_PERM_PROPERTY = 106,
        DIAG_CMD_PERM_USER_CMD = 107, /* Permanent user callbacks
        DIAG_CMD_GPS_SESS_CTRL = 108, /* GPS session control
        DIAG_CMD_GPS_GRID = 109, /* GPS search grid
        DIAG_CMD_GPS_STATISTICS = 110,
        DIAG_CMD_TUNNEL = 111, /* Tunneling command code
        DIAG_CMD_RAM_RW = 112, /* Calibration RAM control using DM
        DIAG_CMD_CPU_RW = 113, /* Calibration CPU control using DM
        DIAG_CMD_SET_FTM_TEST_MODE = 114, /* Field (or Factory?) Test Mode
        DIAG_CMD_LOG_CONFIG = 115, /* New logging config command
        DIAG_CMD_EXT_BUILD_ID = 124,
        DIAG_CMD_EXT_MESSAGE_CONFIG= 125,
        DIAG_CMD_EVENT_GET_MASK = 129,
        DIAG_CMD_EVENT_SET_MASK = 130
 */

/**
 * Created by mkopfensteiner on 01.12.14.
 */
public enum DiagCommandCodes {
    /**
     * This command return a {@code byte} sequence, that seems to represent the units software version.
     * This command doesn't expect any parameters.
     */
    VERSION_INFO((byte) 0),

    /**
     * This command returns the serial number of the device. This command doesn't expect any parameters.
     */
    ESN((byte) 1),

    /**
     * This command reads a sqequence of bytes out of the interal memory.
     * On Huawei E220 it expects a start-address (4 byte) and a run-length (2 byte)
     */
    PEEKB((byte) 2),    //peek byte
    PEEKW((byte) 3),    //peek word
    PEEKD((byte) 4),    //peek dword
    POKEB((byte) 5),    //poke byte
    POKEW((byte) 6),    //poke word
    POKED((byte) 7),    //poke dword
    OUTP((byte) 8),     //byte output
    OUTPW((byte) 9),    //word output
    INP((byte) 10),     //byte input
    INPW((byte) 11),    //word input
    STATUS((byte) 12),  //station status
    LOGMASK((byte) 15), //set logging mask
    LOG((byte) 16),     //log packet
    NV_PEEK((byte) 17), //peek NV memory
    NV_POKE((byte) 18), // Poke NV memory
    BAD_CMD((byte) 19), //(response) Invalid command
    BAD_PARM((byte) 20),    //(response) Invalid parameter
    BAD_LEN((byte) 21), //(response) Invalid packet length
    BAD_DEV((byte) 22), //(response) Not accepted by the device
    BAD_MODE((byte) 24),    //(response) Not allowed in this mode

    NV_READ((byte) 38),     //Read NV item
    NV_WRITE((byte) 39),    //Write NV item
    CONTROL((byte) 41),     //Mode change request
    ERR_READ((byte) 41),    //Error record retreival
    ERR_CLEAR((byte) 43),   //Error record clear


    SW_VERSION((byte) 56),  //Get software version
    DLOAD((byte) 58),       //Switch to downloader

    PASSWORD((byte) 70),    //Send password to unlock secure operations
    BAD_SEC_MODE((byte) 71),    //(response) Operation not allowed in this security state
    PRL_WRITE((byte) 72),   //Write PRL
    PRL_READ((byte) 73),    //Read PRL
    SUBSYS((byte) 75);  //Subsystem commands


    private byte mCodeByte;

    DiagCommandCodes(final byte code) {
        mCodeByte = code;
    }

    public byte getByte() {
        return mCodeByte;
    }
}
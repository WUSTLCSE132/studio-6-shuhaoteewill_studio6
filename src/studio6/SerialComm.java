package studio6;

import java.io.IOException;

import jssc.*;

public class SerialComm {

	SerialPort port;

	private boolean debug;  // Indicator of "debugging mode"
	
	// This function can be called to enable or disable "debugging mode"
	void setDebug(boolean mode) {
		debug = mode;
	}	
	

	// Constructor for the SerialComm class
	public SerialComm(String name) throws SerialPortException {
		port = new SerialPort(name);		
		port.openPort();
		port.setParams(SerialPort.BAUDRATE_9600,
			SerialPort.DATABITS_8,
			SerialPort.STOPBITS_1,
			SerialPort.PARITY_NONE);
		
		debug = false; // Default is to NOT be in debug mode
	}
		
	// TODO: Add writeByte() method from Studio 5
	public void writeByte(char ch) throws IOException, SerialPortException {
		port.writeByte((byte) ch);
	if(debug) {
		String hex = String.format("%04x", (int) ch);
		System.out.println("<0x"+hex+">");
	}
}
	// TODO: Add available() method
	public boolean available() throws SerialPortException {
		int count = port.getInputBufferBytesCount();
		if(count>0) {
			return true;
		}else if (count == 0) {
			return false;
			}
		return debug;
		}
	
	// TODO: Add readByte() method	
	public byte readByte() throws SerialPortException {
		byte[] byteArray;
		byteArray = new byte[1];
		byteArray = port.readBytes(1);
		return byteArray[0];
		
	}
}
	// TODO: Add a main() method


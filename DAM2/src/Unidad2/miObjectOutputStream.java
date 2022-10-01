package Unidad2;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class miObjectOutputStream extends ObjectOutputStream{

	public miObjectOutputStream(OutputStream arg0) throws IOException {
		super(arg0);
	}
	protected void writeStreamHeader() throws IOException{}

}

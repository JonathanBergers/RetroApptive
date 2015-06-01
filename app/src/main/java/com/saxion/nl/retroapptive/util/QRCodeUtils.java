package com.saxion.nl.retroapptive.util;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Hashtable;

/**
 * 
 * @author Thomas
 *
 */
public class QRCodeUtils {

	private static final int DEFAULT_WIDTH = 512;
	private static final int DEFAULT_HEIGHT = 512;

	public static void main(String[] args) {
		try {
			final Bitmap qrCodeImage = generateQRCode("testmessage123", 512, 512);
			//ImageIO.write(qrCodeImage, "PNG", new File("qrcode.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generates a QR code image from given message with default dimensions
	 * 
	 * @param message
	 *            message to store in the QR code
	 * @return returns a Bitmap of the generated QR code, or null if an
	 *         error occured during the QR code generation
	 * @throws IOException
	 */
	public static Bitmap generateQRCode(final String message) throws IOException {
		return generateQRCode(message, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * Generates a QR code image from given message
	 * 
	 * @param message
	 *            message to store in the QR code
	 * @param width
	 *            width in pixels of the generated QR code
	 * @param height
	 *            height in pixels of the generated QR code
	 * @return returns a Bitmap of the generated QR code, or null if an
	 *         error occured during the QR code generation
	 * @throws IOException
	 */
	public static Bitmap generateQRCode(final String message, final int width, final int height) throws IOException {
		final Charset charset = Charset.forName("UTF-8");
		final CharsetEncoder encoder = charset.newEncoder();
		byte[] b = null;
		try {
			// Convert a string to UTF-8 bytes in a ByteBuffer
			final CharBuffer charBuffer = CharBuffer.wrap(message);
			final ByteBuffer bbuf = encoder.encode(charBuffer);
			b = bbuf.array();
		} catch (CharacterCodingException e) {
			e.printStackTrace();
			// should never happen, all java implementations support UTF-8
			// encoding
			return null;
		}

		String data;
		try {
			data = new String(b, "UTF-8");
			// get a byte matrix for the data
			BitMatrix matrix = null;
			final com.google.zxing.Writer writer = new MultiFormatWriter();
			try {
				final Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>(2);
				hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
				matrix = writer.encode(data, com.google.zxing.BarcodeFormat.QR_CODE, width, height, hints);
			} catch (com.google.zxing.WriterException e) {
				e.printStackTrace();
				return null;
			}
			final Bitmap qrCodeBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
			for (int x = 0; x < width; x++){
				for (int y = 0; y < height; y++){
					qrCodeBitmap.setPixel(x, y, matrix.get(x,y) ? Color.BLACK : Color.WHITE);
				}
			}
			return qrCodeBitmap;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			// should never happen, all java implementations support UTF-8
			// encoding
			return null;
		}
	}
}
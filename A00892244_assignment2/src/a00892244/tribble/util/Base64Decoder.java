//==============================================================================
// tribble/util/Base64Decoder.java
//==============================================================================

package a00892244.tribble.util;

// System imports

import java.lang.IllegalArgumentException;
import java.lang.String;

import java.text.ParseException;

// Local imports

// (None)

/*******************************************************************************
 * Base-64 decoding methods.
 * 
 * <p>
 * Base-64 encoding (a.k.a. radix-64 encoding) is a form of encoding binary data
 * in 6-bit units. (6 bits provides 64 distinct binary values, hence the moniker
 * base-<i>64</i> or radix-<i>64</i>.) Groups of three 8-bit octets (bytes),
 * totalling 24 bits, can be converted into four 6-bit units. Each 6-bit unit
 * can in turn be represented by one of 64 unique printable ASCII (8-bit)
 * characters.
 * 
 * <p>
 * Converting straight binary data (as a sequence of 8-bit octets) into a
 * radix-64 representation is thus simply the the process of packing groups of
 * three 8-bit octets into four 6-bit units, and then substituting each 6-bit
 * unit with its corresponding ASCII character code.
 * 
 * <p>
 * Converting radix-64 encoded data (as a sequence of 8-bit characters) back
 * into straight binary data is the reverse operation, substituting each
 * radix-64 ASCII character code with its corresponding 6-bit unit, then
 * unpacking groups of four 6-bit units into three 8-bit octets.
 * 
 * <p>
 * A 65th special ASCII character is used to indicate trailing padding in
 * radix-64 encoded data.
 * 
 * <p>
 * This class can be used as a simple replacement for the
 * {@link "sun.misc.BASE64Decoder"} class.
 * 
 * <!-- ---------------------------------------------------------------------
 * -->
 * 
 * @version $Revision: 1.4 $ $Date: 2005/04/16 16:05:55 $
 * @since 2003-02-26
 * @author David R. Tribble (<a
 *         href="mailto:david@tribble.com">david@tribble.com</a>). <br>
 * 
 *         Copyright �2003-2005 by David R. Tribble, all rights reserved. <br>
 *         Permission is granted to freely use and distribute this source code
 *         provided that the original copyright and authorship notices remain
 *         intact.
 * 
 * @see Base64Encoder
 * @see "sun.misc.BASE64Decoder"
 */

public class Base64Decoder {
	// Identification

	/** Revision information. */
	static final String REV = "@(#)tribble/util/Base64Decoder.java $Revision: 1.4 $ $Date: 2005/04/16 16:05:55 $\n";

	/** Class revision number. */
	public static final int SERIES = 104;

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// - -
	// Public static methods

	/***************************************************************************
	 * Decode base-64 characters into binary data.
	 * 
	 * <!-- NOT IMPLEMENTED YET
	 * <p>
	 * Whitespace characters (spaces, tabs, newlines) are ignored if they occur
	 * in <tt>chars</tt>. !-->
	 * 
	 * <!-- -----------------------------------------------------------------
	 * -->
	 * 
	 * @param chars
	 *            (const) Printable ASCII string containing binary data encoded
	 *            as radix-64 characters.
	 * 
	 * @return Binary data, as an array of bytes (8-bit octets). This array will
	 *         contain <tt>(chars.length()-<i>p</i>)*3/4</tt> bytes, where
	 *         <tt><i>p</i></tt> is 0, 1, or 2, such that the total input length
	 *         is a multiple of 4.
	 * 
	 * @throws ParseException
	 *             Thrown if <tt>chars</tt> is malformed or contains an invalid
	 *             character code.
	 * 
	 * @see #decodeString(String, byte[], int) decodeString()
	 * @see Base64Encoder#encodeAsString(byte[]) Base64Encoder.encodeAsString()
	 * 
	 * @since 1.1, 2003-02-26
	 */

	public static byte[] decodeString(/* const */String chars)
			throws ParseException {
		byte[] dec;
		int len;
		int pad;
		int olen;

		// Find the number of padding chars at the end
		len = chars.length();
		pad = 0;
		if (chars.charAt(len - 1) == '=')
			pad++;
		if (len >= 2 && chars.charAt(len - 2) == '=')
			pad++;

		// Initial size estimate of the decoded 8-bit data
		olen = len * 3 / 4;
		dec = new byte[olen - pad];

		// Decode the radix-64 character string
		decodeString(chars, dec, 0);
		return (dec);
	}

	/***************************************************************************
	 * Decode base-64 characters into binary data.
	 * 
	 * <p>
	 * See {@link #decodeBytes(byte[], int, int, byte[], int) decodeBytes()} for
	 * more details.
	 * 
	 * <!-- -----------------------------------------------------------------
	 * -->
	 * 
	 * @param chars
	 *            (const) Printable ASCII string containing binary data encoded
	 *            as radix-64 characters. <!-- NOT IMPLEMENTED YET Whitespace
	 *            characters (spaces, tabs, newlines) and control characters are
	 *            ignored. !-->
	 * 
	 * @param dec
	 *            Decoded byte (8-bit octet) array, which is filled with the
	 *            decoded data.
	 * 
	 * @param off
	 *            Index of the first byte (8-bit octet) within <tt>dec</tt> to
	 *            write to.
	 * 
	 * @return The number of bytes (8-bit octets) actually written into
	 *         <tt>dec</tt>. This will be <tt>(len+<i>p</i>)*3/4</tt> bytes,
	 *         where <tt><i>p</i></tt> is 0, 1, or 2, such that the total input
	 *         length is a multiple of 4.
	 * 
	 * @throws ParseException
	 *             Thrown if <tt>chars</tt> is malformed or contains an invalid
	 *             character code.
	 * 
	 * @see #decodeString(String) decodeString()
	 * @see Base64Encoder#encodeAsString(byte[], int, int)
	 *      Base64Encoder.encodeAsString()
	 * 
	 * @since 1.1, 2003-02-26
	 */

	public static int decodeString(/* const */String chars, byte[] dec, int off)
			throws ParseException {
		int len;
		int i;
		int o;

		// Skip padding characters at the end
		len = chars.length();
		len += off - 1;
		while (len >= 0 && chars.charAt(len) == '=')
			len--;
		len++;

		// / Note: Some day this should ignore whitespace and noise chars

		// Convert the base64 encoded chars into bytes, a chunk at a time
		i = off;
		o = off;

		while (i < len) {
			int s0;
			int s1;
			int s2 = 'A';
			int s3 = 'A';
			int d;

			// Convert a single 24-bit chunk (4 sextets into 3 octets)
			s0 = chars.charAt(i + 0);
			s1 = chars.charAt(i + 1);
			if (i + 2 < len) {
				s2 = chars.charAt(i + 2);
				if (i + 3 < len)
					s3 = chars.charAt(i + 3);
			}

			d = (fromBase64(s0 & 0xFF) << 18) | (fromBase64(s1 & 0xFF) << 12)
					| (fromBase64(s2 & 0xFF) << 6) | (fromBase64(s3 & 0xFF));

			// Write the decoded binary octets into the output array
			dec[o++] = (byte) ((d >> 16) & 0xFF);
			if (i + 2 < len)
				dec[o++] = (byte) ((d >> 8) & 0xFF);
			if (i + 3 < len)
				dec[o++] = (byte) ((d) & 0xFF);

			i += 4;
		}

		// Done
		return (o - off);
	}

	/***************************************************************************
	 * Decode base-64 character bytes into binary data.
	 * 
	 * <p>
	 * See {@link #decodeBytes(byte[], int, int, byte[], int) decodeBytes()} for
	 * more details.
	 * 
	 * <!-- -----------------------------------------------------------------
	 * -->
	 * 
	 * @param chars
	 *            (const) An array of bytes containing printable ASCII
	 *            characters, representating binary data encoded as radix-64
	 *            characters. <!-- NOT IMPLEMENTED YET Whitespace characters
	 *            (spaces, tabs, newlines) and control characters are ignored.
	 *            !-->
	 * 
	 * @return Binary data, as an array of bytes (8-bit octets). This array will
	 *         contain <tt>(chars.length+<i>p</i>)*3/4</tt> bytes, where
	 *         <tt><i>p</i></tt> is 0, 1, or 2, such that the total input length
	 *         is a multiple of 4.
	 * 
	 * @throws ParseException
	 *             Thrown if <tt>chars</tt> is malformed or contains an invalid
	 *             character code.
	 * 
	 * @see #decodeBytes(byte[], int, int) decodeBytes()
	 * @see Base64Encoder#encodeAsBytes(byte[], int, int)
	 *      Base64Encoder.encodeAsBytes()
	 * 
	 * @since 1.4, 2005-04-16
	 */

	public static byte[] decodeBytes(/* const */byte[] chars)
			throws ParseException {
		// Decode the radix-64 characters into binary data
		return (decodeBytes(chars, 0, chars.length));
	}

	/***************************************************************************
	 * Decode base-64 character bytes into binary data.
	 * 
	 * <p>
	 * See {@link #decodeBytes(byte[], int, int, byte[], int) decodeBytes()} for
	 * more details.
	 * 
	 * <!-- -----------------------------------------------------------------
	 * -->
	 * 
	 * @param chars
	 *            (const) An array of bytes containing printable ASCII
	 *            characters, representating binary data encoded as radix-64
	 *            characters. <!-- NOT IMPLEMENTED YET Whitespace characters
	 *            (spaces, tabs, newlines) and control characters are ignored.
	 *            !-->
	 * 
	 * @param off
	 *            Index of the first byte (8-bit octet) within <tt>chars</tt> to
	 *            decode.
	 * 
	 * @param len
	 *            Number of bytes (8-bit octets) to decode from <tt>chars</tt>.
	 * 
	 * @return Binary data, as an array of bytes (8-bit octets). This array will
	 *         contain <tt>(len+<i>p</i>)*3/4</tt> bytes, where
	 *         <tt><i>p</i></tt> is 0, 1, or 2, such that the total input length
	 *         is a multiple of 4.
	 * 
	 * @throws ParseException
	 *             Thrown if <tt>chars</tt> is malformed or contains an invalid
	 *             character code.
	 * 
	 * @see #decodeBytes(byte[], int, int, byte[], int) decodeBytes()
	 * @see Base64Encoder#encodeAsBytes(byte[], int, int)
	 *      Base64Encoder.encodeAsBytes()
	 * 
	 * @since 1.1, 2005-04-01
	 */

	public static byte[] decodeBytes(/* const */byte[] chars, int off, int len)
			throws ParseException {
		int olen;
		int pad;
		byte[] dec;

		// Find the number of padding chars at the end
		len = chars.length;
		pad = 0;
		if (chars[len - 1] == '=')
			pad++;
		if (len >= 2 && chars[len - 2] == '=')
			pad++;

		// / Note: Some day this should handle whitespace and ignored chars

		// Allocate an output byte array
		olen = len / 4 * 3 - pad;
		dec = new byte[olen];

		// Decode the radix-64 characters into binary data
		decodeBytes(chars, off, len - pad, dec, 0);
		return (dec);
	}

	/***************************************************************************
	 * Decode base-64 character bytes into binary data.
	 * 
	 * <pre>
	 *    +-----------+-----------+-----------+-----------+
	 *    |   sextet  |   sextet  |   sextet  |   sextet  |
	 *    |5 4 3 2 1 0|5 4 3 2 1 0|5 4 3 2 1 0|5 4 3 2 1 0|  input sextets
	 *    :- - - - - -:- -:- - - -:- - - -:- -:- - - - - -:
	 *    |7 6 5 4 3 2 1 0|7 6 5 4 3 2 1 0|7 6 5 4 3 2 1 0|  output octets
	 *    |     octet     |     octet     |     octet     |
	 *    +---------------+---------------+---------------+
	 * 
	 *    +-----------+-----------+-----------+-----------+
	 *    |   sextet  |   sextet  |   sextet  |  padding  |
	 *    |5 4 3 2 1 0|5 4 3 2 1 0|5 4 3 2 1 0|    '='    |  input sextets
	 *    :- - - - - -:- -:- - - -:- - - -:- -:- - - - - -:
	 *    |7 6 5 4 3 2 1 0|7 6 5 4 3 2 1 0|x x            :  output octets
	 *    |     octet     |     octet     |   discarded   :
	 *    +---------------+---------------+ - - - - - - - +
	 * 
	 *    +-----------+-----------+-----------+-----------+
	 *    |   sextet  |   sextet  |  padding  |  padding  |
	 *    |5 4 3 2 1 0|5 4 3 2 1 0|    '='    |    '='    |  input sextets
	 *    :- - - - - -:- -:- - - -:- - - -:- -:- - - - - -:
	 *    |7 6 5 4 3 2 1 0|x x x x        :               :  output octets
	 *    |     octet     |   discarded   :   discarded   :
	 *    +---------------+ - - - - - - - + - - - - - - - +
	 * </pre>
	 * 
	 * <p>
	 * For example, the following table list some sextet sequences and their
	 * resulting octet decodings:
	 * 
	 * <pre>
	 *    04,11,04,11              => 11,11,11
	 *    04,12,08,33              => 11,22,33
	 *    3F,3F,3F,3F              => FF,FF,FF
	 *    04,11,04, =              => 11,11
	 *    04,11,07, =              => 11,11
	 *    3F,3F,3C, =              => FF,FF
	 *    04,10, =, =              => 11
	 *    04,1F, =, =              => 11
	 *    3F,30, =, =              => FF
	 *    3F,30,03,3F,00,0F,3C,00  => FF,00,FF,00,FF,00
	 * </pre>
	 * 
	 * <!-- -----------------------------------------------------------------
	 * -->
	 * 
	 * @param chars
	 *            (const) An array of bytes containing printable ASCII
	 *            characters, representating binary data encoded as radix-64
	 *            characters. <!-- NOT IMPLEMENTED YET Whitespace characters
	 *            (spaces, tabs, newlines) and control characters are ignored.
	 *            !-->
	 * 
	 * @param off
	 *            Index of the first byte (8-bit octet) within <tt>chars</tt> to
	 *            decode.
	 * 
	 * @param len
	 *            Number of bytes (8-bit octets) to decode from <tt>chars</tt>.
	 * 
	 * @param dec
	 *            Decoded byte (8-bit octet) array, which is filled with the
	 *            decoded data.
	 * 
	 * @param decOff
	 *            Index of the first byte (8-bit octet) within <tt>dec</tt> to
	 *            write to.
	 * 
	 * @return The number of bytes (8-bit octets) actually written into
	 *         <tt>dec</tt>. This will be <tt>(len+<i>p</i>)*3/4</tt> bytes,
	 *         where <tt><i>p</i></tt> is 0, 1, or 2, such that the total input
	 *         length is a multiple of 4.
	 * 
	 * @throws ParseException
	 *             Thrown if <tt>chars</tt> is malformed or contains an invalid
	 *             character code.
	 * 
	 * @see Base64Encoder#encodeAsBytes(byte[], int, int)
	 *      Base64Encoder.encodeAsBytes()
	 * 
	 * @since 1.1, 2003-02-26
	 */

	public static int decodeBytes(/* const */byte[] chars, int off, int len,
			byte[] dec, int decOff) throws ParseException {
		int i;
		int o;

		// Skip padding characters at the end
		len += off - 1;
		while (len >= 0 && chars[len] == '=')
			len--;
		len++;

		// / Note: Some day this should ignore whitespace and noise chars

		// Convert the base64 encoded chars into bytes, a chunk at a time
		i = off;
		o = decOff;

		while (i < len) {
			int s0;
			int s1;
			int s2 = 'A';
			int s3 = 'A';
			int d;

			// Convert a single 24-bit chunk (4 sextets into 3 octets)
			s0 = chars[i + 0];
			s1 = chars[i + 1];
			if (i + 2 < len) {
				s2 = chars[i + 2];
				if (i + 3 < len)
					s3 = chars[i + 3];
			}

			d = (fromBase64(s0 & 0xFF) << 18) | (fromBase64(s1 & 0xFF) << 12)
					| (fromBase64(s2 & 0xFF) << 6) | (fromBase64(s3 & 0xFF));

			// Write the decoded binary octets into the output array
			dec[o++] = (byte) ((d >> 16) & 0xFF);
			if (i + 2 < len)
				dec[o++] = (byte) ((d >> 8) & 0xFF);
			if (i + 3 < len)
				dec[o++] = (byte) ((d) & 0xFF);

			i += 4;
		}

		// Done
		return (o - decOff);
	}

	/***************************************************************************
	 * Convert a radix-64 printable ASCII character code into its corresponding
	 * 6-bit binary value.
	 * 
	 * <!-- -----------------------------------------------------------------
	 * -->
	 * 
	 * @param ch
	 *            A printable radix-64 ASCII character code.
	 * 
	 * @return A 6-bit binary value in the range [0,63]; or the value 64,
	 *         indicating the special trailing padding code; or -1, indicating
	 *         an invalid character.
	 * 
	 * @see Base64Encoder#toBase64 Base64Encoder.toBase64()
	 * 
	 * @since 1.1, 2003-02-26
	 */

	public static int fromBase64(int ch) {
		// This averages 2.45 comparisons per call
		if (ch >= 'a') {
			if (ch <= 'z')
				return (ch - 'a' + 26);
			return (-1);
		}
		if (ch >= 'A') {
			if (ch <= 'Z')
				return (ch - 'A' + 0);
			return (-1);
		}
		if (ch >= '0') {
			if (ch <= '9')
				return (ch - '0' + 52);
			if (ch == '=')
				return (64);
			return (-1);
		}
		if (ch == '+')
			return (62);
		if (ch == '/')
			return (63);
		return (-1);
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// - -
	// Variables

	// (None)

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// - -
	// Public constructors

	/***************************************************************************
	 * Default constructor.
	 * 
	 * <p>
	 * This constructor is provided for compatibility with the
	 * {@link "sun.misc.BASE64Decoder"} class, which allows this class to be used
	 * as a simple replacement for it.
	 * 
	 * @since 1.2, 2005-04-10
	 */

	public Base64Decoder() {
		// Do nothing
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// - -
	// Public methods

	/***************************************************************************
	 * Decode base-64 characters into binary data.
	 * 
	 * <p>
	 * This method is provided for compatibility, producing the same result as
	 * the <tt>decodeBuffer()</tt> method of the {@link "sun.misc.BASE64Decoder"}
	 * class.
	 * 
	 * <!-- NOT IMPLEMENTED YET
	 * <p>
	 * Whitespace characters (spaces, tabs, newlines) are ignored if they occur
	 * in <tt>chars</tt>. !-->
	 * 
	 * <!-- -----------------------------------------------------------------
	 * -->
	 * 
	 * @param chars
	 *            (const) Printable ASCII string containing binary data encoded
	 *            as radix-64 characters.
	 * 
	 * @return Binary data, as an array of bytes (8-bit octets). This array will
	 *         contain <tt>(chars.length()-<i>p</i>)*3/4</tt> bytes, where
	 *         <tt><i>p</i></tt> is 0, 1, or 2, such that the total input length
	 *         is a multiple of 4.
	 * 
	 * @throws IllegalArgumentException
	 *             (unchecked) Thrown if <tt>chars</tt> is malformed or contains
	 *             an invalid character code.
	 * 
	 * @see #decodeString(String) decodeString()
	 * @see Base64Encoder#encode(byte[]) Base64Encoder.encode()
	 * 
	 * @since 1.2, 2005-04-10
	 */

	public byte[] decodeBuffer(/* const */String chars) {
		try {
			// Decode the string of base-64 characters into binary data
			return (decodeString(chars));
		} catch (ParseException ex) {
			// Ignore, convert into a IllegalArgumentException
			throw new IllegalArgumentException(ex.getMessage());
		}
	}
}

// End Base64Decoder.java

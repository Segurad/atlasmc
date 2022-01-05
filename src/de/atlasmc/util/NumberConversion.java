package de.atlasmc.util;

/**
 * Collection of some number related Methods useful or not
 */
public final class NumberConversion {

	private NumberConversion() {}
	
	/**
	 * 
	 * @param value
	 * @return value or 0
	 */
	public static int toInt(Object value) {
		return toInt(value, 0);
	}
	
	/**
	 * 
	 * @param value
	 * @return value or 0
	 */
	public static double toDouble(Object value) {
		return toDouble(value, 0);
	}
	
	/**
	 * 
	 * @param value
	 * @return value or 0
	 */
	public static byte toByte(Object value) {
		return toByte(value, 0);
	}
	
	/**
	 * 
	 * @param value
	 * @return value or 0
	 */
	public static long toLong(Object value) {
		return toLong(value, 0);
	}
	
	/**
	 * 
	 * @param value
	 * @return value or 0
	 */
	public static short toShort(Object value) {
		return toShort(value, 0);
	}
	
	/**
	 * 
	 * @param value
	 * @return value or 0
	 */
	public static float toFloat(Object value) {
		return toFloat(value, 0);
	}
	
	/**
	 * 
	 * @param value
	 * @param alt number returned when not a number
	 * @return value or alt
	 */
	public static int toInt(Object value, int alt) {
		if (value instanceof Number) {
			return ((Number) value).intValue();
		}
		try {
			return Integer.parseInt(value.toString());
		} catch(NumberFormatException e) {} catch (NullPointerException e) {}
		return alt;
	}
	
	/**
	 * 
	 * @param value
	 * @param alt number returned when not a number
	 * @return value or alt
	 */
	public static double toDouble(Object value, double alt) {
		if (value instanceof Number) {
			return ((Number) value).doubleValue();
		}
		try {
			return Double.parseDouble(value.toString());
		} catch(NumberFormatException e) {} catch (NullPointerException e) {}
		return alt;
	}
	
	/**
	 * 
	 * @param value
	 * @param alt number returned when not a number
	 * @return value or alt
	 */
	public static byte toByte(Object value, int alt) {
		if (value instanceof Number) {
			return ((Number) value).byteValue();
		}
		try {
			return Byte.parseByte(value.toString());
		} catch (NumberFormatException e) {} catch (NullPointerException e) {}
		return (byte) alt;
	}
	
	/**
	 * 
	 * @param value
	 * @param alt number returned when not a number
	 * @return value or alt
	 */
	public static long toLong(Object value, long alt) {
		if (value instanceof Number) {
			return ((Number) value).longValue();
		}
		try {
			return Byte.parseByte(value.toString());
		} catch (NumberFormatException e) {} catch (NullPointerException e) {}
		return alt;
	}
	
	/**
	 * 
	 * @param value
	 * @param alt number returned when not a number
	 * @return value or alt
	 */
	public static short toShort(Object value, int alt) {
		if (value instanceof Number) {
			return ((Number) value).shortValue();
		}
		try {
			Short.parseShort(value.toString());
		} catch (NumberFormatException e) {} catch (NullPointerException e) {};
		return (short) alt;
	}
	
	/**
	 * 
	 * @param value
	 * @param alt number returned when not a number
	 * @return value or alt
	 */
	public static float toFloat(Object value, float alt) {
		if (value instanceof Number) {
			return ((Number) value).floatValue();
		}
		try {
			return Float.parseFloat(value.toString());
		} catch (NumberFormatException e) {} catch (NullPointerException e) {}
		return alt;
	}
	
	/**
	 * 
	 * @param value
	 * @param alt number returned when not a number
	 * @return value or alt
	 */
	public static boolean isNumber(Object value) {
		if (value == null) return false;
		if (value instanceof Number) return true;
		try {
			Double.parseDouble(value.toString());
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}

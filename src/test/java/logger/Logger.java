package logger;

import org.apache.logging.log4j.LogManager;

/**
 * Common Test Logger
 */
public class Logger {

	private static final String DEFAULT_LOGGER_NAME = "ExampleLogger";
	final static String PREFIX_OPERATION = "OPERATION";
	
	/** Logger */
	public static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(DEFAULT_LOGGER_NAME);

	public static void info(String msg) {
		LOGGER.info(msg);
	}

	public static void info(String msg, Throwable error) {
		LOGGER.info(msg, error);
	}

	public static void info(Object message) {
		LOGGER.info(message);
	}

	public static void operation(String msg) {
		LOGGER.info(buildOperationMessage(msg));
	}

	public static void operation(String msg, Throwable error) {
		LOGGER.info(buildOperationMessage(msg), error);
	}

	public static void debug(String msg) {
		LOGGER.debug(msg);
	}

	public static void debug(Object message) {
		LOGGER.debug(message);
	}

	public static void debug(String msg, Throwable error) {
		LOGGER.debug(msg, error);
	}

	public static void warn(String msg) {
		LOGGER.warn(msg);
	}

	public static void warn(String msg, Throwable error) {
		LOGGER.warn(msg, error);
	}

	public static void warn(Object message) {
		LOGGER.warn(message);
	}

	public static void fatal(String msg) {
		LOGGER.fatal(msg);
	}

	public static void fatal(String msg, Throwable error) {
		LOGGER.fatal(msg, error);
	}

	public static void fatal(Object message) {
		LOGGER.fatal(message);
	}

	public static void error(String msg) {
		LOGGER.error(msg);
	}

	public static void error(String msg, Throwable error) {
		LOGGER.error(msg, error);
	}

	public static void error(Object message) {
		LOGGER.error(message);
	}

	public static void trace(String msg) {
		LOGGER.trace(msg);
	}

	public static void trace(String msg, Throwable error) {
		LOGGER.trace(msg, error);
	}

	public static void trace(Object message) {
		LOGGER.trace(message);
	}

	private static String buildOperationMessage(String msg) {
		StringBuilder builder = new StringBuilder();
		builder.append(PREFIX_OPERATION);
		builder.append(" - ");
		builder.append(msg);
		return builder.toString();
	}
}

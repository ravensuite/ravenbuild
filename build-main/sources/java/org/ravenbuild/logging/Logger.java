package org.ravenbuild.logging;

import org.ravenbuild.LogLevel;

import java.io.PrintStream;

public class Logger {
	private final LogLevel logLevel;
	private final PrintStream outputStream;
	private LoggerChooser loggerChooser = new LoggerChooser(this);
	
	public Logger(final LogLevel logLevel) {
		this(logLevel, System.out);
	}
	
	Logger(final LogLevel logLevel, final PrintStream outputStream) {
		this.logLevel = logLevel;
		this.outputStream = outputStream;
	}
	
	public void log(final LogLevel logLevel, final String prefix, final String message) {
		if(logLevel.ordinal() <= this.logLevel.ordinal()) {
			printFormattedLogLevel(logLevel);
			printFormattedPrefix(prefix);
			outputStream.println(message);
		}
	}
	
	public LoggerChooser or() {
		return loggerChooser;
	}
	
	LogLevel getLogLevel() {
		return logLevel;
	}
	
	private void printFormattedLogLevel(final LogLevel logLevel) {
		outputStream.print(logLevel.prefix());
		outputStream.print(" ");
	}
	
	private void printFormattedPrefix(final String prefix) {
		int printedCharacters = 0;
		for(; printedCharacters<20-prefix.length(); printedCharacters++) {
			outputStream.print(" ");
		}
		
		for(int i=0; printedCharacters < 20; i++, printedCharacters++) {
			outputStream.print(prefix.charAt(i));
		}
		
		if(prefix.length() == 0) {
			outputStream.print("  ");
		} else {
			outputStream.print(": ");
		}
		printedCharacters++;
	}
	
	public void logNewLine(final LogLevel logLevel) {
		if(logLevel.ordinal() <= this.logLevel.ordinal()) {
			printFormattedLogLevel(logLevel);
			outputStream.println();
		}
	}
	
	public void logMajorSeparator(final LogLevel logLevel) {
		log(logLevel, "===============================================",
				"==================================================");
		
	}
	
	public void logSeparator(final LogLevel logLevel, final String name) {
		log(logLevel, "--------------------------------------", padMessage(name));
	}
	
	private String padMessage(final String message) {
		StringBuilder paddedMessageBuilder = new StringBuilder();
		paddedMessageBuilder.append(message);
		paddedMessageBuilder.append(" ");
		final int messageLength = paddedMessageBuilder.length();
		
		for(int i=messageLength; i<50; i++) {
			paddedMessageBuilder.append("-");
		}
		
		return paddedMessageBuilder.toString();
	}
	
}

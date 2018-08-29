package com.egroup.amazon.cloudwatch.logback.entity;

public class ThrowableProxy {
	public String message;
	public String className;
	public String[] stackTraceElements;
	public ThrowableProxy cause;
}

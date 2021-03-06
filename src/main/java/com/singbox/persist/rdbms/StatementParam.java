package com.singbox.persist.rdbms;

import java.util.Arrays;

import com.singbox.persist.rdbms.sorm.stm.Statement;

public class StatementParam extends Statement {

	private Object[] args;

	// ---------------------------------------------------------------------------------------------------------------------------

	public Object[] getArgs() {
		if (args == null) {
			args = new Object[0];
		}
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public boolean addArg(Object newArg) {
		if (args == null) {
			args = new Object[0];
		}

		Object[] args = new Object[this.args.length + 1];

		for (int i = 0; i < this.args.length; i++) {
			args[i] = this.args[i];
		}

		args[args.length - 1] = newArg;
		this.args = args;

		return true;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public String toString() {
		String s = "";

		s += "\n" + this.getSql();
		s += "\n";

		if (args == null) {
			args = new Object[0][0];
		}

		s += "\n" + Arrays.toString(args);

		return s;
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	@SuppressWarnings("rawtypes")
	protected Object buildArg(Object arg, Class c) {
		return (arg == null || arg.toString().trim().isEmpty()) ? c : arg;
	}

	@SuppressWarnings("rawtypes")
	protected Object buildArgTrim(Object arg, Class c) {
		if (c == String.class) {
			return (arg == null || arg.toString().trim().isEmpty()) ? c : arg.toString().trim();
		}
		return buildArg(arg, c);
	}

	@SuppressWarnings("rawtypes")
	protected Object buildArgTrimLower(Object arg, Class c) {
		if (c == String.class) {
			return (arg == null || arg.toString().trim().isEmpty()) ? c
					: "%" + arg.toString().trim().toLowerCase() + "%";
		}
		return (arg == null || arg.toString().trim().isEmpty()) ? c : arg;
	}

} // END CLASS -----------------------------------------------------------------

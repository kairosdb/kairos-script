package org.kairosdb.plugins.script.data;

/**
 Created by bhawkins on 5/19/17.
 */
public class Value
{
	public long time;
	public double value;

	public Value(long time, double value)
	{
		this.time = time;
		this.value = value;
	}
}

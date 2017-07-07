package org.kairosdb.plugins.script.data;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import java.util.ArrayList;
import java.util.List;

/**
 Created by bhawkins on 5/19/17.
 */
public class Result
{
	public String name;

	public SetMultimap<String, String> tags;

	public List<Value> values;

	public Result()
	{
		name = "";
	}

	public Result addTag(String name, String value)
	{
		if (tags == null)
			tags = HashMultimap.create();

		tags.put(name, value);

		return this;
	}

	public Result addValue(long time, double value)
	{
		if (values == null)
			values = new ArrayList<>();

		values.add(new Value(time, value));

		return this;
	}
}

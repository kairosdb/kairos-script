package org.kairosdb.plugins.script.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 Created by bhawkins on 5/19/17.
 */
public class Query
{
	public int sample_size;

	public List<Result> results;

	public Query()
	{
		sample_size = 0;
	}

	public Result addResult(String name)
	{
		if (results == null)
			results = new ArrayList<>();

		Result result = new Result();
		result.name = name;
		results.add(result);
		return result;
	}
}

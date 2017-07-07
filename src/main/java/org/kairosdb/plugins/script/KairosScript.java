package org.kairosdb.plugins.script;

import org.kairosdb.plugins.script.data.QueryResults;
import org.kairosdb.plugins.script.data.Result;

import java.util.Arrays;

/**
 Created by bhawkins on 5/19/17.
 This class is full of helper functions for working with time series data
 */
public class KairosScript
{
	private QueryResults m_queryResults = new QueryResults();

	public Result combineResults(Result... results)
	{
		if ((results.length == 1) && (results[0] instanceof Iterable))
			return (combineResults((Iterable<Result>)results[0]));
		else
			return (combineResults(Arrays.asList(results)));
	}


	public Result combineResults(Iterable<Result> results)
	{
		System.out.println("You did it");
		return null;
	}

	public QueryResults getQueryResults()
	{
		return m_queryResults;
	}


}

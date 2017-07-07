package org.kairosdb.plugins.script.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 Created by bhawkins on 5/19/17.
 */
public class QueryResults
{
	public List<Query> queries;

	public Query addQuery()
	{
		if (queries == null)
			queries = new ArrayList<>();

		Query query = new Query();
		queries.add(query);
		return query;
	}
}

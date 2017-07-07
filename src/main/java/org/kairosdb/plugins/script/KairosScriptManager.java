package org.kairosdb.plugins.script;

import com.google.common.collect.SetMultimap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.kairosdb.core.datastore.KairosDatastore;
import org.kairosdb.core.http.rest.json.SetMultimapDeserializer;
import org.kairosdb.core.http.rest.json.SetMultimapSerializer;
import org.kairosdb.plugins.script.data.Value;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 Created by bhawkins on 5/19/17.
 */
public class KairosScriptManager
{
	private final ScriptEngineManager m_scriptEngineManager;
	private final Gson m_gson;

	public KairosScriptManager()
	{
		m_scriptEngineManager = new ScriptEngineManager();

		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(SetMultimap.class, new SetMultimapDeserializer());
		builder.registerTypeAdapter(SetMultimap.class, new SetMultimapSerializer());
		builder.registerTypeAdapter(Value.class, new ValueDeserializer());
		builder.registerTypeAdapter(Value.class, new ValueSerializer());

		m_gson = builder.create();
	}

	public ScriptEngine getScriptEngine()
	{
		return m_scriptEngineManager.getEngineByName("nashorn");
	}

	public Gson getGson()
	{
		return m_gson;
	}
}

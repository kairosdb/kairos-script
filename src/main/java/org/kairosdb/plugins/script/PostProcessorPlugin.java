package org.kairosdb.plugins.script;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.kairosdb.core.PluginException;
import org.kairosdb.core.annotation.PluginName;
import org.kairosdb.core.datastore.KairosDatastore;
import org.kairosdb.core.datastore.QueryPostProcessingPlugin;
import org.kairosdb.core.datastore.ServiceKeyStore;
import org.kairosdb.plugins.script.data.QueryResults;

import javax.inject.Inject;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.*;

/**
 Created by bhawkins on 5/19/17.
 */
@PluginName(name = "kairos_script", description = "Post processing scripts for queries")
public class PostProcessorPlugin implements QueryPostProcessingPlugin
{
	private static final String PLUGIN_NAME = "kairos_script";
	private final ServiceKeyStore m_serviceKeyStore;
	private final KairosDatastore m_kairosDatastore;
	private final ScriptEngine m_scriptEngine;
	private final Gson m_gson;
	private File m_scriptFile;

	@Inject
	public PostProcessorPlugin(KairosScriptManager kairosScriptManager,
			ServiceKeyStore serviceKeyStore, KairosDatastore kairosDatastore)
	{
		m_serviceKeyStore = serviceKeyStore;
		m_kairosDatastore = kairosDatastore;

		m_scriptEngine = kairosScriptManager.getScriptEngine();
		m_gson = kairosScriptManager.getGson();
	}

	@Override
	public File processQueryResults(File queryResults) throws PluginException
	{
		if (m_scriptFile == null || !m_scriptFile.exists())
			throw new PluginException(PLUGIN_NAME, "No script file specified");

		File respFile = null;
		try
		{
			respFile = File.createTempFile("kairos_script", ".json", new File(m_kairosDatastore.getCacheDir()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(respFile), "UTF-8"));

			m_scriptEngine.put("keyStore", m_serviceKeyStore);
			m_scriptEngine.put("datastore", m_kairosDatastore);
			KairosScript ks = new KairosScript();
			m_scriptEngine.put("ks", ks);

			//parse in results from queryResults and set that
			JsonParser parser = new JsonParser();
			JsonObject rootObject = parser.parse(new FileReader(queryResults)).getAsJsonObject();

			QueryResults results = m_gson.fromJson(rootObject, QueryResults.class);
			m_scriptEngine.put("queryResults", results);

			m_scriptEngine.eval(new FileReader(m_scriptFile));

			writer.write(m_gson.toJson(ks.getQueryResults()));
			writer.close();
		}
		catch (IOException | ScriptException e)
		{
			throw new PluginException(PLUGIN_NAME, "Failed to write output file", e);
		}
		return respFile;
	}

	@Override
	public String getName()
	{
		return null;
	}

	public void setScript(String scriptPath)
	{
		m_scriptFile = new File(scriptPath);
	}
}

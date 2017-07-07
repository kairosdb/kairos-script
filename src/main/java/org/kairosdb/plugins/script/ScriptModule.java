package org.kairosdb.plugins.script;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 Created by bhawkins on 5/19/17.
 */
public class ScriptModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(KairosScriptManager.class).in(Singleton.class);
		bind(PostProcessorPlugin.class);
	}
}

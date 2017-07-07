package org.kairosdb.plugins.script;

import com.google.gson.*;
import org.kairosdb.plugins.script.data.Value;

import java.lang.reflect.Type;

/**
 Created by bhawkins on 5/19/17.
 */
public class ValueSerializer implements JsonSerializer<Value>
{
	@Override
	public JsonElement serialize(Value src, Type typeOfSrc, JsonSerializationContext context)
	{
		JsonArray value = new JsonArray();
		value.add(new JsonPrimitive(src.time));
		value.add(new JsonPrimitive(src.value));
		return value;
	}
}

package org.kairosdb.plugins.script;

import com.google.gson.*;
import org.kairosdb.plugins.script.data.Value;

import java.lang.reflect.Type;

/**
 Created by bhawkins on 5/19/17.
 */
public class ValueDeserializer implements JsonDeserializer<Value>
{
	@Override
	public Value deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
	{
		JsonArray valueArray = json.getAsJsonArray();
		long time = valueArray.get(0).getAsLong();
		double value = valueArray.get(1).getAsDouble();

		return new Value(time, value);
	}
}

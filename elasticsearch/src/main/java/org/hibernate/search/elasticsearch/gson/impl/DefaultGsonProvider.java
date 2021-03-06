/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.search.elasticsearch.gson.impl;

import java.util.function.Supplier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Guillaume Smet
 */
public class DefaultGsonProvider implements GsonProvider {

	protected static final String ELASTIC_SEARCH_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

	public static GsonProvider create(Supplier<GsonBuilder> builderBaseSupplier) {
		return new DefaultGsonProvider( builderBaseSupplier );
	}

	private DefaultGsonProvider(Supplier<GsonBuilder> builderBaseSupplier) {
		// Null serialization needs to be enabled to index null fields
		gson = builderBaseSupplier.get()
				.serializeNulls()
				.create();

		gsonPrettyPrinting = builderBaseSupplier.get()
				.setPrettyPrinting()
				.create();

		gsonNoSerializeNulls = builderBaseSupplier.get()
				.create();
	}

	private final Gson gson;

	private final Gson gsonPrettyPrinting;

	private final Gson gsonNoSerializeNulls;

	@Override
	public Gson getGson() {
		return gson;
	}

	@Override
	public Gson getGsonPrettyPrinting() {
		return gsonPrettyPrinting;
	}

	@Override
	public Gson getGsonNoSerializeNulls() {
		return gsonNoSerializeNulls;
	}

}

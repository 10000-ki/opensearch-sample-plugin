/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */
package org.opensearch.sample.plugin;

import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;

import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.opensearch.client.Request;
import org.opensearch.client.Response;
import org.opensearch.plugins.Plugin;
import org.opensearch.test.OpenSearchIntegTestCase;

import com.carrotsearch.randomizedtesting.annotations.ThreadLeakScope;

@ThreadLeakScope(ThreadLeakScope.Scope.NONE)
@OpenSearchIntegTestCase.ClusterScope(scope = OpenSearchIntegTestCase.Scope.SUITE, numDataNodes = 1, numClientNodes = 0, supportsDedicatedMasters = false)
public class SortCharPluginIT extends OpenSearchIntegTestCase {

	@Override
	protected Collection<Class<? extends Plugin>> nodePlugins() {
		return Collections.singletonList(SortCharPlugin.class);
	}

	public void testPluginInstalled() throws IOException, ParseException {
		Response response = getRestClient().performRequest(new Request("GET", "/_cat/plugins"));
		String body = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);

		logger.info("response body: {}", body);
		assertThat(body, containsString("sort_char"));
	}
}

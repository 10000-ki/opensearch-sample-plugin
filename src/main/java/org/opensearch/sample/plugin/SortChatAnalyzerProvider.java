package org.opensearch.sample.plugin;

import org.opensearch.common.inject.Inject;
import org.opensearch.common.inject.assistedinject.Assisted;
import org.opensearch.common.settings.Settings;
import org.opensearch.index.IndexSettings;
import org.opensearch.index.analysis.AbstractIndexAnalyzerProvider;
import org.opensearch.sample.plugin.analysis.SortCharAnalyzer;

public class SortChatAnalyzerProvider extends AbstractIndexAnalyzerProvider<SortCharAnalyzer> {

	protected SortCharAnalyzer analyzer = new SortCharAnalyzer();

	@Inject
	public SortChatAnalyzerProvider(IndexSettings indexSettings, @Assisted String name,
		@Assisted Settings settings) {
		super(indexSettings, name, settings);
	}

	@Override
	public SortCharAnalyzer get() {
		return this.analyzer;
	}
}

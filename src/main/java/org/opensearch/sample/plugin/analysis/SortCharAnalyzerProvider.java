package org.opensearch.sample.plugin.analysis;

import org.opensearch.common.settings.Settings;
import org.opensearch.env.Environment;
import org.opensearch.index.IndexSettings;
import org.opensearch.index.analysis.AbstractIndexAnalyzerProvider;

public class SortCharAnalyzerProvider extends AbstractIndexAnalyzerProvider<SortCharAnalyzer> {

	private final SortCharAnalyzer analyzer;

	public SortCharAnalyzerProvider(IndexSettings indexSettings, String name,
		Settings settings) {
		super(indexSettings, name, settings);
		analyzer = new SortCharAnalyzer();
	}

	public static SortCharAnalyzerProvider getIkSmartAnalyzerProvider(IndexSettings indexSettings, Environment env,
		String name, Settings settings) {
		return new SortCharAnalyzerProvider(indexSettings, name, settings);
	}

	@Override
	public SortCharAnalyzer get() {
		return this.analyzer;
	}
}

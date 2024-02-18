package org.opensearch.sample.plugin;

import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.opensearch.index.analysis.AnalyzerProvider;
import org.opensearch.index.analysis.TokenizerFactory;
import org.opensearch.indices.analysis.AnalysisModule;
import org.opensearch.plugins.AnalysisPlugin;
import org.opensearch.plugins.Plugin;
import org.opensearch.sample.plugin.analysis.SortCharAnalyzerProvider;
import org.opensearch.sample.plugin.analysis.SortTokenizerFactory;

public class SortCharPlugin extends Plugin implements AnalysisPlugin {

	@Override
	public Map<String, AnalysisModule.AnalysisProvider<TokenizerFactory>> getTokenizers() {
		Map<String, AnalysisModule.AnalysisProvider<TokenizerFactory>> extra = new HashMap<>();

		extra.put("sort_char", SortTokenizerFactory::getTokenizerFactory);

		return extra;
	}

	@Override
	public Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> getAnalyzers() {
		Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> extra = new HashMap<>();

		extra.put("sort_char", SortCharAnalyzerProvider::getIkSmartAnalyzerProvider);

		return extra;
	}

}

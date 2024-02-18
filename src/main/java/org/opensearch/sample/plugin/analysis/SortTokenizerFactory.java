package org.opensearch.sample.plugin.analysis;

import org.apache.lucene.analysis.Tokenizer;
import org.opensearch.common.settings.Settings;
import org.opensearch.env.Environment;
import org.opensearch.index.IndexSettings;
import org.opensearch.index.analysis.AbstractTokenizerFactory;

public class SortTokenizerFactory extends AbstractTokenizerFactory {

	public SortTokenizerFactory(IndexSettings indexSettings,
		Settings settings, String name) {
		super(indexSettings, settings, name);
	}

	public static SortTokenizerFactory getTokenizerFactory(IndexSettings indexSettings, Environment env, String name,
		Settings settings) {
		return new SortTokenizerFactory(indexSettings, settings, name);
	}

	@Override
	public Tokenizer create() {
		return new SortCharTokenizer();
	}
}

package org.opensearch.sample.plugin.analysis;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;

public class SortCharAnalyzer extends Analyzer {
	@Override
	protected TokenStreamComponents createComponents(String s) {
		Tokenizer tokenizer = new SortCharTokenizer();
		TokenStream lowerCaseFilter = new LowerCaseFilter(tokenizer);
		return new TokenStreamComponents(tokenizer, lowerCaseFilter);
	}
}

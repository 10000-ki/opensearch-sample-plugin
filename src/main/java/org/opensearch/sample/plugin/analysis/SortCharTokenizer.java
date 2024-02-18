package org.opensearch.sample.plugin.analysis;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class SortCharTokenizer extends Tokenizer {

	protected CharTermAttribute charTermAttribute = addAttribute(CharTermAttribute.class);

	/*
	 * This object stores the string that we are turning into tokens. We will
	 * process its content
	 * as we call the incrementToken() function.
	 */
	protected String stringToTokenize;

	/*
	 * This stores the current position in this.stringToTokenize. We will
	 * increment its value as
	 * we call the incrementToken() function.
	 */
	protected int position = 0;

	public SortCharTokenizer() {
		int numChars;
		char[] buffer = new char[1024];
		StringBuilder stringBuilder = new StringBuilder();
		try {
			while ((numChars = input.read(buffer, 0, buffer.length)) != -1) {
				stringBuilder.append(buffer, 0, numChars);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		char[] stringBuilderChars = stringBuilder.toString().toLowerCase(Locale.US).toCharArray();
		Arrays.sort(stringBuilderChars);
		this.stringToTokenize = new String(stringBuilderChars);

	}

	@Override
	public void reset() throws IOException {
		super.reset();
		this.position = 0;
	}

	@Override
	public boolean incrementToken() {
		// Clear anything that is already saved in this.charTermAttribute
		this.charTermAttribute.setEmpty();
		//pass everthing
		if (this.position < this.stringToTokenize.length()) {
			String nextToken = this.stringToTokenize.substring(this.position);
			this.charTermAttribute.append(nextToken);
			this.position = this.stringToTokenize.length();
			return true;
		} // Execute this block if no more tokens exist in the string.
		else {
			return false;
		}
	}

	private List<String> tokenizeString(Analyzer analyzer, Reader reader) {
		List<String> result = new ArrayList<>();
		try (TokenStream stream = analyzer.tokenStream(null, reader)) {
			stream.reset();
			while (stream.incrementToken()) {
				result.add(stream.getAttribute(CharTermAttribute.class).toString());
			}
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		return result;
	}
}

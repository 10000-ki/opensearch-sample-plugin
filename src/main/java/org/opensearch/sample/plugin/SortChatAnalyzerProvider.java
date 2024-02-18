package org.opensearch.sample.plugin;

import org.opensearch.common.inject.Inject;
import org.opensearch.common.inject.assistedinject.Assisted;
import org.opensearch.common.settings.Settings;
import org.opensearch.index.IndexSettings;
import org.opensearch.index.analysis.AbstractIndexAnalyzerProvider;
import org.opensearch.sample.plugin.analysis.SortCharAnalyzer;

public class SortChatAnalyzerProvider extends AbstractIndexAnalyzerProvider<SortCharAnalyzer> {

	/*
	 * Instance of SortCharAnalyzer class that is returned by this class.
	 */
	protected SortCharAnalyzer analyzer = new SortCharAnalyzer();

	/*
	 * Name to associate with this class. We will use this in
	 * PlusSignBinderProcessor.
	 */
	public static final String NAME = "sort_char";

	/*
	 * Constructor. Nothing special here.
	 */
	@Inject
	public SortChatAnalyzerProvider(IndexSettings indexSettings, @Assisted String name,
		@Assisted Settings settings) {
		super(indexSettings, name, settings);
	}

	/*
	 * This function needs to be overridden to return an instance of
	 * SortCharAnalyzer.
	 */
	@Override
	public SortCharAnalyzer get() {
		return this.analyzer;
	}
}

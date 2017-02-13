package com.chumbok.news;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

public class EndlessScrollListener implements OnScrollListener {

	int currentFirstVisibleItem = 0;
	int currentVisibleItemCount = 0;
	int totalItemCount = 0;
	int currentScrollState = 0;

	Long startIndex = 0L;
	Long offset = 10L;
	View footerView;

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		this.currentScrollState = scrollState;
		this.isScrollCompleted();
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		this.currentFirstVisibleItem = firstVisibleItem;
		this.currentVisibleItemCount = visibleItemCount;
		this.totalItemCount = totalItemCount;

	}

	private void isScrollCompleted() {
		if (this.currentVisibleItemCount > 0 && this.currentScrollState == SCROLL_STATE_IDLE
				&& this.totalItemCount == (currentFirstVisibleItem + currentVisibleItemCount)) {
			if (!loadingMore) {
				loadingMore = true;
				new LoadMoreItemsTask(this).execute();
			}
		}
	}

}

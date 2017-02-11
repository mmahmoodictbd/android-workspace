package com.chumbok.dailypoetry.task;

public class LoadMoreItemsTask{

}

/*
public class LoadMoreItemsTask extends AsyncTask<Void, Void, List<ListItem>> {

	private Activity activity;
	private View footer;
	boolean loadingMore = false;

	private LoadMoreItemsTask(Activity activity) {
		this.activity = activity;
		loadingMore = true;
		footer = activity.getLayoutInflater().inflate(R.layout.base_list_item_loading_footer, null);
	}

	@Override
	protected void onPreExecute() {
		list.addFooterView(footer);
		list.setAdapter(adapter);
		super.onPreExecute();
	}

	@Override
	protected List<ListItem> doInBackground(Void... voids) {

		return getNextItems(startIndex, offset);
	}

	@Override
	protected void onPostExecute(List<ListItem> listItems) {
		if (footer != null) {
			list.removeFooterView(footer);
		}
		list.setAdapter(adapter);

		loadingMore = false;
		if (listItems.size() > 0) {
			startIndex = startIndex + listItems.size();
			setItems(listItems);
		}
		super.onPostExecute(listItems);
	}

}*/
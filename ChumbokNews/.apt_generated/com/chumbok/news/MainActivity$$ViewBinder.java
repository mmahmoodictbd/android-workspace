// Generated code from Butter Knife. Do not modify!
package com.chumbok.news;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.chumbok.news.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131361792, "field 'listView'");
    target.listView = finder.castView(view, 2131361792, "field 'listView'");
  }

  @Override public void unbind(T target) {
    target.listView = null;
  }
}

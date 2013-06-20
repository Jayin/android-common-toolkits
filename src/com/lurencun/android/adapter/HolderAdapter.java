package com.lurencun.android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author : 桥下一粒砂 chenyoca@gmail.com
 * date   : 2012-7-10
 * 实现HolderView缓存方法的Adapter。
 */
public class HolderAdapter<T> extends AbstractAdapter<T> {

	/**
	 * @see AbstractAdapter#AbstractAdapter(android.view.LayoutInflater, ViewBuilderDelegate)
	 */
	public HolderAdapter(LayoutInflater inflater, ViewBuilderDelegate<T> creator) {
		super(inflater, creator);
	}

	private static class ViewHolder{
		public View view;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = viewBuilderDelegate.newView(layoutInflater);
			holder.view = convertView;
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
			viewBuilderDelegate.releaseView(convertView, getItem(position));
		}
		viewBuilderDelegate.bindView(holder.view, position, getItem(position));
		return convertView;
	}
}

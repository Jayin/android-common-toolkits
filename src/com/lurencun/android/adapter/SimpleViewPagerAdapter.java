package com.lurencun.android.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author : 桥下一粒砂 chenyoca@gmail.com
 * date    : 2012-10-15
 * 为ViewPager提供的通用Adapter
 */
public class SimpleViewPagerAdapter<T> extends PagerAdapter {

	private List<T> dataSetReference;
	private LayoutInflater layoutInflater;
	private ViewBuilderDelegate<T> viewBuilderDelegate;
	private boolean mIsForceUpdateView = false;
	
	public SimpleViewPagerAdapter (LayoutInflater inf, ViewBuilderDelegate<T> delegate){
		viewBuilderDelegate = delegate;
		layoutInflater = inf;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		View view = (View) object;
		container.removeView(view);
		int index = Math.max(0, Math.min(position, dataSetReference.size() - 1));
		viewBuilderDelegate.releaseView(view, dataSetReference.get(index));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view = viewBuilderDelegate.newView(layoutInflater);
		viewBuilderDelegate.bindView(view, position, dataSetReference.get(position));
		container.addView(view);
		return view;
	}

	@Override
	public int getCount() {
		return dataSetReference == null ? 0 : dataSetReference.size();
	}
	
	public T getDataItemAt(int position){
		return dataSetReference == null ? null : dataSetReference.get(position);
	}
	
	public void toggleForceUpdate(boolean isForce){
		mIsForceUpdateView = isForce;
	}
	
	@Override
	public int getItemPosition(Object object) {
		if(mIsForceUpdateView){
			return POSITION_NONE;
		}
		return super.getItemPosition(object);
	}

	public void update(List<T> ds){
		dataSetReference = ds;
		notifyDataSetChanged();
	}
	
	public void add(List<T> extraData){
		if(dataSetReference == null){
			update(extraData);
			return;
		}
		dataSetReference.addAll(extraData);
		notifyDataSetChanged();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

}

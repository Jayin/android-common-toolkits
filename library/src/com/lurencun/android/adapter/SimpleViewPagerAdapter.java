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

	private OnPagerItemClickListener<T> onPagerItemClickListener;

	/**
	 * ViewPager页面被点击监听接口
	 * @param <T>
	 */
	public interface OnPagerItemClickListener<T>{

		/**
		 * ViewPager页面被点击
		 * @param pageView 当前页面View
		 * @param position 当前位置
		 * @param data 当前数据
		 */
		void onPagerItemClick (View pageView, int position, T data);
	}
	
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
	public Object instantiateItem(ViewGroup container,final int position) {
		final View view = viewBuilderDelegate.newView(layoutInflater);
		final T data = dataSetReference.get(position);
		viewBuilderDelegate.bindView(view, position, data);
		if(onPagerItemClickListener != null){
			view.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick (View view) {
					onPagerItemClickListener.onPagerItemClick(view, position, data);
				}
			});
		}
		container.addView(view);
		return view;
	}

	@Override
	public int getCount() {
		return dataSetReference == null ? 0 : dataSetReference.size();
	}

	/**
	 * 添加ViewPager Item页面被点击的监听接口
	 * @param onPagerItemClickListener 监听接口
	 */
	public void setOnPagerItemClickListener (OnPagerItemClickListener<T> onPagerItemClickListener) {
		this.onPagerItemClickListener = onPagerItemClickListener;
	}

	/**
	 * 获取数据源中指定位置的数据对象
	 * @param position 位置
	 * @return 数据对象。如果数据源为null，则返回null。
	 */
	public T getDataItemAt(int position){
		return dataSetReference == null ? null : dataSetReference.get(position);
	}

	/**
	 * 设置强制刷新
	 * @param isForce true为强制刷新，否则不强制刷新。
	 */
	public void setForceUpdate(boolean isForce){
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

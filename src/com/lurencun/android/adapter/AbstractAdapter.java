package com.lurencun.android.adapter;

import java.util.List;

import android.view.LayoutInflater;
import android.widget.BaseAdapter;

/**
 * @author : 桥下一粒砂
 * @email  : chenyoca@gmail.com
 * @date   : 2012-9-13
 * @desc   : 抽象Adapter类
 * @param <T>
 */
public abstract class AbstractAdapter<T> extends BaseAdapter {

	protected List<T> dataSetReference;
	protected LayoutInflater layoutInflater;
	protected ViewCreator<T> creator;
	
	/**
	 * </br><b>description : </b>	创建Adapter，需要给定View创建接口。
	 * @param inflater
	 * @param creator
	 */
	public AbstractAdapter(LayoutInflater inflater,ViewCreator<T> creator){
		this.layoutInflater = inflater;
		this.creator = creator;
	}

	/**
	 * </br><b>title : </b>		更新数据集
	 * </br><b>description :</b>更新数据集
	 * </br><b>time :</b>		2012-7-10 下午11:06:40
	 * @param data
	 */
	public void update(List<T> data){
		dataSetReference = data;
	}
	
	public void clear(){
		if(dataSetReference != null){
			dataSetReference.clear();
		}
	}
	
	/**
	 * 提交更新
	 */
	public void postChange(){
		notifyDataSetChanged();
	}
	
	/**
	 * 刷新
	 */
	public void postInvalidate(){
		notifyDataSetInvalidated();
	}
	
	/**
	 * <b>description :</b>添加数据集，向数据缓存中添加多个元素。
	 * </br><b>time :</b>		2012-7-17 下午10:19:45
	 * @param set
	 */
	public void add(List<T> set){
	    if( null == dataSetReference ){
	    	throw new NullPointerException("DataSet is NULL, call 'update' first !");
	    }
	    dataSetReference.addAll(set);
	}
	
	public void remove(int position){
		if(dataSetReference == null) return;
		dataSetReference.remove(position);
	}
	
	@Override
	public int getCount() {
		return null == dataSetReference ? 0 : dataSetReference.size();
	}

	@Override
	public T getItem(int position) {
		return null == dataSetReference ? null : dataSetReference.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}

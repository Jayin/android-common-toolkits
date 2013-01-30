package com.lurencun.android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author : 桥下一粒砂
 * @email  : chenyoca@gmail.com
 * @date   : 2012-9-13
 * @desc   : TODO
 * @param <T>
 */
public class CommonAdapter<T> extends AbstractAdapter<T> {

	public CommonAdapter(LayoutInflater inflater, ViewCreator<T> creator) {
		super(inflater, creator);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		T data = dataSetReference.get(position);
		if(null == convertView){
			convertView = creator.createView(layoutInflater, position, data);
		}else{
			creator.updateView(convertView, position, data);
		}
		return convertView;
	}

}

package com.lurencun.android.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class InnerFragment extends Fragment {
	
	protected static final String KEY_LAYOUT_RES = "layout_res_id";
	protected static final int INVALID_LAYOUT_ID = -228441083;
	
	protected Activity activity;
	protected View contentView;
	
	private int layoutResourceId;
	
	public final void setLayoutParams(int layoutResId){
		Bundle args = new Bundle();
	    args.putInt(KEY_LAYOUT_RES, layoutResId);
	    setArguments(args);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = activity;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		layoutResourceId = getLayoutResId();
	}
	
	private int getLayoutResId(){
		Bundle args = getArguments();
		if( args != null ){
			return args.getInt(KEY_LAYOUT_RES);
		}else{
			return INVALID_LAYOUT_ID;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		if(layoutResourceId == INVALID_LAYOUT_ID) {
			throw new IllegalArgumentException(String.format("## %s ==> Illegal LayoutResourceId !", this.getClass().getSimpleName()));
		}
		contentView = inflater.inflate(layoutResourceId, null);
		return contentView;
	}
	
}

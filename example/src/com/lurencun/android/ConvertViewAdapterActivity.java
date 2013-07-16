package com.lurencun.android;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.lurencun.android.adapter.ConvertViewAdapter;
import com.lurencun.android.adapter.ViewBuilderDelegate;

import java.util.ArrayList;
import java.util.List;

public class ConvertViewAdapterActivity extends ListActivity {

    class TestEntity{
        final int id;
        final String name;
        TestEntity(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    private ConvertViewAdapter<TestEntity> adapter;

    private List<TestEntity> entities = new ArrayList<TestEntity>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 测试数据
        for(int i=0;i<500;i++){
            TestEntity entity = new TestEntity(i,"item - "+i);
            entities.add(entity);
        }

        adapter = new ConvertViewAdapter<TestEntity>(getLayoutInflater(),new ViewBuilder());

        // 将Adapter绑定到ListActivity内置的ListView中
        setListAdapter(adapter);

        // 将数据更新到Adapter中
        adapter.update(entities);

    }

    class ViewBuilder implements ViewBuilderDelegate<TestEntity> {
        @Override
        public View newView(LayoutInflater layoutInflater) {
            View view = layoutInflater.inflate(R.layout.list_cell, null);
            view.setTag(new ViewHolder(view));
            return view;
        }
        @Override
        public void bindView(View view, int position, TestEntity data) {
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            viewHolder.mText1.setText(String.valueOf(data.id));
            viewHolder.mText2.setText(data.name);
        }
        @Override
        public void releaseView(View view, TestEntity data) {

        }
    }

    // View Holder，优化findViewById的查找性能
    private final static class ViewHolder {
        public final TextView mText1;
        public final TextView mText2;

        public ViewHolder(View v) {
            mText1 = (TextView) v.findViewById(R.id.text_view_1);
            mText2 = (TextView) v.findViewById(R.id.text_view_2);
        }
    }
}

package com.app.modules;

import java.util.List;

import com.app.modules.TotalScoreItemListAdapter.ItemClickInterfaceListener;
import com.app.yuejuan.R;
import com.app.yuejuan.R.drawable;
import com.app.yuejuan.R.id;
import com.app.yuejuan.R.layout;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.View.OnFocusChangeListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AlreadyMarkItemListAdapter extends BaseAdapter{
	LayoutInflater inflater = null;
    List<AlreadyMarkItemInfo> listInfo;
    ItemClickInterfaceListener itemClickListener;
    public AlreadyMarkItemListAdapter(Context context,List<AlreadyMarkItemInfo> listInfo, ItemClickInterfaceListener itemClickListener){
    	
        inflater = LayoutInflater.from(context);
        this.listInfo = listInfo;
        this.itemClickListener = itemClickListener;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listInfo.size();
    }
    @Override
    public Object getItem(int index) {
        // TODO Auto-generated method stub
        return listInfo.get(index);
    }
    @Override
    public long getItemId(int index) {
        // TODO Auto-generated method stub
        return index;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder;
        final AlreadyMarkItemInfo itemInfo = listInfo.get(position);
        if(convertView == null || convertView.getTag() == null){
            convertView = inflater.inflate(R.layout.list_item_already_mark,null);
            holder = new ViewHolder();
            holder.itemBox = convertView;
            holder.numView = (TextView)convertView.findViewById(R.id.que_num);
            holder.scoreView = (TextView)convertView.findViewById(R.id.que_score);
            holder.timeView = (TextView)convertView.findViewById(R.id.que_time);
            
            convertView.setTag(holder);
        }else{
             
            holder = (ViewHolder)convertView.getTag();
        }
        
        
        holder.numView.setText(itemInfo.quename);
        holder.scoreView.setText(itemInfo.que_score);
        holder.timeView.setText(itemInfo.que_time);

        
        holder.itemBox.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				itemClickListener.Callback(itemInfo);
			}
		});
        return convertView;
    }
    public class ViewHolder{
    	View itemBox;
        public TextView numView;
        public TextView scoreView;
        public TextView timeView;
    }
    public static interface ItemClickInterfaceListener{
    	public void Callback(AlreadyMarkItemInfo itemInfo);
    }
}

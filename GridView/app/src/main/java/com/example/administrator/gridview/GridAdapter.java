package com.example.administrator.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.PipedOutputStream;
import java.util.List;

/**
 * Created by Administrator on 2019/3/21.
 */
public class GridAdapter extends BaseAdapter{
    private Context context;
    private List<Animal> datas;
    private  boolean mIsShowDelete;
    public GridAdapter(Context context, List<Animal> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size()+1;
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if(convertView==null)
        {
            view= LayoutInflater.from(context).inflate(R.layout.item_layout1,null);
            viewHolder=new ViewHolder();
            viewHolder.animalImage=(ImageView)view.findViewById(R.id.iv);
            viewHolder.animalName=(TextView)view.findViewById(R.id.tv);
            viewHolder.deleteImage=(ImageView)view.findViewById(R.id.delete_markView);
            view.setTag(viewHolder);
        }
        else
        {
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        if(position<datas.size()) {
            Animal animal=(Animal)getItem(position);
            viewHolder.animalImage.setImageResource(animal.getImgId());
            viewHolder.animalName.setText(animal.getAnimal());
            viewHolder.deleteImage.setVisibility(mIsShowDelete ? View.VISIBLE : View.GONE);
            if (mIsShowDelete) {
                viewHolder.deleteImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        datas.remove(position);
                        setmIsShowDelete(false);
                    }
                });
            }
        }
        else
        {
            viewHolder.animalImage.setImageResource(R.drawable.add);
//            viewHolder.animalImage.setScaleType(ImageView.ScaleType.CENTER);
//            viewHolder.animalImage.setLayoutParams(new LinearLayout.LayoutParams(50,50));
            viewHolder.animalName.setText("单击添加");
            viewHolder.deleteImage.setVisibility(View.GONE);
        }
        return view;
    }
    class ViewHolder
    {
        ImageView animalImage,deleteImage;
        TextView animalName;
    }
    public void setmIsShowDelete(boolean mIsShowDelete)
    {
        this.mIsShowDelete=mIsShowDelete;
        notifyDataSetChanged();
    }
}

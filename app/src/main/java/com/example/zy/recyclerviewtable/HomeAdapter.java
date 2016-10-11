package com.example.zy.recyclerviewtable;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> mDatas;
    private LayoutInflater mInflater;

    public enum ITEM_TYPE {
        ITEM1,
        ITEM2
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public HomeAdapter(Context context, List<String> datas) {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//		MyViewHolder holder = new MyViewHolder(mInflater.inflate(
//				R.layout.item_home, parent, false));
//		return holder;

        if (viewType == ITEM_TYPE.ITEM1.ordinal()) {
            return new Item1ViewHolder(mInflater.inflate(R.layout.recyclerview_item1, parent, false));
        } else {
            return new Item2ViewHolder(mInflater.inflate(R.layout.recyclerview_item2, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof Item1ViewHolder) {
            ((Item1ViewHolder) holder).mTextView.setText("Item1ViewHolder_item1");
        } else if (holder instanceof Item2ViewHolder) {
            ((Item2ViewHolder) holder).mTextView2.setText("Item1ViewHolder_item2");
        }


        //	holder.tv.setText(mDatas.get(position));

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
//					int pos = holder.getLayoutPosition();
//					mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
//					int pos = holder.getLayoutPosition();
//					mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
//					removeData(pos);
                    return false;
                }
            });
        }
    }

    //设置ITEM类型，可以自由发挥，这里设置item position单数显示item1 偶数显示item2
    @Override
    public int getItemViewType(int position) {
//Enum类提供了一个ordinal()方法，返回枚举类型的序数，这里ITEM_TYPE.ITEM1.ordinal()代表0， ITEM_TYPE.ITEM2.ordinal()代表1
        return position % 2 == 0 ? ITEM_TYPE.ITEM1.ordinal() : ITEM_TYPE.ITEM2.ordinal();
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void addData(int position) {
        mDatas.add(position, "Insert One");
        notifyItemInserted(position);
    }


    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    class MyViewHolder extends ViewHolder {

        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);


        }
    }

    public static class Item1ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public Item1ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.id_num1);
        }
    }

    public static class Item2ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView2;

        public Item2ViewHolder(View itemView) {
            super(itemView);
            mTextView2 = (TextView) itemView.findViewById(R.id.id_num2);
        }
    }
}
package com.websarva.wings.android.tableorderapp.customer.ordermenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.websarva.wings.android.tableorderapp.R;
import com.websarva.wings.android.tableorderapp.customer.ordermenu.OrderListingFragment.ListItem;

import java.util.ArrayList;

public class OderMenuAdapter extends BaseAdapter {

    private ArrayList<ListItem> mList;

    Context context;
    LayoutInflater layoutInflater = null;

    /**
     * コンストラクタ
     * @param mList
     */
    public OderMenuAdapter(Context context, ArrayList<ListItem> mList) {
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mList = mList;
    }

    /**
     * 設定必須
     * Listに表示するデータの個数を設定する
     * @return int
     */
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * 設定必須
     * リストの中に表示するViewを設定する
     * @param position int
     * @param convertView View
     * @param parent ViewGroup
     * @return View
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.row_order_list, parent, false);

        ((TextView)convertView.findViewById(R.id.product_text)).setText(mList.get(position).getOrderProduct());
        ((TextView)convertView.findViewById(R.id.volume_text)).setText(mList.get(position).getOrderVolume());

        return convertView;
    }
}

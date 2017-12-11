package recipe.tangy.com.tangyrecipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by binod on 4/7/17.
 */

public class NavigationAdapter extends BaseAdapter {
    String[] menusItems;
    Context context;
    LayoutInflater inflater;

    public NavigationAdapter(Context context, String[] menuItems) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.menusItems = menuItems;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return menusItems.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.nav_item, null);
            holder = new ViewHolder();
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            holder.ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvTitle.setText(menusItems[position]);

        return convertView;
    }

    class ViewHolder {
        TextView tvTitle;
        ImageView ivIcon;
    }



    }



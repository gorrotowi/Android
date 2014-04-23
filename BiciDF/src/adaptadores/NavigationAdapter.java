package adaptadores;

import java.util.ArrayList;

import com.gorro.bicidf.R;
import com.gorro.entidades.ItemDrawer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NavigationAdapter extends BaseAdapter {

	private Activity activity;
	ArrayList<ItemDrawer> arrayList;

	public NavigationAdapter(Activity activity, ArrayList<ItemDrawer> arrayList) {
		super();
		this.activity = activity;
		this.arrayList = arrayList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrayList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return arrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public class Fila {
		TextView titulo_item;
		ImageView img_item;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Fila view;
		LayoutInflater inflater = activity.getLayoutInflater();
		if (convertView == null) {
			view = new Fila();
			ItemDrawer item = arrayList.get(position);
			convertView = inflater.inflate(R.layout.item_drawer_list, null);
			view.titulo_item = (TextView) convertView.findViewById(R.id.txtDrawer);
			view.img_item = (ImageView) convertView.findViewById(R.id.iconDrawer);
			
			view.titulo_item.setText(item.getTitulo());
			view.img_item.setImageResource(item.getIcono());
			
			convertView.setTag(view);
		}else{
			view = (Fila) convertView.getTag();
		}
		return convertView;
	}

}

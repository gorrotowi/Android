package adaptadores;

import java.util.ArrayList;

import com.gorro.bicidf.R;
import com.gorro.entidades.ItemListaBicis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListaAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<ItemListaBicis> listArrayItem;
	
	public ListaAdapter(Context context, ArrayList<ItemListaBicis> listItem) {
		this.context = context;
		this.listArrayItem = listItem;
	}

	@Override
	public int getCount() {
		return listArrayItem.size();
	}

	@Override
	public Object getItem(int position) {
		return listArrayItem.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View rowList = convertView;
		if (rowList == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowList = inflater.inflate(R.layout.item_list_activity, parent, false);
		}
		
		//ItemListaBicis item = (ItemListaBicis) getItem(position);
		
		TextView txtTitulo = (TextView) rowList.findViewById(R.id.txtEstacion);
		TextView txtDisp = (TextView) rowList.findViewById(R.id.txtDisp);
		TextView txtLib = (TextView) rowList.findViewById(R.id.txtLugLib);
		TextView txtDis = (TextView) rowList.findViewById(R.id.txtDistancia);
		
		txtTitulo.setText(listArrayItem.get(position).getNombreEst());
		txtDisp.setText(listArrayItem.get(position).getDisponibles());
		txtLib.setText(listArrayItem.get(position).getLugaresLib());
		txtDis.setText(listArrayItem.get(position).getDistancia());
		//Log.i("Arrow Position", position+"");

		return rowList;
	}

}

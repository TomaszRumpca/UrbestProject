package lemur.urbest.urbestproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

<<<<<<< HEAD
public class SpecialAdapter extends BaseAdapter{

	private int[] colors = new int[]{0xc4c4c4c4,0x00000000};
=======
public class SpecialAdapter extends BaseAdapter {

	private int[] colors = new int[] { 0xc4c4c4c4, 0x00000000 };
>>>>>>> better
	private LayoutInflater mInflater;
	private float[] Latitude;
	private float[] Longitude;
	private String[] Date;
<<<<<<< HEAD
	
	
	public SpecialAdapter(Context context, float[] _Latitude, float[] _Longitude, String[] _Date){
=======

	public SpecialAdapter(Context context, float[] _Latitude,
			float[] _Longitude, String[] _Date) {
>>>>>>> better
		mInflater = LayoutInflater.from(context);
		this.Latitude = _Latitude;
		this.Longitude = _Longitude;
		this.Date = _Date;
	}
<<<<<<< HEAD
	
	
	@Override
	public int getCount() {
		
=======

	@Override
	public int getCount() {

>>>>>>> better
		return Latitude.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
<<<<<<< HEAD
		
		if(convertView == null){
			convertView = mInflater.inflate(R.layout.row,null);
			holder = new ViewHolder();
			holder.latitude = (TextView) convertView.findViewById(R.id.latitude);
			holder.longitude = (TextView) convertView.findViewById(R.id.longitude);
			holder.date = (TextView) convertView.findViewById(R.id.date);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
			
		}
		
		holder.latitude.setText(String.valueOf(Latitude[position]));
		holder.longitude.setText(String.valueOf(Longitude[position]));
		holder.date.setText(Date[position]);
		
		int posColor = position % colors.length;
		convertView.setBackgroundColor(colors[posColor]);
		
		return convertView;
	}
	
	static class ViewHolder{
		TextView latitude;
		TextView longitude;
		TextView date;
		
=======

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.row, null);
			holder = new ViewHolder();
			holder.latitude = (TextView) convertView
					.findViewById(R.id.latitude);
			holder.longitude = (TextView) convertView
					.findViewById(R.id.longitude);
			holder.date = (TextView) convertView.findViewById(R.id.date);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();

		}

		holder.latitude.setText(String.valueOf(Latitude[position]));
		holder.longitude.setText(String.valueOf(Longitude[position]));
		holder.date.setText(Date[position]);

		int posColor = position % colors.length;
		convertView.setBackgroundColor(colors[posColor]);

		return convertView;
	}

	static class ViewHolder {
		TextView latitude;
		TextView longitude;
		TextView date;

>>>>>>> better
	}
}

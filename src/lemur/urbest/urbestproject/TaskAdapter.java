package lemur.urbest.urbestproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TaskAdapter extends BaseAdapter {

	private int[] colors = new int[] { 0xFF6600FF, 0xFFFFFFFF };
	private int[] textColors = new int[] { 0xFFFFFFFF, 0xFF6600FF };

	private LayoutInflater mInflater;
	String[] title;
	String[] description;
	int[] points;

	public TaskAdapter(Context context, String[] title, String[] description,
			int[] points) {

		this.mInflater = LayoutInflater.from(context);
		this.description = description;
		this.title = title;
		this.points = points;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return title.length;
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

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.task_row_schema, null);
			holder = new ViewHolder();
			holder.title = (TextView) convertView.findViewById(R.id.task_title);
			holder.description = (TextView) convertView
					.findViewById(R.id.short_description);
			holder.points = (TextView) convertView.findViewById(R.id.points);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();

		}

		holder.title.setText(String.valueOf(this.title[position]));
		holder.description.setText(String.valueOf(this.description[position]));
		holder.points.setText(Integer.toString(this.points[position]));

		int posColor = position % colors.length;
		holder.title.setTextColor(textColors[posColor]);
		holder.description.setTextColor(textColors[posColor]);
		holder.points.setTextColor(textColors[posColor]);
		TextView tw = (TextView) convertView.findViewById(R.id.description);
		tw.setTextColor(textColors[posColor]);
		tw = (TextView) convertView.findViewById(R.id.max_points_number);
		tw.setTextColor(textColors[posColor]);
		tw = (TextView) convertView.findViewById(R.id.task);
		tw.setTextColor(textColors[posColor]);
		convertView.setBackgroundColor(colors[posColor]);

		return convertView;
	}

	static class ViewHolder {
		TextView title;
		TextView description;
		TextView points;

	}
}

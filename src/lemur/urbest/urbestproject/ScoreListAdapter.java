package lemur.urbest.urbestproject;


import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ScoreListAdapter extends BaseAdapter{

	private int[] colors = new int[]{0xFF6600FF,0xFFFFFFFF};
	private int[] textColors = new int[]{0xFFFFFFFF,0xFF6600FF};
	private LayoutInflater mInflater;
	
	List<String> tasksList;
	List<String> scoresList;
	List<String> dateOfCompletionList;
	

	public ScoreListAdapter(Context context, List<String> tasksList, List<String> scoresList, List<String> dateOfCompletionList){
		mInflater = LayoutInflater.from(context);
		this.tasksList = tasksList;
		this.scoresList = scoresList;
		this.dateOfCompletionList = dateOfCompletionList;
	}
	
	@Override
	public int getCount() {
		return scoresList.size();
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
		
		if(convertView == null){
			convertView = mInflater.inflate(R.layout.score_list_schema,null);
			holder = new ViewHolder();
			holder.task = (TextView) convertView.findViewById(R.id.task_name);
			holder.score = (TextView) convertView.findViewById(R.id.score);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
			
		}
		
		holder.task.setText(String.valueOf(tasksList.get(position)));
		holder.score.setText(String.valueOf(scoresList.get(position)));
		
		int posColor = position % colors.length;
		convertView.setBackgroundColor(colors[posColor]);
		holder.task.setTextColor(textColors[posColor]);
		holder.score.setTextColor(textColors[posColor]);
		
		return convertView;
	}

	static class ViewHolder{
		TextView task;
		TextView score;
	}
}

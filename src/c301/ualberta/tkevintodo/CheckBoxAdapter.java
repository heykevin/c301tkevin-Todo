package c301.ualberta.tkevintodo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class CheckBoxAdapter extends ArrayAdapter<Todo> {

	private LayoutInflater inflated;
	static TodoList todoList = null;

	public CheckBoxAdapter(Context context, ArrayList<Todo> todoList) {
		super(context, R.layout.rowlayout, R.id.textView1, todoList);
		inflated = LayoutInflater.from(context);
	}
/*
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	        RowViewHolder rvh = null;
	        RowItemData rid = this.mListRowItems.get(position);
	        if (convertView == null){
	            convertView = LayoutInflater.from().inflate(R.layout.row_item, null);
	            TextView tvSomeText = (TextView)convertView.findViewById(R.id.textView1);
	            CheckBox cbSomeCheckBox = (CheckBox)convertView.findViewById(R.id.checkBox1);
	            rvh = new RowViewHolder();
	            rvh.mSomeTextView = tvSomeText;
	            rvh.mSomeCheckBox = cbSomeCheckBox;
	            convertView.setTag(rvh);
	        }else{
	            rvh = (RowViewHolder)convertView.getTag();
	        }
	        if (rvh != null){
	            rvh.mSomeTextView.setText(rid.getSomeText());
	            rvh.mSomeCheckBox.setChecked(rid.getCheckState());
	            rvh.mSomeCheckBox.setOnClickListener(rid.getOnClickListener());
	        }
	        return convertView;
	    }

	    static class RowViewHolder{
	        public TextView mSomeTextView;
	        public CheckBox mSomeCheckBox;
	    }
	}
		*/
		
		
		
		
		
		
		
		
	public View getView(int position, View view, ViewGroup parent) {

		
		Todo todo = (Todo) this.getItem(position);

		CheckBox checkBox;
		TextView textView;

		if (view == null) {
			view = inflated.inflate(R.layout.rowlayout, null);

			textView = (TextView) view.findViewById(R.id.textView1);
			checkBox = (CheckBox) view.findViewById(R.id.checkBox1);

			view.setTag(new TodoHolder(textView, checkBox));

			checkBox.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					CheckBox cb = (CheckBox) v;
					Todo todo = (Todo) cb.getTag();
					todo.setChecked(cb.isChecked());
				}
			});
		} else {
			TodoHolder todoHolder = (TodoHolder) view.getTag();
			checkBox = todoHolder.getCheckBox();
			textView = todoHolder.getTextView();
		}
		checkBox.setTag(todo);
		checkBox.setChecked(todo.isChecked());
		textView.setText(todo.getName());
		return view;

	}
	/*
	 * LayoutInflater inflater = ((Activity) context).getLayoutInflater(); view
	 * = inflater.inflate(R.layout.checkboxlayout, parent, false); TextView name
	 * = (TextView) view.findViewById(R.id.textView1); CheckBox cb = (CheckBox)
	 * view.findViewById(R.id.checkBox1);
	 * name.setText(todoList.getTodo(position).getName());
	 * 
	 * return view;
	 * 
	 * }
	 */
}

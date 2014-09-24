package c301.ualberta.tkevintodo;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class CheckBoxAdapter extends ArrayAdapter<Todo> {

	private LayoutInflater inflated;
	ArrayList<Todo> todoList = null;
	private SparseBooleanArray mSelectedItemsIds;
	Context context;

	public CheckBoxAdapter(Context context, int resourceID,
			ArrayList<Todo> todoList_) {
		super(context, resourceID, todoList_);
		todoList = todoList_;
		this.context = context;
		mSelectedItemsIds = new SparseBooleanArray();
		inflated = LayoutInflater.from(context);
	}

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
					notifyDataSetChanged();

				}
			});
		} else {
			TodoHolder todoHolder = (TodoHolder) view.getTag();
			checkBox = todoHolder.getCheckBox();
			textView = todoHolder.getTextView();
			notifyDataSetChanged();

		}
		checkBox.setTag(todo);
		checkBox.setChecked(todo.isChecked());
		textView.setText(todo.getName());
		notifyDataSetChanged();
		return view;

	}

	// selection functions
	public void removeSelection() {
		mSelectedItemsIds = new SparseBooleanArray();
		notifyDataSetChanged();

	}

	public void toggleSelection(int position) {
		selectView(position, !mSelectedItemsIds.get(position));
	}

	public void selectView(int position, boolean value) {
		if (value)
			mSelectedItemsIds.put(position, value);
		else
			mSelectedItemsIds.delete(position);
		notifyDataSetChanged();
	}

	public int getSelectedCount() {
		return mSelectedItemsIds.size();
	}

	public SparseBooleanArray getSelectedIds() {
		return mSelectedItemsIds;
	}

}

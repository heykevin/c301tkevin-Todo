package c301.ualberta.tkevintodo;

import java.util.ArrayList;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
//Custom adapter resoponsible for changing the default view in listview in main. Also responsible for 
//selection in the contextual action bar. Custom Adapter was adapted from SurvivingWithAndroid's customadapter turotial.
public class CheckBoxAdapter extends ArrayAdapter<Todo> {

	private LayoutInflater inflated;
	ArrayList<Todo> todoList = null;
	private SparseBooleanArray selectedItems;
	Context context;

	public CheckBoxAdapter(Context context, int resourceID,
			ArrayList<Todo> todoList_) {
		super(context, resourceID, todoList_);
		todoList = todoList_;
		this.context = context;
		selectedItems = new SparseBooleanArray();
		inflated = LayoutInflater.from(context);
	}

	public View getView(int position, View view, ViewGroup parent) {

		Todo todo = (Todo) this.getItem(position);

		CheckBox checkBox;
		TextView textView;

		if (view == null) {
			//inflating view
			view = inflated.inflate(R.layout.rowlayout, null);
			//checking textviews/checkbox in layouts
			textView = (TextView) view.findViewById(R.id.totalSummaryHeader);
			checkBox = (CheckBox) view.findViewById(R.id.checkBox1);
			view.setTag(new TodoHolder(textView, checkBox));
			//update when clicked on - change checbox
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
		//setting positions to textviewand checkbox
		checkBox.setTag(todo);
		checkBox.setChecked(todo.isChecked());
		textView.setText(todo.getName());
		return view;

	}

	// selection functions. Boolean array to keep track of item positions from list.
	public void removeSelection() {
		selectedItems = new SparseBooleanArray();
		notifyDataSetChanged();

	}

	public void toggleSelection(int position) {
		selectView(position, !selectedItems.get(position));
	}

	public void selectView(int position, boolean value) {
		if (value)
			selectedItems.put(position, value);
		else
			selectedItems.delete(position);
		notifyDataSetChanged();
	}

	public int getSelectedCount() {
		return selectedItems.size();
	}

	public SparseBooleanArray getSelected() {
		return selectedItems;
	}

}

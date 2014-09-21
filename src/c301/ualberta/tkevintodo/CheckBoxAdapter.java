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
		super(context, R.layout.checkboxlayout, R.id.textView1, todoList);
		inflated = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		view = inflater.inflate(R.layout.checkboxlayout, parent, false);
		TextView name = (TextView) view.findViewById(R.id.textView1);
		CheckBox cb = (CheckBox) view.findViewById(R.id.checkBox1);
		name.setText(todoList.getTodo(position).getName());

		return view;

	}
}

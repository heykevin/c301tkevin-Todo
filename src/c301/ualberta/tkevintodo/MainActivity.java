package c301.ualberta.tkevintodo;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AbsListView.MultiChoiceModeListener;

public class MainActivity extends Activity {
	ListView lv;
	// test case
	// Todo cat = new Todo("Bananas");
	CheckBoxAdapter todoAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TodoListManager.initManager(this);
		// On create - display the todos currently in the filed
		lv = (ListView) findViewById(R.id.todolistview);
		// TodoListController.getTodoList().addTodo(cat);
		Collection<Todo> todos = TodoListController.getTodoList()
				.getNormalList();
		final ArrayList<Todo> list = new ArrayList<Todo>(todos);
		final CheckBoxAdapter todoAdapter = new CheckBoxAdapter(this,
				R.layout.activity_main, list);
		lv.setAdapter(todoAdapter);
		lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		lv.setMultiChoiceModeListener(new MultiChoiceModeListener() {

			// call action mode when state changed. Selection info in adapter.
			@Override
			public void onItemCheckedStateChanged(ActionMode mode,
					int position, long id, boolean bool) {
				todoAdapter.toggleSelection(position);
			}

			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				SparseBooleanArray selected = todoAdapter.getSelected();
				switch (item.getItemId()) {
				case R.id.delete:
					for (int i = (selected.size() - 1); i >= 0; --i) {
						if (selected.valueAt(i)) {
							Todo selecteditem = todoAdapter.getItem(selected
									.keyAt(i));
							TodoListController.getTodoList().deleteTodo(
									selecteditem);
						}
					}
					mode.finish();
					return true;

				case R.id.archiveCAB:
					for (int i = (selected.size() - 1); i >= 0; --i) {
						if (selected.valueAt(i)) {
							Todo selecteditem = todoAdapter.getItem(selected
									.keyAt(i));
							TodoListController.getTodoList().archiveTodo(
									selecteditem);
						}
					}
					mode.finish();
					return true;
				case R.id.emailCAB:
					ArrayList<Todo> emailList = new ArrayList<Todo>();
					for (int i = (selected.size() - 1); i >= 0; --i) {
						if (selected.valueAt(i)) {
							Todo selecteditem = todoAdapter.getItem(selected
									.keyAt(i));
							emailList.add(selecteditem);
						}
					}
					Intent i = new Intent(Intent.ACTION_SEND);
					i.setType("message/rfc822");
					i.putExtra(Intent.EXTRA_EMAIL,
							new String[] { "recipient@example.com" });
					i.putExtra(Intent.EXTRA_SUBJECT, "My Todo List");
					i.putExtra(Intent.EXTRA_TEXT, emailBuilder(emailList));
					try {
						startActivity(Intent.createChooser(i, "Send mail..."));
					} catch (android.content.ActivityNotFoundException ex) {
						Toast.makeText(MainActivity.this,
								"There are no email clients installed.",
								Toast.LENGTH_SHORT).show();
					}

					mode.finish();
					return true;
				default:
					return false;
				}
			}

			@Override
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				mode.getMenuInflater().inflate(R.menu.activity_main, menu);
				return true;
			}

			@Override
			public void onDestroyActionMode(ActionMode mode) {
				todoAdapter.removeSelection();

			}

			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				// TODO Auto-generated method stub
				return false;
			}

		});

		// Create listeners so that list will update
		TodoListController.getTodoList().addListener(new Listener() {

			@Override
			public void update() {
				list.clear();
				Collection<Todo> todos = TodoListController.getTodoList()
						.getList();
				list.addAll(todos);
				todoAdapter.notifyDataSetChanged();
			}
		});

	}

	/*
	 * @Override public void onResume(){ //I unno how this works
	 * super.onResume(); final ArrayList<Todo> list =
	 * TodoListController.getTodoList().getList(); todoAdapter = new
	 * CheckBoxAdapter(this,R.layout.activity_main, list); lv = (ListView)
	 * findViewById(R.id.todolistview); lv.setAdapter(todoAdapter);
	 * TodoListController.getTodoList().addListener(new Listener() {
	 * 
	 * @Override public void update() { list.clear(); Collection<Todo> todos =
	 * TodoListController.getTodoList() .getList(); list.addAll(todos);
	 * todoAdapter.notifyDataSetChanged(); } });
	 * 
	 * 
	 * 
	 * }
	 */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	//email all todos
	public void emailAllTodoMenu(MenuItem menu) {
		Toast.makeText(this, "Emailing All Todos", Toast.LENGTH_SHORT).show();
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("message/rfc822");
		i.putExtra(Intent.EXTRA_EMAIL,
				new String[] { "recipient@example.com" });
		i.putExtra(Intent.EXTRA_SUBJECT, "My Todo List");
		i.putExtra(Intent.EXTRA_TEXT, emailBuilder(TodoListController.getTodoList().getList()));
		try {
			startActivity(Intent.createChooser(i, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(MainActivity.this,
					"There are no email clients installed.",
					Toast.LENGTH_SHORT).show();
		}
	}
	
	//Email all unarchived todos
	
	public void emailNormalTodoMenu(MenuItem menu) {
		Toast.makeText(this, "Emailing All Todos", Toast.LENGTH_SHORT).show();
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("message/rfc822");
		i.putExtra(Intent.EXTRA_EMAIL,
				new String[] { "recipient@example.com" });
		i.putExtra(Intent.EXTRA_SUBJECT, "My Todo List");
		i.putExtra(Intent.EXTRA_TEXT, emailBuilder(TodoListController.getTodoList().getNormalList()));
		try {
			startActivity(Intent.createChooser(i, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(MainActivity.this,
					"There are no email clients installed.",
					Toast.LENGTH_SHORT).show();
		}
	}
	
	// creates a legible list of todos
	public String emailBuilder(ArrayList<Todo> todos) {
		String str = "";
		for (int i = 0; i < todos.size(); ++i) {
			str += todos.get(i).toString() + "\n";
		}
		return str;
	}

	public void archiveActivity(MenuItem menu) {
		Toast.makeText(this, "The Archives", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(MainActivity.this, ArchiveActivity.class);
		startActivity(intent);

	}

	public void summaryMenu(MenuItem menu) {
		Toast.makeText(this, "Summaries of Todo", Toast.LENGTH_SHORT).show();
	}

	public void addATodo(View v) {

		Toast.makeText(this, "Added Todo", Toast.LENGTH_SHORT).show();
		TodoListController tc = new TodoListController();
		EditText textView = (EditText) findViewById(R.id.todoentrytext);
		tc.addTodo(new Todo(textView.getText().toString()));

	}

}

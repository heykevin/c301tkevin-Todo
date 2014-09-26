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
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AbsListView.MultiChoiceModeListener;

public class ArchiveActivity extends Activity {
	ListView lv;
	CheckBoxAdapter todoAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_archive);
		TodoListManager.initManager(this);
		// Creating a listview from the list in todolistcontroller
		lv = (ListView) findViewById(R.id.archiveListView);
		Collection<Todo> todos = TodoListController.getTodoList().getAList();
		final ArrayList<Todo> list = new ArrayList<Todo>(todos);
		final CheckBoxAdapter todoAdapter = new CheckBoxAdapter(this,
				R.layout.activity_archive, list);
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
					deleteArchive(selected, todoAdapter);
					mode.finish();
					return true;

				case R.id.unarchiveCAB:
					unarchiveArchive(selected, todoAdapter);
					mode.finish();
					return true;
				case R.id.emailCAB:
					emailArchive(selected, todoAdapter);
					mode.finish();
					return true;
				default:
					return false;
				}
			}

			@Override
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				mode.getMenuInflater().inflate(R.menu.activity_archive, menu);
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

		TodoListController.getTodoList().addListener(new Listener() {
			@Override
			public void update() {
				list.clear();
				Collection<Todo> todos = TodoListController.getTodoList()
						.getAList();
				list.addAll(todos);
				todoAdapter.notifyDataSetChanged();
			}
		});
	}

	// Functions for CAB. Iterate through selected values, get key, and apply
	// intended actions.
	protected void deleteArchive(SparseBooleanArray selected,
			CheckBoxAdapter todoAdapter) {
		for (int i = (selected.size() - 1); i >= 0; --i) {
			if (selected.valueAt(i)) {
				Todo selecteditem = todoAdapter.getItem(selected.keyAt(i));
				TodoListController.getTodoList().deleteTodo(selecteditem);
			}
		}
	}

	protected void unarchiveArchive(SparseBooleanArray selected,
			CheckBoxAdapter todoAdapter) {
		for (int i = (selected.size() - 1); i >= 0; --i) {
			if (selected.valueAt(i)) {
				Todo selecteditem = todoAdapter.getItem(selected.keyAt(i));
				TodoListController.getTodoList().unarchiveTodo(selecteditem);
			}
		}
	}

	protected void emailArchive(SparseBooleanArray selected,
			CheckBoxAdapter todoAdapter) {
		ArrayList<Todo> emailList = new ArrayList<Todo>();
		for (int i = (selected.size() - 1); i >= 0; --i) {
			if (selected.valueAt(i)) {
				Todo selecteditem = todoAdapter.getItem(selected.keyAt(i));
				emailList.add(selecteditem);
			}
		}
		sendEmail(emailList);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.archive, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// creates a legible list of todos
	public String emailBuilder(ArrayList<Todo> todos) {
		String str = "";
		for (int i = 0; i < todos.size(); ++i) {
			str += todos.get(i).toString() + "\n";
		}
		return str;
	}

	/*
	 * public void backToMain(MenuItem menu) { Toast.makeText(this,
	 * "Welcome Back", Toast.LENGTH_SHORT).show(); Intent intent = new
	 * Intent(ArchiveActivity.this, MainActivity.class); startActivity(intent);
	 * 
	 * }
	 */

	// email functions from main activity.
	public void emailArchivedTodoMenu(MenuItem menu) {
		Toast.makeText(this, "Emailing All Archived Todos", Toast.LENGTH_SHORT)
				.show();
		sendEmail(TodoListController.getTodoList().getAList());
	}

	public void sendEmail(ArrayList<Todo> emailList) {
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("message/rfc822");
		i.putExtra(Intent.EXTRA_EMAIL, new String[] { "recipient@example.com" });
		i.putExtra(Intent.EXTRA_SUBJECT, "My Todo List");
		i.putExtra(Intent.EXTRA_TEXT, emailBuilder(emailList));
		try {
			startActivity(Intent.createChooser(i, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(ArchiveActivity.this,
					"There are no email clients installed.", Toast.LENGTH_SHORT)
					.show();
		}
	}
}

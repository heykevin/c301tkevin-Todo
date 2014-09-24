package c301.ualberta.tkevintodo;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class ArchiveActivity extends Activity {
	ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_archive);
		
		//Creating a listview from the list in todolistcontroller
		lv = (ListView) findViewById(R.id.archiveListView);
		Collection<Todo> todos = TodoListController.getTodoList().getAList();
		final ArrayList<Todo> list = new ArrayList<Todo>(todos);
		final CheckBoxAdapter todoAdapter = new CheckBoxAdapter(this,R.layout.activity_archive , list);
		lv.setAdapter(todoAdapter);
		
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
	
	public void backToMain(MenuItem menu) {
		Toast.makeText(this, "Welcome Back", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(ArchiveActivity.this, MainActivity.class);
		startActivity(intent);

	}
}

package c301.ualberta.tkevintodo;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ListView lv;
	TodoList todoList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//On create - display the todos currently in the filed
		lv = (ListView) findViewById(R.id.todolistview);
		Collection<Todo> todos = TodoListController.getTodoList().getList();
		final ArrayList<Todo> list = new ArrayList<Todo>(todos);
		final ArrayAdapter<Todo> todoAdapter = new ArrayAdapter<Todo>(this, android.R.layout.simple_list_item_1  , list);
		lv.setAdapter(todoAdapter);
		
		//Create listeners so that list will update
		TodoListController.getTodoList().addListener(new Listener(){
			@Override
			public void update(){
				list.clear();
				Collection<Todo> todos = TodoListController.getTodoList().getList();
				list.addAll(todos);
				todoAdapter.notifyDataSetChanged();
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	public void deleteTodoMenu(MenuItem menu) {
		Toast.makeText(this, "Deleted Todo", Toast.LENGTH_SHORT).show();
	}

	public void emailTodoMenu(MenuItem menu) {
		Toast.makeText(this, "Emailed Todo", Toast.LENGTH_SHORT).show();
	}

	public void archiveTodoMenu(MenuItem menu) {
		Toast.makeText(this, "Archived Todo", Toast.LENGTH_SHORT).show();
	}

	public void addATodo(View v) {
		
		Toast.makeText(this, "Added Todot", Toast.LENGTH_SHORT).show();
		TodoListController tc = new TodoListController();
		EditText textView = (EditText) findViewById(R.id.todoentrytext);
		tc.addTodo(new Todo(textView.getText().toString()));

	}

}

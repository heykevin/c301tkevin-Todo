package c301.ualberta.tkevintodo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;
//Todolist manager that allows storage of the todolist. Class style was followed by Abram Hindle's student picker tutorials
public class TodoListManager {
	// name of file, name of object
	static final String prefFile = "TodoList";
	static final String tlKey = "todoList";

	Context context;

	static private TodoListManager todoListManager = null;

	public TodoListManager(Context context) {
		this.context = context;
	}

	public static void initManager(Context context) {
		if (todoListManager == null) {
			if (context == null) {
				throw new RuntimeException(
						"missing context for TodoListManager");
			}
			todoListManager = new TodoListManager(context);
		}
	}
	
	public static TodoListManager getManager(){
		if (todoListManager == null){
			throw new RuntimeException("Did not initialize Manager");
		}
		return todoListManager;
	}

	public TodoList loadTodoList() throws StreamCorruptedException, IOException, ClassNotFoundException {
		SharedPreferences settings = context.getSharedPreferences(prefFile,
				Context.MODE_PRIVATE);
		String todoListData = settings.getString(tlKey, "");
		if (todoListData.equals("")) {
			return new TodoList();

		} else {
			return todoListFromString(todoListData);
		}
	}

	public static TodoList todoListFromString(String todoListData) throws StreamCorruptedException, IOException, ClassNotFoundException {
		ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(todoListData, Base64.DEFAULT));
		ObjectInputStream oi = new ObjectInputStream(bi);
		return(TodoList)oi.readObject();
	}
	
	public static String todoListToString(TodoList tl) throws IOException{
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(tl);
		oo.close();
		byte bytes[] = bo.toByteArray();
		return Base64.encodeToString(bytes, Base64.DEFAULT);
	}
	
	public void saveTodoList(TodoList tl) throws IOException{
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString(tlKey, todoListToString(tl));
		editor.commit();
	}
}
//hi
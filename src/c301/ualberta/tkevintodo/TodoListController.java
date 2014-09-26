package c301.ualberta.tkevintodo;

import java.io.IOException;
import java.io.StreamCorruptedException;

public class TodoListController {

	/*
	 * static public TodoList getTodoList() { if (todoList == null) { todoList =
	 * new TodoList(); } return todoList; }
	 */
	private static TodoList todoList = null;
	static public TodoList getTodoList() {
		if (todoList == null) {
			try {
				todoList = TodoListManager.getManager().loadTodoList();
				todoList.addListener(new Listener() {
					
					@Override
					public void update() {
						saveTodoList();
						
					}
				});
				
			} catch (StreamCorruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return todoList;
	}

	static public void saveTodoList() {
		try {
			TodoListManager.getManager().saveTodoList(todoList);
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addTodo(Todo todo) {
		getTodoList().addTodo(todo);
	}

	public void delTodo(Todo todo) {
		getTodoList().deleteTodo(todo);

	}

	public void arcTodo(Todo todo) {
		getTodoList().archiveTodo(todo);

	}

	/*
	 * public void selectionArchive(TodoList list, Context context) { TodoList
	 * todolist; todolist = getTodoList();
	 * 
	 * for (int i = 0; i < list.size(); i++) {
	 * todolist.archiveTodo(list.getPos(i)); }
	 * 
	 * }
	 */
}

package c301.ualberta.tkevintodo;

import android.content.Context;


public class TodoListController {
	private static TodoList todoList = null;

	static public TodoList getTodoList() {
		if (todoList == null) {
			todoList = new TodoList();
		}
		return todoList;
	}

	public void addTodo(Todo todo) {
		getTodoList().addTodo(todo);
	}

	public void delTodo(Todo todo) {
		getTodoList().deleteTodo(todo);

	}
public void arcTodo(Todo todo){
	getTodoList().archiveTodo(todo);

	}


	public void selectionArchive(TodoList list, Context context) {
		TodoList todolist;
		todolist = getTodoList();

		for (int i = 0; i < list.size(); i++) {
			todolist.archiveTodo(list.getPos(i));
		}

	}
}


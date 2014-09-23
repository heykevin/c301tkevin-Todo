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


	public TodoList getSelected() {
		TodoList olist;
		TodoList list = new TodoList();
		olist = getTodoList();
		for (int i = 0; i < olist.size(); i++) {
			if (olist.getPos(i).isChecked()) {
				list.addTodo(olist.getPos(i));
			}
		}
		return list;

	}
	public TodoList getSelected2() {
		TodoList olist;
		TodoList list = new TodoList();
		olist = getTodoList();
		for (int i = 0; i < olist.size(); i++) {
			if (olist.getPos(i).isSelected()) {
				list.addTodo(olist.getPos(i));
			}
		}
		return list;

	}
	// deletes todos from a list
	public void selectionDelete(TodoList list) {
		TodoList todolist;
		todolist = getTodoList();
		for (int i = 0; i < list.size(); i++) {
			todolist.deleteTodo(list.getPos(i));
		}
	}

	public void selectionArchive(TodoList list, Context context) {
		TodoList todolist;
		todolist = getTodoList();

		for (int i = 0; i < list.size(); i++) {
			todolist.archiveTodo(list.getPos(i), context);
		}

	}
}

package c301.ualberta.tkevintodo;

import java.util.ArrayList;
import java.util.Collection;

public class TodoList {
	protected ArrayList<ToDo> todoList;

	// Basic addition/removal of list of todos and size
	// Add bool indicating archive status?
	public TodoList() {
		todoList = new ArrayList<ToDo>();
	}

	public Collection<ToDo> getList() {
		return todoList;
	}

	public void addTodo(ToDo newTodo) {
		todoList.add(newTodo);
	}

	public void deleteTodo(ToDo delTodo) {
		todoList.remove(delTodo);
	}

	public int size() {
		return todoList.size();
	}
}

package c301.ualberta.tkevintodo;

import java.io.Serializable;
import java.util.ArrayList;
//Todlist class that holds todolist. Includes functions for retireving list, and modifying list. Serializable for storage.
public class TodoList implements Serializable {

	private static final long serialVersionUID = -3275705092903792284L;
	protected ArrayList<Todo> todoList = null;
	protected transient ArrayList<Listener> listeners = null;

	// Basic addition/removal of list of todos and size
	// Add bool indicating archive status?
	public TodoList() {
		todoList = new ArrayList<Todo>();
		listeners = new ArrayList<Listener>();

	}

	private ArrayList<Listener> getListeners() {
		if (listeners == null) {
			listeners = new ArrayList<Listener>();
		}
		return listeners;
	}

	public ArrayList<Todo> getList() {
		return todoList;
	}
	public ArrayList<Todo> getNormalList() {
		ArrayList<Todo> normalTodos = new ArrayList<Todo>();
		for (int i = 0; i < todoList.size(); ++i) {
			if (!todoList.get(i).isArchive()) {
				normalTodos.add(todoList.get(i));
			}

		}
		return normalTodos;
	}

	
	public ArrayList<Todo> getAList() {
		ArrayList<Todo> archivedTodos = new ArrayList<Todo>();
		for (int i = 0; i < todoList.size(); ++i) {
			if (todoList.get(i).isArchive()) {
				archivedTodos.add(todoList.get(i));
			}

		}
		return archivedTodos;
	}

	public void addTodo(Todo newTodo) {
		todoList.add(newTodo);
		notifyListeners();

	}

	public void archiveTodo(Todo newTodo) {
		todoList.get(todoList.lastIndexOf(newTodo)).setArchive();
		notifyListeners();

	}

	private void notifyListeners() {
		for (Listener listener : getListeners()) {
			listener.update();
		}
	}

	public void addListener(Listener L) {
		getListeners().add(L);
	}

	public void removeListener(Listener l) {
		getListeners().remove(l);
	}

	public void deleteTodo(Todo delTodo) {
		todoList.remove(delTodo);
		notifyListeners();

	}

	public int size() {
		return todoList.size();
	}

	public Todo getPos(int position) {
		return todoList.get(position);
	}

	public boolean contains(Todo testTodo) {
		return todoList.contains(testTodo);
	}

}

package c301.ualberta.tkevintodo;

import java.util.ArrayList;


import android.content.Context;


public class TodoList {
	protected ArrayList<Todo> todoList;
	protected ArrayList<Listener> listeners;

	// Basic addition/removal of list of todos and size
	// Add bool indicating archive status?
	public TodoList() {
		todoList = new ArrayList<Todo>();
		listeners = new ArrayList<Listener>();

	}

	public ArrayList<Todo> getList() {
		return todoList;
	}
	
	public ArrayList<Todo> getAList(){
		ArrayList<Todo> archivedTodos = new ArrayList<Todo>();
		for(int i =0; i< todoList.size(); ++i){
			if(todoList.get(i).isArchive()){
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
		for (Listener listener : listeners) {
			listener.update();
		}
	}

	public void addListener(Listener L) {
		listeners.add(L);
	}

	public void removeListener(Listener l) {
		listeners.remove(l);
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
}

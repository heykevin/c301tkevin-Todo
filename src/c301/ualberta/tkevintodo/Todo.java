package c301.ualberta.tkevintodo;

import java.io.Serializable;

//Todo class. Consists of name setters and getters. Variables for archive and for selection.
public class Todo implements Serializable {

	private static final long serialVersionUID = 49136507405809519L;
	protected String todoName;
	private Boolean archive = false;
	private Boolean check = false;

	public Todo(String todoName) {
		this.todoName = todoName;
	}

	public void Archive(Boolean todoArchive) {
		this.archive = todoArchive;
	}

	public String getName() {
		return this.todoName;
	}

	public void setArchive() {
		archive = true;
	}

	public void remArchive() {
		archive = false;
	}

	public boolean isArchive() {
		return archive;

	}

	public boolean isChecked() {
		return check;
	}

	public void setChecked(boolean check) {
		this.check = check;
	}

	public void toggleChecked() {
		check = !check;
	}

	public boolean equals(Object compareTodo) {

		if (compareTodo != null && compareTodo.getClass() == this.getClass()) {
			return this.equals((Todo) compareTodo);
		} else {
			return false;
		}

	}

	public boolean equals(Todo compareTodo) {
		if (compareTodo == null) {
			return false;
		}
		return getName().equals(compareTodo.getName());
	}

}

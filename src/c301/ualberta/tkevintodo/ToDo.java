package c301.ualberta.tkevintodo;

//Todo class
public class Todo {
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

	public Boolean isArchive() {
		return archive;

	}

	public String toString() {
		return getName();
	}

	public boolean isChecked() {
		return check;
	}

	public void setChecked(boolean check) {
		this.check = check;
	}
    public void toggleChecked() {
        check = !check ;
      }
}

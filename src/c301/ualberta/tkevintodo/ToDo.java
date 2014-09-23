package c301.ualberta.tkevintodo;

//Todo class
public class Todo {
	protected String todoName;
	private Boolean archive = false;
	private Boolean check = false;
	private Boolean select = false;

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

	public boolean isArchive() {
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

	public void toggleSelect() {
		select = !select;
		
	}

	public boolean isSelected() {
		// TODO Auto-generated method stub
		return select;
	}
}

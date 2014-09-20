package c301.ualberta.tkevintodo;
//Todo class
public class ToDo {
	protected String todoName;

	public void Todo(String todoName) {
		this.todoName = todoName;
	}

	public String getName() {
		return this.todoName;
	}
}

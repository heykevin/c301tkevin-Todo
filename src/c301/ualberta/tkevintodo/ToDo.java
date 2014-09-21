package c301.ualberta.tkevintodo;
//Todo class
public class Todo {
	protected String todoName;
	public Boolean archive;
	
	public Todo(String todoName) {
		this.todoName = todoName;
	}
	public void Archive(Boolean todoArchive){
		this.archive = todoArchive;
	}

	public String getName() {
		return this.todoName;
	}
	
	public void setValue(){
		
	}
	
	public Boolean getValue(){
		return this.archive;
		
	}
	public String toString(){
		return getName();
	}

}

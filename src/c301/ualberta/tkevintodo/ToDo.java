package c301.ualberta.tkevintodo;
//Todo class
public class ToDo {
	protected String todoName;
	public Boolean archive;
	
	public void Todo(String todoName) {
		this.todoName = todoName;
	}
	public void Archive(Boolean todoArchive){
		this.archive = todoArchive;
	}

	public String getName() {
		return this.todoName;
	}
	
	public Boolean getStatus(){
		return this.archive;
		
	}
}

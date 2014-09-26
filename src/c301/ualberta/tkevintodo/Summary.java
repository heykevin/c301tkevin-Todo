package c301.ualberta.tkevintodo;

//Summary class containing info on counts.
import java.io.Serializable;

public class Summary implements Serializable{

	private static final long serialVersionUID = 5848686107882970627L;
	private int total;
	private int checked;
	private int unchecked;
	private int archived;
	private int archived_c;
	private int archived_u;
	
	
	
	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getChecked() {
		return this.checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}

	public int getUnchecked() {
		return this.unchecked;
	}

	public void setUnchecked(int unchecked) {
		this.unchecked = unchecked;
	}

	public int getArchived() {
		return this.archived;
	}

	public void setArchived(int archived) {
		this.archived = archived;
	}

	public int getArchived_c() {
		return archived_c;
	}

	public void setArchived_c(int archived_c) {
		this.archived_c = archived_c;
	}

	public int getArchived_u() {
		return archived_u;
	}

	public void setArchived_u(int archived_u) {
		this.archived_u = archived_u;
	}
}

package c301.ualberta.tkevintodo;
//summary activity displaying stats.
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SummaryActivity extends Activity {
	Summary mySummary = new Summary();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary);
		createSummary();
		setTotal(mySummary);
		setArchive(mySummary);

	}

	public void setTotal(Summary mySummary) {
		TextView total = (TextView) findViewById(R.id.totalcounttext);
		total.setText("Total number of todos: " + mySummary.getTotal());
		TextView total_unchecked = (TextView) findViewById(R.id.totalunchecktext);
		total_unchecked.setText("Total number of unchecked todos: "
				+ mySummary.getUnchecked());
		TextView total_checked = (TextView) findViewById(R.id.totalchecktext);
		total_checked.setText("Total number of checked todos: "
				+ mySummary.getChecked());

	}

	public void setArchive(Summary mySummary) {
		TextView total = (TextView) findViewById(R.id.totalarchivedtext);
		total.setText("Total number of archived todos: " + mySummary.getArchived());
		TextView total_unchecked = (TextView) findViewById(R.id.totalarchivedunchecked);
		total_unchecked.setText("Total number of unchecked todos: "
				+ mySummary.getArchived_u());
		TextView total_checked = (TextView) findViewById(R.id.totalarchivedcheckedtext);
		total_checked.setText("Total number of checked todos: "
				+ mySummary.getArchived_c());
	}

	public void createSummary() {
		// Totals
		int checkCount = 0;
		mySummary.setTotal(TodoListController.getTodoList().size());
		for (int i = 0; i < TodoListController.getTodoList().size(); i++) {
			if (TodoListController.getTodoList().getPos(i).isChecked()) {
				checkCount++;
			}
		}
		mySummary.setChecked(checkCount);
		mySummary.setUnchecked(TodoListController.getTodoList().size()
				- checkCount);

		// Archive totals
		mySummary.setArchived(TodoListController.getTodoList().getAList()
				.size());
		int checkArchive = 0;
		for (int i = 0; i < TodoListController.getTodoList().getAList().size(); i++) {
			if (TodoListController.getTodoList().getPos(i).isChecked()) {
				checkArchive++;
			}
		}
		mySummary.setArchived_c(checkArchive);
		mySummary.setArchived_u(TodoListController.getTodoList().getAList()
				.size()
				- checkArchive);

	}

}

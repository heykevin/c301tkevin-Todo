package c301.ualberta.tkevintodo;
//holds format of row
import android.widget.CheckBox;
import android.widget.TextView;

public class TodoHolder {
    private CheckBox checkBox ;
    private TextView textView ;
    private Boolean select;
    public TodoHolder() {}
    public TodoHolder( TextView textView, CheckBox checkBox ) {
      this.checkBox = checkBox ;
      this.textView = textView ;
    }
    public CheckBox getCheckBox() {
      return checkBox;
    }
    public void setCheckBox(CheckBox checkBox) {
      this.checkBox = checkBox;
    }
    public TextView getTextView() {
      return textView;
    }
    public void setTextView(TextView textView) {
      this.textView = textView;
    }
	public Object getSelect() {
		return select;
	}   
}

package Models;

public class StartAssayModel {

	public String DateTime;
	public String InstrumentID;
	public int UserID;
	public String CartridgeID;
	public String RunID;
	public String PathogenName;
	public String testCaseTitle;
	
	public StartAssayModel (String _DateTime, String _InstrumentID, int _UserID, String _CartridgeID, String _RunID, String _PathogenName, String _testCaseTitle) {
		
		this.DateTime = _DateTime;
		this.InstrumentID = _InstrumentID;
		this.UserID = _UserID;
		this.CartridgeID = _CartridgeID;
		this.RunID = _RunID;
		this.PathogenName = _PathogenName;
		this.testCaseTitle = _testCaseTitle;
	}
	
}

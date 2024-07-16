package Models;

import java.util.ArrayList;

public class ReportFilters {
	public String FilterName;
	public String FilterXPath;
	public String FilterID;
	public String FilterSort;
	public String FilterApply;
	public String FilterClear;
	public String FilterListXPathSearch;
	public String SearchVlaue;
	public String FilterListXPathPrefix;
	public String FilterListXPathSuffix;
	public String FilterListXPathChkSuffix;
	public String ClearInput;
	public String ColumnID;
	public String getRowValue;
	public String rowValueExpected;
	public String checkboxNumber;
	public int wait;
	
	public String toMonth;
	public String fromMonth;
	public String toDate;
	public String fromDate;
	public String alertMessage;
	
	public String count;
	public String paginationPage;
	public String paginationCount;
	
	public ArrayList<String> LstFilterValues;
	public ArrayList<String> LstFilterXpath;
	public ArrayList<String> LstFilterSearch;
	public ArrayList<String> LstSampleID;
	public ArrayList<String> LstValues;
	
	public String MinMean;
	public String MaxMean;
	public String MinStd;
	public String MaxStd;
	
	public ArrayList<String> LstColumnValues;
	public ArrayList<Integer> LstColumnID;
	public ArrayList<Integer> LstRowID;
}

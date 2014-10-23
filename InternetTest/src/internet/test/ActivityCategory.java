package internet.test;


public class ActivityCategory {
	private int _activityCategoryID = 0;
	private String _activityCategoryName = "";
	private String _activityImageName = "";
	private int _vercion = 0;
	private int _isNeedUpdate = 0;
	
	public int GetIsNeedUpdate() {
		return _isNeedUpdate;
	}
	
	public void SetIsNeedUpdate(int id) {
		_isNeedUpdate = id;
	}
	
	public int GetVercion() {
		return _vercion;
	}
	
	public void SetVercion(int id) {
		_vercion = id;
	}
	
	public int GetActivityCategoryID() {
		return _activityCategoryID;
	}
	
	public void SetActivityCategoryID(int id) {
		_activityCategoryID = id;
	}
	
	public String GetActivityCategoryName() {
		return _activityCategoryName;
	}
	
	public void SetActivityCategoryName(String actCategoryName) {
		_activityCategoryName = actCategoryName;
	}
	
	public String GetActivityImageName() {
		return _activityImageName;
	}
	
	public void SetActivityImageName(String imageName) {
		_activityImageName = imageName;
	}

}

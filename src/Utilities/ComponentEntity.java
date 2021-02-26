package Utilities;

import org.apache.james.mime4j.field.datetime.DateTime;

public class ComponentEntity
{
    public int Id;
    public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public DateTime getCreated() {
		return Created;
	}
	public void setCreated(DateTime created) {
		Created = created;
	}
	public DateTime getModified() {
		return Modified;
	}
	public void setModified(DateTime modified) {
		Modified = modified;
	}
	public String Name;
    public DateTime Created;
    public DateTime Modified;
    
    
    public static String Empty(String str) {
        return str == null ? "" : str;
    }

    public static boolean isNullOrEmpty(String str) {
    	if (Empty(str) == null || str.trim().length() == 0) {
            return true;
        }
        return false;
    }
    
}
package app.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.drawable.Drawable;
import android.provider.ContactsContract.CommonDataKinds.Nickname;


/**
 * ��ϵ�˵�ģ��
 * @author W.Z.L
 *
 */
public class ContactModel extends BaseModel{
    
	private String id;  //id

    private String lookupKey; //�鿴Ȩ��

    private String username; //�û���
    
    private String gender; //�Ա�
    
    private String nickName; //�ǳ�
    
    private String signature; //ǩ��
 
    private String imgUrl; //ͷ���ַ
   
    private String phoneNum; //�绰����
    
    private Drawable img; //ͷ��

    private boolean checked;

    private String flag;
    
    private static final String ID = "id";
    private static final String LOOKUPKEY = "lookupKey";
	private static final String USERNAME = "username";
	private static final String GENDER = "gender";
	private static final String NICKNAME = "nickName";
	private static final String SIGNATURE = "signature";
	private static final String IMGURL = "imgUrl";
	private static final String PHONENUM = "phonenum";

    public String getFlag()
    {
        return flag;
    }

    public void setFlag(String flag)
    {
        this.flag = flag;
    }

    public ContactModel()
    {

    }
    public ContactModel(String id, String lookupKey, String name, String phoneNum, String telNum, Drawable img,
            boolean checked)
    {
        this.id = id;
        this.lookupKey = lookupKey;
        this.username = name;
        this.phoneNum = phoneNum;
        this.img = img;
        this.checked = checked;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public String getNickname()
    {
        return username;
    }

    public void setNickname(String username)
    {
        this.username = username;
    }

    public String getPhoneNum()
    {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum)
    {
        this.phoneNum = phoneNum;
    }

    public Drawable getImg()
    {
        return img;
    }

    public void setImg(Drawable img)
    {
        this.img = img;
    }

    public boolean isChecked()
    {
        return checked;
    }

    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getLookupKey()
    {
        return lookupKey;
    }

    public void setLookupKey(String lookupKey)
    {
        this.lookupKey = lookupKey;
    }

    public static ContactModel parse(JSONObject object) throws JSONException {
    	ContactModel bean = new ContactModel();
		if (object.has(ID)) {
			bean.id = object.getString(ID);
		}
		if (object.has(USERNAME)) {
			bean.username = object.getString(USERNAME);
		}
		if (object.has(GENDER)) {
			bean.gender = object.getString(GENDER);
		}
		if (object.has(NICKNAME)) {
			bean.nickName = object.getString(NICKNAME);
		}
		if (object.has(IMGURL)) {
			bean.imgUrl = object.getString(IMGURL);
		}
		if (object.has(PHONENUM)) {
			bean.phoneNum = object.getString(PHONENUM);
		}
		if (object.has(SIGNATURE)) {
			bean.signature = object.getString(SIGNATURE);
		}
		return bean;
	}
}

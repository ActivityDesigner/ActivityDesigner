package app.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.baidu.lbsapi.auth.i;
import com.example.activitydesigner.R;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import app.adapter.SortAdapter;
import app.constant.NewActivityConst;
import app.model.ContactModel;
import app.model.SortModel;
import app.utils.CharacterParser;
import app.utils.ContactUtil;
import app.utils.PinyinComparator;
import app.view.widget.ClearEditText;
import app.view.widget.SideBar;
import app.view.widget.SideBar.OnTouchingLetterChangedListener;

/**
 * �������ѡ����ϵ��
 * @author W.Z.L
 *
 */
public class SelectContactActivity extends BaseActivity{

	List<ContactModel> models;
	private ImageView mBack;
	private ImageView mSure;
	private ListView mListView;
	private SideBar mSideBar;
	private TextView mCharName;
	private SortAdapter mAdapter;
	private ClearEditText mClearEditText;

	/**
	 * ����ת����ƴ������
	 */
	private CharacterParser characterParser;
	private List<SortModel> SourceDateList;
	
	/**
	 * ����ƴ��������ListView�����������
	 */
	private PinyinComparator pinyinComparator;
	
	Intent mIntent;
	
	@Override
	public void setView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_select_contact);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

		//ʵ��������תƴ����
		characterParser = CharacterParser.getInstance();
		
		pinyinComparator = new PinyinComparator();
		mBack = (ImageView)findViewById(R.id.select_contact_back);
		mBack.setOnClickListener(this);
		mSure = (ImageView)findViewById(R.id.select_contact_sure);
		mSure.setOnClickListener(this);
		mSideBar = (SideBar) findViewById(R.id.select_contact_sidebar);
		mCharName = (TextView) findViewById(R.id.select_contact_charname);
		mListView = (ListView) findViewById(R.id.select_contact_listview);
		mClearEditText = (ClearEditText) findViewById(R.id.select_contact_filter);
		mClearEditText.setFocusable(false);
		mClearEditText.setFocusableInTouchMode(true);
		mSideBar.setTextView(mCharName);
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		//�����Ҳഥ������
	  mSideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {
			
		@Override
		public void onTouchingLetterChanged(String s) {
		 //����ĸ�״γ��ֵ�λ��
		 int position = mAdapter.getPositionForSection(s.charAt(0));
		 if(position != -1){
		    mListView.setSelection(position);
		  }					
		 }
	   });
	  
	  mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			//����Ҫ����adapter.getItem(position)����ȡ��ǰposition����Ӧ�Ķ���
			Toast.makeText(getApplication(), ((SortModel)mAdapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
			if ( ( (SortModel)mAdapter.getItem(position) ).hasSelected() ) {
				//���֮ǰ�Ѿ���ѡ����
				view.setBackground(null);
				( (SortModel)mAdapter.getItem(position) ).setUnselected();
			}
			else {
				//���֮ǰû�б�ѡ��
				view.setBackgroundColor(Color.RED);
				( (SortModel)mAdapter.getItem(position) ).setSelected();
			}
		  }
		});
	   //�������������ֵ�ĸı�����������
		mClearEditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
						//������������ֵΪ�գ�����Ϊԭ�����б�����Ϊ���������б�
				filterData(s.toString());
			}
					
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
							int after) {
						
			}
					
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}
	@Override
	public void setProcess() {
		// TODO Auto-generated method stub
		//�����Ҳഥ������
        SourceDateList = filledData(
        		ContactUtil.getAllPersonName(this));
		
		// ����a-z��������Դ����
		Collections.sort(SourceDateList, pinyinComparator);
		mAdapter = new SortAdapter(this, SourceDateList);
		mListView.setAdapter(mAdapter);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.select_contact_back:
			this.finish();
			break;
		case R.id.select_contact_sure:
			//��ȡ��ѡ����˵���ϵ��
			String nameTmp = "";		
			List<SortModel> mList = mAdapter.getAllSelectedItem();
			NewActivityConst.Contact = new ArrayList<ContactModel>();
			String []contactname = new String[mAdapter.getAllSelectedItem().size()];
			for (int i = 0; i < mAdapter.getAllSelectedItem().size(); i++) {
				ContactModel model = new ContactModel();
				model.setNickname(mList.get(i).getName());
				contactname[i] = mList.get(i).getName();
				NewActivityConst.Contact.add(model);
				nameTmp = mAdapter.getAllSelectedItem().get(i).getName()+","+nameTmp;
			} 
			for (int i = 0; i < contactname.length; i++) {
				Log.d("��ȡ��ѡ����˵���ϵ��", contactname[i]);
			}
			NewActivityConst.phoneNumList = 
					ContactUtil.getPhoneFromName(this, contactname);
			NewActivityConst.printPhoneNumList();
			mIntent = new Intent();
			mIntent.putExtra("CONTACT_NAME", nameTmp);
			SelectContactActivity.this.setResult(100,mIntent);
			this.finish();
			break;
		default:
			break;
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		this.overridePendingTransition(R.anim.right_in, R.anim.right_out); 
	}
	
	/**
	 * ΪListView�������
	 * @param date
	 * @return
	 */
	private List<SortModel> filledData(String [] date){
		List<SortModel> mSortList = new ArrayList<SortModel>();
		
		for(int i=0; i<date.length; i++){
			SortModel sortModel = new SortModel();
			sortModel.setName(date[i]);
			//����ת����ƴ��
			String pinyin = characterParser.getSelling(date[i]);
			String sortString = pinyin.substring(0, 1).toUpperCase();
			
			// ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
			if(sortString.matches("[A-Z]")){
				sortModel.setSortLetters(sortString.toUpperCase());
			}else{
				sortModel.setSortLetters("#");
			}
			
			mSortList.add(sortModel);
		}
		return mSortList;
		
	}
	
	/**
	 * ����������е�ֵ���������ݲ�����ListView
	 * @param filterStr
	 */
	private void filterData(String filterStr){
		List<SortModel> filterDateList = new ArrayList<SortModel>();
		
		if(TextUtils.isEmpty(filterStr)){
			filterDateList = SourceDateList;
		}else{
			filterDateList.clear();
			for(SortModel sortModel : SourceDateList){
				String name = sortModel.getName();
				if(name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())){
					filterDateList.add(sortModel);
				}
			}
		}
		
		// ����a-z��������
		Collections.sort(filterDateList, pinyinComparator);
		mAdapter.updateListView(filterDateList);
	}
	
}

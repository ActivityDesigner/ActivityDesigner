package app.activity;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.activitydesigner.R;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import app.adapter.SortAdapter;
import app.model.SortModel;
import app.utils.CharacterParser;
import app.utils.ContactUtil;
import app.utils.PinyinComparator;
import app.view.widget.ClearEditText;
import app.view.widget.SideBar;
import app.view.widget.SideBar.OnTouchingLetterChangedListener;

/**
 * ��ϵ����Ϣ
 * @author W.Z.L
 *
 */
public class ContactInfoActivity extends BaseActivity{

	private ListView sortListView;
	private SideBar sideBar;
	private TextView dialog;
	private SortAdapter adapter;
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
	ImageView mBack;
	@Override
	public void setView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_contact_info);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

		//ʵ��������תƴ����
		characterParser = CharacterParser.getInstance();
		
		pinyinComparator = new PinyinComparator();
		sideBar = (SideBar) findViewById(R.id.sidrbar);
		dialog = (TextView) findViewById(R.id.dialog);
		sortListView = (ListView) findViewById(R.id.country_lvcountry);
		mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);
		sideBar.setTextView(dialog);
		mBack = (ImageView)findViewById(R.id.contact_info_back);
		mBack.setOnClickListener(this);
		}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		//�����Ҳഥ������
	}

	@Override
	public void setProcess() {
		// TODO Auto-generated method stub
		//�����Ҳഥ������
				sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {
					
					@Override
					public void onTouchingLetterChanged(String s) {
						//����ĸ�״γ��ֵ�λ��
						int position = adapter.getPositionForSection(s.charAt(0));
						if(position != -1){
							sortListView.setSelection(position);
						}
						
					}
				});
				
				sortListView = (ListView) findViewById(R.id.country_lvcountry);
				sortListView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						//����Ҫ����adapter.getItem(position)����ȡ��ǰposition����Ӧ�Ķ���
						Toast.makeText(getApplication(), ((SortModel)adapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
						mIntent = new Intent(ContactInfoActivity.this,ContactInfoDetailActivity.class);
						startActivity(mIntent);
					}
				});
        //SourceDateList = filledData(getResources().getStringArray(R.array.date));
				SourceDateList = filledData(ContactUtil.getAllPersonName(this));
		
		// ����a-z��������Դ����
		Collections.sort(SourceDateList, pinyinComparator);
		adapter = new SortAdapter(this, SourceDateList);
		sortListView.setAdapter(adapter);
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
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.contact_info_back:
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
		adapter.updateListView(filterDateList);
	}

}

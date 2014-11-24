package app.view.dialog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.activitydesigner.R;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import app.adapter.ChatMsgAdapter;
import app.alchat.ChatMessage;
import app.alchat.ChatMessage.Type;
import app.alchat.ChatMessageAdapter;
import app.alchat.HttpUtils;

public class ChatDialog extends Dialog implements OnClickListener{

	Context mContext;
	/**
	 * 展示消息的listview
	 */
	private ListView mChatView;

	/**
	 * 存储聊天消息
	 */
	private List<ChatMessage> mDatas = new ArrayList<ChatMessage>();
	/*
	 * 适配器
	 */
	private ChatMessageAdapter mAdapter;
	
	Button mSend;
	
	/**
	 * 文本域
	 */
	EditText mText;
	
	ImageView mBack;
	
	public ChatDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.widget_dialog_chat);
		initView();
		mAdapter = new ChatMessageAdapter(getContext(), mDatas);
		mChatView.setAdapter(mAdapter);
	}
	
	
	private void initView()
	{
		mChatView = (ListView) findViewById(R.id.chat_listView);
		mSend = (Button)findViewById(R.id.chat_send);
		mSend.setOnClickListener(this);
		mText = (EditText)findViewById(R.id.chat_text);
		mDatas.add(new ChatMessage(Type.INPUT, "聊天功能~"));
		mBack = (ImageView)findViewById(R.id.chat_back);
		mBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.chat_send:
			sendMessage(arg0);
			break;
		case R.id.chat_back:
			dismiss();
		default:
			break;
		}
	}
	
	private Handler mHandler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			ChatMessage from = (ChatMessage) msg.obj;
			mDatas.add(from);
			mAdapter.notifyDataSetChanged();
			mChatView.setSelection(mDatas.size() - 1);
		};
	};
	
	public void sendMessage(View view)
	{
		final String msg = mText.getText().toString();
		if (TextUtils.isEmpty(msg))
		{
			Toast.makeText(mContext, "您还没有填写信息呢...", Toast.LENGTH_SHORT).show();
			return;
		}

		ChatMessage to = new ChatMessage(Type.OUTPUT, msg);
		to.setDate(new Date());
		mDatas.add(to);

		mAdapter.notifyDataSetChanged();
		mChatView.setSelection(mDatas.size() - 1);

		mText.setText("");

		// 关闭软键盘
		InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
		// 得到InputMethodManager的实例
		if (imm.isActive())
		{
			// 如果开启
			imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
					InputMethodManager.HIDE_NOT_ALWAYS);
			// 关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
		}

		new Thread()
		{
			public void run()
			{
				ChatMessage from = null;
				try
				{
					from = HttpUtils.sendMsg(msg);
				} catch (Exception e)
				{
					from = new ChatMessage(Type.INPUT, "服务器挂了呢...");
				}
				Message message = Message.obtain();
				message.obj = from;
				mHandler.sendMessage(message);
			};
		}.start();

	}
}

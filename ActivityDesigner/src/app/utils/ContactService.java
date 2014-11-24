package app.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import app.model.ContactModel;


/**
 * @auther hujh
 * @version 2010-10-31 ����06:01:56
 */

public class ContactService
{

    public static ContactService service;

    private ContactService()
    {

    }

    public static ContactService instance()
    {
        if (service == null)
            service = new ContactService();
        return service;
    }

    /**
     * <һ�仰���ܼ���> <������ϸ����>
     * 
     * @param activity
     * @param type
     * @param flag
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public List<ContactModel> loadInfoFromPhone(Activity activity, int type, Map<String, String> map)
    {
        List<ContactModel> list = new ArrayList<ContactModel>();
        // �õ�ContentResolver����
        ContentResolver cr = activity.getContentResolver();
        // ȡ�õ绰���п�ʼһ��Ĺ��
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        Uri uri;
        if (type == 1)
        {
            Intent intent = new Intent();
            intent.setData(Uri.parse("content://icc/adn"));
            uri = intent.getData();
        }
        else
        {
            uri = ContactsContract.Contacts.CONTENT_URI;
        }
        cursor = activity.getContentResolver().query(uri, null, null, null, null);
        ContactModel entity = null;
        while (cursor.moveToNext())
        {
            // ȡ����ϵ��ID
            int id = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            // ����ContactsContract.CommonDataKinds.Phone�и��ݲ�ѯ��Ӧid��ϵ�˵����е绰��
            Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", new String[] {Integer.toString(id)}, null);
            // ȡ�õ绰����(���ܴ��ڶ������)
            while (phone.moveToNext())
            {
                entity = new ContactModel();
                // ȡ����ϵ������
                int nameFieldColumnIndex = cursor.getColumnIndex(PhoneLookup.DISPLAY_NAME);
                entity.setNickname(cursor.getString(nameFieldColumnIndex));
                // String pic =
                // cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_ID));
                String strPhoneNumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                entity.setPhoneNum(GetNumber(strPhoneNumber));
                if ("1".equals(map.get(GetNumber(strPhoneNumber))))
                {
                    entity.setFlag("1");
                }
                else
                {
                    entity.setFlag("0");
                }

                list.add(entity);
            }
            phone.close();
        }
        cursor.close();
        return list;
    }

    /*
     * public int deleteSelectedItem(final ContactManagerActvity activity, int
     * position) { Uri uri = null; ContactEntity entity = null;
     * List<ContactEntity> list = null; int tab =
     * activity.myTabHost.getCurrentTab(); if (tab == 0) list =
     * activity.phoneList; else list = activity.simList; entity =
     * list.get(position); Message msg; uri =
     * ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,
     * entity.getId()); int count = activity.getContentResolver().delete(uri,
     * null, null); list.remove(position); msg = new Message(); msg.what =
     * tab; activity.myHandler.sendMessage(msg); return count; } public void
     * deleteSelectedItems(final ContactManagerActvity activity) { new
     * Thread(new Runnable() {
     * @Override public void run() { // TODO Auto-generated method stub Uri
     * uri = null; ContactEntity entity; Message msg; if
     * (activity.myTabHost.getCurrentTab() == 0) for (int i = 0, j =
     * activity.phoneList.size(); i < j; i++) { if (!activity.canDelete) try {
     * Thread.sleep(100); } catch (InterruptedException e) { // TODO
     * Auto-generated catch block e.printStackTrace(); } entity =
     * activity.phoneList.get(i); if (entity.isChecked()) { uri =
     * ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,
     * entity.getId()); activity.getContentResolver().delete(uri, null, null);
     * activity.phoneList.remove(i); i--; j--; msg = new Message(); msg.what =
     * 0; activity.canDelete = false; activity.myHandler.sendMessage(msg); } }
     * else for (int i = 0, j = activity.simList.size(); i < j; i++) { if
     * (!activity.canDelete) try { Thread.sleep(100); } catch
     * (InterruptedException e) { // TODO Auto-generated catch block
     * e.printStackTrace(); } entity = activity.simList.get(i); if
     * (entity.isChecked()) { uri =
     * ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,
     * entity.getId()); activity.getContentResolver().delete(uri, null, null);
     * activity.simList.remove(i); i--; j--; msg = new Message(); msg.what =
     * 1; activity.canDelete = false; activity.myHandler.sendMessage(msg); } }
     * msg = new Message(); msg.what = 2; activity.myHandler.sendMessage(msg);
     * } }).start(); } public void checkAll(ContactManagerActvity activity) {
     * int type = activity.myTabHost.getCurrentTab(); if (type == 0) { for
     * (int i = 0, j = activity.phoneList.size(); i < j; i++) {
     * activity.phoneList.get(i).setChecked(true);
     * activity.phoneAdapter.notifyDataSetChanged(); } } else if (type == 1) {
     * for (int i = 0, j = activity.simList.size(); i < j; i++) {
     * activity.simList.get(i).setChecked(true);
     * activity.simAdapter.notifyDataSetChanged(); } } } public void
     * cancelAll(ContactManagerActvity activity) { int type =
     * activity.myTabHost.getCurrentTab(); if (type == 0) { for (int i = 0, j
     * = activity.phoneList.size(); i < j; i++) {
     * activity.phoneList.get(i).setChecked(false);
     * activity.phoneAdapter.notifyDataSetChanged(); } } else if (type == 1) {
     * for (int i = 0, j = activity.simList.size(); i < j; i++) {
     * activity.simList.get(i).setChecked(false);
     * activity.simAdapter.notifyDataSetChanged(); } } }
     */
    // ��ԭ11λ�ֻ��� ����ȥ����-��
    public static String GetNumber(String num2)
    {
        String num;
        if (num2 != null)
        {
            num = num2.replaceAll("-", "");
            if (num.startsWith("+86"))
            {
                num = num.substring(3);
            }
            else if (num.startsWith("86"))
            {
                num = num.substring(2);
            }
            else if (num.startsWith("86"))
            {
                num = num.substring(2);
            }
        }
        else
        {
            num = "";
        }
        return num;
    }
}

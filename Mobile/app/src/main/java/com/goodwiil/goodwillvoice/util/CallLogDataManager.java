package com.goodwiil.goodwillvoice.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.goodwiil.goodwillvoice.model.CallAnalysisInfo;
import com.goodwiil.goodwillvoice.model.CallLogData;
import com.goodwiil.goodwillvoice.model.CallLogInfo;
import com.goodwiil.goodwillvoice.model.CallNumber;
import com.goodwiil.goodwillvoice.model.ContactInfo;
import com.goodwiil.goodwillvoice.model.NumberType;
import com.goodwiil.goodwillvoice.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CallLogDataManager {
    public static final int SEOUL_NUMBER = 0;
    public static final int BUSAN_NUMBER = 0;
    public static final int DAEGU_NUMBER = 0;
    public static final int INCHEON_NUMBER = 0;
    public static final int GYANGJU_NUMBER = 0;
    public static final int DAEJEON_NUMBER = 0;
    public static final int ULSAN_NUMBER = 0;
    public static final int SEJONG_NUMBER = 0;
    public static final int GYEONGGI_NUMBER = 0;
    public static final int GANGWON_NUMBER = 0;
    public static final int CHUNGBUK_NUMBER = 0;
    public static final int CHUNGNAM_NUMBER = 0;
    public static final int JEONBUK_NUMBER = 0;
    public static final int JEONNAM_NUMBER = 0;
    public static final int GYEONGBUK_NUMBER = 0;
    public static final int GYEONGNAM_NUMBER = 0;
    public static final int JEJU_NUMBER = 0;


    public static CallAnalysisInfo callAnalysisInfo = new CallAnalysisInfo();
    public static ArrayList<CallLogInfo> callLogInfos = new ArrayList<CallLogInfo>();
    public static HashMap<String, String> callNumbers = new HashMap<String, String>();
    public static NumberType numberType = new NumberType();
    public static FirebaseFirestore db;

    public static int knownTotal = 0;
    public static int unknownTotal = 0;

    public static int totalCall = 0;
    public static int knownCallTotal = 0;
    public static int unknownCallTotal = 0;
    public static int knownCallTotalNum = 0;
    public static int unknownCallTotalNum = 0;

    //위치정보 받기
    public static ArrayList<Double> getCurrentLoc(Context context) {

        ArrayList<Double> gps = new ArrayList<Double>();
        double longitude;
        double latitude;

        int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        final LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            if(lm.getLastKnownLocation(LocationManager.GPS_PROVIDER) != null){
                Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                longitude = location.getLongitude();
                latitude = location.getLatitude();
                gps.add(longitude);
                gps.add(latitude);
            }
            else{
                gps.add(0.0);
                gps.add(0.0);
            }

        }

        return gps;
    }

    //파이어베이스 등록번호 목록 불러오기
    public static HashMap<String, String> getFireBaseCallLog(){

        db = FirebaseFirestore.getInstance();
        User user = AppDataManager.getUserModel();
        CollectionReference colRef = db.collection("UserCallLog").document(user.getNumber()).collection("CallNumbers");

        colRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int count = 0;
                            for (DocumentSnapshot document : task.getResult()) {
                                CallNumber callNumber = document.toObject(CallNumber.class);
                                callNumbers.put(callNumber.getNumber(), callNumber.getType());
                            }


                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });


        return callNumbers;
    }


    //최근기록 불러오기
    public static ArrayList<CallLogInfo> getCallLog(Context context, int type) {

        CallLogDataManager.unknownCallTotalNum = 0;
        CallLogDataManager.unknownCallTotal = 0;
        CallLogDataManager.knownCallTotalNum = 0;
        CallLogDataManager.knownCallTotal = 0;
        CallLogDataManager.unknownTotal = 0;
        CallLogDataManager.knownTotal = 0;

        callAnalysisInfo = new CallAnalysisInfo();
        numberType = new NumberType();
        ArrayList<CallLogInfo> callLogInfos = new ArrayList<CallLogInfo>();

        int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null);
            int log_name = cursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
            int log_number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
            int log_type = cursor.getColumnIndex(CallLog.Calls.TYPE);
            int log_date = cursor.getColumnIndex(CallLog.Calls.DATE);
            int log_duration = cursor.getColumnIndex(CallLog.Calls.DURATION);

            while (cursor.moveToNext()) {
                CallLogInfo callLogInfo = new CallLogInfo();

                if(cursor.getString(log_number).length()>3){
                    categorizeIncomingNumber(cursor.getString(log_number));
                    if(cursor.getString(log_name) == null){
                        CallLogDataManager.unknownTotal += 1;
                        callLogInfo.setName("unknown");

                        if((Integer.parseInt(cursor.getString(log_duration)) > 0)){

                            CallLogDataManager.unknownCallTotalNum += 1;
                            CallLogDataManager.unknownCallTotal += Integer.parseInt(cursor.getString(log_duration));


                            if((Integer.parseInt(cursor.getString(log_duration)) > callAnalysisInfo.getUnknownCallMax())){
                                callAnalysisInfo.setUnknownCallMax(Integer.parseInt(cursor.getString(log_duration)));
                                callAnalysisInfo.setUnknownCallMaxNumber(CallLogDataManager.convertNumber(cursor.getString(log_number)));
                            }

                            if((Integer.parseInt(cursor.getString(log_duration)) < callAnalysisInfo.getUnknownCallMin())){
                                callAnalysisInfo.setUnknownCallMin(Integer.parseInt(cursor.getString(log_duration)));
                                callAnalysisInfo.setUnknownCallMinNumber(CallLogDataManager.convertNumber(cursor.getString(log_number)));
                            }

                        }

                    }

                    else{
                        CallLogDataManager.knownTotal += 1;
                        CallLogDataManager.knownCallTotalNum += 1;
                        CallLogDataManager.knownCallTotal += Integer.parseInt(cursor.getString(log_duration));
                        callLogInfo.setName(cursor.getString(log_name));
                    }

                    callLogInfo.setNumber(cursor.getString(log_number));
                    callLogInfo.setDate(changeDate(cursor.getString(log_date)));
                    callLogInfo.setDuration(Integer.parseInt((cursor.getString(log_duration))));

                    String callType = cursor.getString(log_type);
                    int code = Integer.parseInt(callType);
                    switch (code) {
                        case CallLog.Calls.OUTGOING_TYPE:
                            callLogInfo.setType("OUTGOING");
                            if(callLogInfo.getName().equals("unknown")) callAnalysisInfo.setNumOutgoing(callAnalysisInfo.getNumOutgoing()+1);
                            break;
                        case CallLog.Calls.INCOMING_TYPE:
                            callLogInfo.setType("INCOMING");
                            if(callLogInfo.getName().equals("unknown")) callAnalysisInfo.setNumIncoming(callAnalysisInfo.getNumIncoming()+1);
                            break;
                        case CallLog.Calls.MISSED_TYPE:
                            callLogInfo.setType("MISSED");
                            if(callLogInfo.getName().equals("unknown")) callAnalysisInfo.setNumMissed(callAnalysisInfo.getNumMissed()+1);
                            break;
                        case CallLog.Calls.REJECTED_TYPE:
                            callLogInfo.setType("REJECTED");
                            if(callLogInfo.getName().equals("unknown")) callAnalysisInfo.setNumRejected(callAnalysisInfo.getNumRejected()+1);
                            break;
                    }

                    if(type == 0) callLogInfos.add(callLogInfo);

                    else if(type == 1){
                        if(callLogInfo.getDuration() > 0 &&
                                !callLogInfo.getType().equals("MISSED") &&
                                !callLogInfo.getType().equals("OUTGOING") &&
                                !callLogInfo.getType().equals("REJECTED") &&
                                callLogInfo.getName().equals("unknown")
                        )
                            callLogInfos.add(callLogInfo);
                    }

                }


            }

            cursor.close();

            CallLogDataManager.totalCall = CallLogDataManager.knownTotal + CallLogDataManager.unknownTotal;
        } else {
            ScreenManager.printToast(context, "최근기록 읽기 권한을 받아야 사용할수 있습니다.");
        }

        return callLogInfos;
    }

    //연락처 불러오기
    public static ArrayList<ContactInfo> getContacts(Context context) {

        ArrayList<ContactInfo> contactInfos = new ArrayList<ContactInfo>();

        int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Cursor cursor = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

            while (cursor.moveToNext()) {
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                if (hasPhoneNumber > 0) {
                    ContactInfo contactsInfo = new ContactInfo();

                    String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    contactsInfo.setName(displayName);

                    Cursor phoneCursor = context.getContentResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{contactId},
                            null);

                    if (phoneCursor.moveToNext()) {
                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                        contactsInfo.setPhoneNumber(phoneNumber.replaceAll("-", ""));
                    }

                    phoneCursor.close();

                    contactInfos.add(contactsInfo);
                }
            }
        } else {
            ScreenManager.printToast(context, "연락처 권한을 받아야 사용할수 있습니다.");
        }

        return contactInfos;
    }

    //연락처에서 번호 찾기
    public static String contactExists(Context context, String number) {
        /// number is the phone number
        String contactName = "unknown";

        Uri lookupUri = Uri.withAppendedPath(
                ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(number));
        String[] mPhoneNumberProjection = {ContactsContract.PhoneLookup._ID, ContactsContract.PhoneLookup.NUMBER, ContactsContract.PhoneLookup.DISPLAY_NAME};
        Cursor cur = context.getContentResolver().query(lookupUri, mPhoneNumberProjection, null, null, null);
        try {
            if (cur.moveToFirst()) {
                contactName = cur.getString(2);
                cur.close();
                return contactName;
            }
        } finally {
            if (cur != null)
                cur.close();
        }
        return contactName;
    }


    //날짜 형식 바꾸기 "yyyy/MM/dd HH:mm"
    public static String changeDate(String log_date) {
        Date logDate = new Date(Long.valueOf(log_date));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String newDate = formatter.format(logDate);

        return newDate;
    }

    //전화번호 형식으로 바꾸기
    public static String convertNumber(String src) {
        if (src == null) {
            return "";
        }
        if (src.length() == 8) {
            return src.replaceFirst("^([0-9]{4})([0-9]{4})$", "$1-$2");
        } else if (src.length() == 12) {
            return src.replaceFirst("(^[0-9]{4})([0-9]{4})([0-9]{4})$", "$1-$2-$3");
        }
        return src.replaceFirst("(^02|[0-9]{3})([0-9]{3,4})([0-9]{4})$", "$1-$2-$3");
    }

    //초를 mm:ss 형식으로 바꾸기
    public static String secondsToString(int pTime) {
        return String.format("%02d:%02d", pTime / 60, pTime % 60);
    }

    // 번호 분류하기
    private static void categorizeIncomingNumber(String number){

        String numberKind = number.substring(0,3);
        if(numberKind.equals("010")) numberType.addPhoneNumber();
        else if(numberKind.equals("070"))numberType.addInternetNumber();
        else if(number.substring(0,2).equals("02")) numberType.addlocationNumber(SEOUL_NUMBER);
        else if(numberKind.equals("051")) numberType.addlocationNumber(BUSAN_NUMBER);
        else if(numberKind.equals("053")) numberType.addlocationNumber(DAEGU_NUMBER);
        else if(numberKind.equals("032")) numberType.addlocationNumber(INCHEON_NUMBER);
        else if(numberKind.equals("062")) numberType.addlocationNumber(GYANGJU_NUMBER);
        else if(numberKind.equals("042")) numberType.addlocationNumber(DAEJEON_NUMBER);
        else if(numberKind.equals("052")) numberType.addlocationNumber(ULSAN_NUMBER);
        else if(numberKind.equals("044")) numberType.addlocationNumber(SEJONG_NUMBER);
        else if(numberKind.equals("031")) numberType.addlocationNumber(GYEONGGI_NUMBER);
        else if(numberKind.equals("033")) numberType.addlocationNumber(GANGWON_NUMBER);
        else if(numberKind.equals("043")) numberType.addlocationNumber(CHUNGBUK_NUMBER);
        else if(numberKind.equals("041")) numberType.addlocationNumber(CHUNGNAM_NUMBER);
        else if(numberKind.equals("063")) numberType.addlocationNumber(JEONBUK_NUMBER);
        else if(numberKind.equals("061")) numberType.addlocationNumber(JEONNAM_NUMBER);
        else if(numberKind.equals("054")) numberType.addlocationNumber(GYEONGBUK_NUMBER);
        else if(numberKind.equals("055")) numberType.addlocationNumber(GYEONGNAM_NUMBER);
        else if(numberKind.equals("064")) numberType.addlocationNumber(JEJU_NUMBER);


    }

}

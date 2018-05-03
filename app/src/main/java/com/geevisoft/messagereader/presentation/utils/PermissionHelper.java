package com.geevisoft.messagereader.presentation.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class PermissionHelper {

	public static boolean isPending(Context context, String permission){
		return ContextCompat.checkSelfPermission(context, permission)
				!= PackageManager.PERMISSION_GRANTED;
	}

	public static boolean isGranted(Context context, String permission){
		return ContextCompat.checkSelfPermission(context, permission)
				== PackageManager.PERMISSION_GRANTED;
	}

	public static boolean readSmsPermissionIsGranted(Context context){
		return isGranted(context, Manifest.permission.READ_SMS);
	}

	public static void showReadSmsPermissionRequest(Activity activity){
		showPermissionRequest(activity, Manifest.permission.READ_SMS);
	}

	public static void checkReadSmsPermission(Activity activity){
		checkPermission(activity, Manifest.permission.READ_SMS);
	}

	public static void showPermissionRequest(Activity activity, String permission){
		if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
			ActivityCompat.requestPermissions(activity, new String[]{ permission },23);
		}
	}

	public static void checkPermission(Activity activity, String permission){
		if (isPending(activity, permission)) {
			showPermissionRequest(activity, permission);
		}
	}

}
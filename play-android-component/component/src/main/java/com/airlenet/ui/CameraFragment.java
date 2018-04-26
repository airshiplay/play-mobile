package com.airlenet.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.airlenet.util.FileUtils;

public class CameraFragment extends BaseFragment {
	protected static String IMAGE_FILE_NAME;
	protected Bitmap profileImageFile;
	protected String profileImageUrl;
	protected File rotateFile;
	/* 请求码 */
	protected static final int IMAGE_REQUEST_CODE = 5;
	protected static final int CAMERA_REQUEST_CODE = 6;
	protected static final int RESULT_REQUEST_CODE = 7;
	protected File fullImageUri;
	protected void camera() {
//		IMAGE_FILE_NAME = System.currentTimeMillis() + ".PNG";
		IMAGE_FILE_NAME = System.currentTimeMillis() + ".JPEG";
		Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// 判断存储卡是否可以用，可用进行存储
		fullImageUri = new File(FileUtils.getCameraFile(getActivity()),
				IMAGE_FILE_NAME);
		if (FileUtils.hasSdcard()) {
			intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
					.fromFile(fullImageUri));
		}
		intentFromCapture.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
		startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);
	}

	/**
	 * 裁剪图片方法实现
	 * 
	 * @param uri
	 */
	protected void startPhotoZoom(Uri uri) {

		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// 设置裁剪
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 320);
		intent.putExtra("outputY", 320);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, RESULT_REQUEST_CODE);
	}

	public void onActivityResultDemo(int requestCode, int resultCode,
			Intent data) {
		if (resultCode == Activity.RESULT_CANCELED) {
			return;
		}
		switch (requestCode) {
		case IMAGE_REQUEST_CODE:
			startPhotoZoom(data.getData());
			break;
		case CAMERA_REQUEST_CODE:
			if (FileUtils.hasSdcard()) {
				File tempFile = new File(FileUtils.getStorePath(getActivity()),
						IMAGE_FILE_NAME);
				startPhotoZoom(Uri.fromFile(tempFile));
			} else {
				Toast.makeText(getActivity(), "未找到存储卡，无法存储照片！",
						Toast.LENGTH_LONG).show();
			}

			break;
		case RESULT_REQUEST_CODE:
			if (data != null) {
				getImageToView(data);
			}
			break;
		default:
			if (resultCode == Activity.RESULT_OK) {

			}
			break;
		}
	}

	/**
	 * 保存裁剪之后的图片数据
	 * 
	 * @param data
	 */
	private void getImageToView(Intent data) {
		Bundle extras = data.getExtras();
		if (extras != null) {
			Bitmap photo = extras.getParcelable("data");
			Drawable drawable = new BitmapDrawable(photo);

		}
	}

	protected void compressHeadPhoto(final Bitmap bm) {
		rotateFile = new File(FileUtils.getStorePath(getActivity()),
//				System.currentTimeMillis()+"rotate.png");
				System.currentTimeMillis()+"rotate.jpeg");
		try {
//			bm.compress(Bitmap.CompressFormat.PNG, 70, new FileOutputStream(
//					rotateFile));
			bm.compress(Bitmap.CompressFormat.JPEG, 70, new FileOutputStream(
					rotateFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static final int DEFAULT_REQUIRED_SIZE = 70;

	public static Bitmap decodeFile(File f, int size) {
		try {
			BitmapFactory.Options option = new BitmapFactory.Options();
			option.inJustDecodeBounds = true;
			FileInputStream stream1 = new FileInputStream(f);
			BitmapFactory.decodeStream(stream1, null, option);
			stream1.close();
			final int REQUIRED_SIZE = size > 0 ? size : DEFAULT_REQUIRED_SIZE;
			int width_tmp = option.outWidth, height_tmp = option.outHeight;
			int scale = 1;
			while (true) {
				if (width_tmp / 2 < REQUIRED_SIZE
						|| height_tmp / 2 < REQUIRED_SIZE)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}
			if (scale >= 2) {
				scale /= 2;
			}
			BitmapFactory.Options option2 = new BitmapFactory.Options();
			option2.inSampleSize = scale;
			FileInputStream stream2 = new FileInputStream(f);
			Bitmap bitmap = BitmapFactory.decodeStream(stream2, null, option2);
			stream2.close();
			return bitmap;
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}

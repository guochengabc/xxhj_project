package com.kongtiaoapp.xxhj.e_code.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.DeviceNameE_CodeBean;
import com.kongtiaoapp.xxhj.bean.ECodeListBean;
import com.kongtiaoapp.xxhj.energymetering.activity.EnergyRecordActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.EcodeCreateP;
import com.kongtiaoapp.xxhj.mvp.view.EcodeCreateV;
import com.mylhyl.zxing.scanner.encode.QREncode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 二维码生成器
 */
public class EcodeCreateActivity extends BaseActivity<EcodeCreateP, EcodeCreateV> implements EcodeCreateV {
    @BindView(R.id.txt_device)
    TextView txt_device;
    @BindView(R.id.img_code)
    ImageView img_code;
    private String whichModule = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_ecode_create;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        img_code.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View viewm) {
                Bitmap obmp = ((BitmapDrawable) (img_code).getDrawable()).getBitmap();
                int width = obmp.getWidth();
                int height = obmp.getHeight();
                int[] data = new int[width * height];
                obmp.getPixels(data, 0, width, 0, 0, width, height);
                RGBLuminanceSource source = new RGBLuminanceSource(width, height, data);
                BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));
                QRCodeReader reader = new QRCodeReader();
                Result re = null;
                try {
                    re = reader.decode(bitmap1);
                } catch (NotFoundException e) {
                    e.printStackTrace();
                } catch (ChecksumException e) {
                    e.printStackTrace();
                } catch (FormatException e) {
                    e.printStackTrace();
                }
                if (re == null) {
                    showAlert(obmp);
                } else {
                    showSelectAlert(obmp, re.getText());
                }
                return false;
            }
        });
    }

    private void showAlert(final Bitmap bitmap) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("保存图片")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterfacem, int i) {
                        saveImageToGallery(EcodeCreateActivity.this, bitmap);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterfacem, int i) {
                    }
                });
        builder.show();
    }

    private void showSelectAlert(final Bitmap bitmap, final String url) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择");
        String str[] = {"保存图片", "识别图中二维码"};
        builder.setItems(str, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterfacem, int i) {
                switch (i) {
                    case 0: {
                        saveImageToGallery(EcodeCreateActivity.this, bitmap);
                    }
                    break;
                    case 1: {
                        Log.i("ffffffff", "========二维码内容===" + url);
                        DeviceNameE_CodeBean deviceNameE_codeBean = gson.fromJson(url, DeviceNameE_CodeBean.class);
                        Intent intent = new Intent();
                        if (deviceNameE_codeBean.getDeviceId() == null) {
                            intent.setClass(EcodeCreateActivity.this, EnergyRecordActivity.class);
                            intent.putExtra("device", (Serializable) deviceNameE_codeBean);
                        } else {
                            intent.setClass(EcodeCreateActivity.this, RecordFormEcodeActivity.class);
                            intent.putExtra("device", (Serializable) deviceNameE_codeBean);
                        }
                        startActivity(intent);
                        finish();
                    }
                    break;
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterfacem, int i) {

            }
        });
        builder.show();
    }

    /**
     * 将bitmap保存到相册
     */
    public static void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "xxhj");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 0, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
            Toast.makeText(context, "保存图片成功" + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        context.sendBroadcast(intent);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            ECodeListBean.ResobjBean bean = (ECodeListBean.ResobjBean) intent.getSerializableExtra("device");
            if (bean != null) {
                Map<String, String> mapCode = new HashMap<>();
                whichModule = intent.getStringExtra("whichModule");
                if (whichModule.equals("0")) {
                    mapCode.put("DeviceId", bean.getDeviceId());
                } else if (whichModule.equals("1")) {
                    mapCode.put("SensorId", bean.getSensorId());
                }
                mapCode.put("Name", bean.getName());
                mapCode.put("Type", bean.getType());
                String deviceJson = gson.toJson(mapCode);
                txt_device.setText(bean.getName());
                // 二维码中间图标
                final Bitmap centerImage = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
// 生成的二维码图案
//文本类型
                Bitmap bitmap = new QREncode.Builder(this)
                        .setColor(getResources().getColor(R.color.a666666))//二维码颜色
                        //.setParsedResultType(ParsedResultType.TEXT)//默认是TEXT类型
                        .setContents(deviceJson)//二维码内容
                        .setLogoBitmap(centerImage)//二维码中间logo
                        .build().encodeAsBitmap();
                img_code.setImageBitmap(bitmap);
            }
        }
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    protected EcodeCreateP getPresenter() {
        return new EcodeCreateP();
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

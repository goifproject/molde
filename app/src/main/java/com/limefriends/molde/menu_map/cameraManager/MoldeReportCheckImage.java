package com.limefriends.molde.menu_map.cameraManager;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.limefriends.molde.R;
import com.limefriends.molde.menu_map.report.MoldeReportActivity;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoldeReportCheckImage extends AppCompatActivity {
    @BindView(R.id.report_check_image)
    ImageView report_check_image;
    @BindView(R.id.report_check_ok_button)
    ImageButton report_check_ok_button;
    @BindView(R.id.report_check_cancel_button)
    ImageButton report_check_cancel_button;

    private final int ADD_IMAGE = 100;
    private int imageSeq = 0;

    private String reportContent;
    private String reportAddress;
    private String reportDetailAddress;
    private String reportEmailName;
    private String reportEmailDomainPosition;
    private String reportLat;
    private String reportLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity_molde_report_check_image);
        ButterKnife.bind(this);

        Intent cameraIntent = getIntent();
        imageSeq = cameraIntent.getIntExtra("imageSeq", 1);
        final Uri uri = cameraIntent.getParcelableExtra("imagePath");
        reportContent = cameraIntent.getStringExtra("reportContent");
        reportAddress = cameraIntent.getStringExtra("reportAddress");
        reportDetailAddress = cameraIntent.getStringExtra("reportDetailAddress");
        reportEmailName = cameraIntent.getStringExtra("reportEmailName");
        reportEmailDomainPosition = cameraIntent.getStringExtra("reportEmailDomainPosition");
        reportLat = cameraIntent.getStringExtra("reportLat");
        reportLng = cameraIntent.getStringExtra("reportLng");

        if(uri != null){
            report_check_image.setImageURI(uri);
        }

        report_check_ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MoldeReportActivity.class);
                intent.putExtra("imagePath", uri);
                intent.putExtra("imageSeq", imageSeq);
                intent.putExtra("reportContent", reportContent);
                intent.putExtra("reportAddress", reportAddress);
                intent.putExtra("reportDetailAddress", reportDetailAddress);
                intent.putExtra("reportEmailName", reportEmailName);
                intent.putExtra("reportEmailDomainPosition", reportEmailDomainPosition);
                intent.putExtra("reportLat", reportLat);
                intent.putExtra("reportLng", reportLng);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        report_check_cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File imageFile = new File(uri.getPath());
                imageFile.delete();
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(imageFile)));
                finish();
            }
        });

    }


}

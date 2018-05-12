package com.limefriends.molde.menu_map;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.limefriends.molde.R;
import com.limefriends.molde.menu_map.callbackMethod.MoldeMapHistoryRecyclerViewAdapterCallback;
import com.limefriends.molde.menu_map.callbackMethod.MoldeMapInfoRecyclerViewAdapterCallback;
import com.limefriends.molde.menu_map.cameraManager.MoldeReportCameraActivity;
import com.limefriends.molde.menu_map.entity.MoldeSearchMapHistoryEntity;
import com.limefriends.molde.menu_map.entity.MoldeSearchMapInfoEntity;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoldeReportActivity extends AppCompatActivity {
    @BindView(R.id.first_iamge)
    ImageButton first_iamge;
    @BindView(R.id.second_iamge)
    ImageButton second_iamge;
    @BindView(R.id.third_iamge)
    ImageButton third_iamge;
    @BindView(R.id.forth_image)
    ImageButton forth_iamge;
    @BindView(R.id.fifth_image)
    ImageButton fifth_iamge;

    @BindView(R.id.first_iamge_delete_button)
    ImageButton first_iamge_delete_button;
    @BindView(R.id.second_iamge_delete_button)
    ImageButton second_iamge_delete_button;
    @BindView(R.id.third_iamge_delete_button)
    ImageButton third_iamge_delete_button;
    @BindView(R.id.forth_iamge_delete_button)
    ImageButton forth_iamge_delete_button;
    @BindView(R.id.fifth_iamge_delete_button)
    ImageButton fifth_iamge_delete_button;

    @BindView(R.id.search_loc_input)
    TextView search_loc_input;

    @BindView(R.id.reply_email_select)
    Spinner reply_email_select;
    @BindView(R.id.reply_email_self)
    EditText reply_email_self;
    @BindView(R.id.self_close_button)
    ImageButton self_close_button;

    @BindView(R.id.find_map_loc_button)
    ImageButton find_map_loc_button;

    private final int TAKE_PICTURE_FOR_ADD_IMAGE = 100;
    private static SparseArrayCompat<Uri> imageArray;
    private MoldeSearchMapInfoEntity searchEntity;
    private MoldeSearchMapHistoryEntity historyEntity;

    public ArrayList<String> imagePathList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity_molde_report);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.default_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView toolbar_title = getSupportActionBar().getCustomView().findViewById(R.id.toolbar_title);
        toolbar_title.setText("신고하기");

        final InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        //UI 컨트롤
        first_iamge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePictureForAdd(1);
            }
        });
        second_iamge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePictureForAdd(2);
            }
        });
        third_iamge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePictureForAdd(3);
            }
        });
        forth_iamge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePictureForAdd(4);
            }
        });
        fifth_iamge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePictureForAdd(5);
            }
        });

        first_iamge_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageArray.delete(1);
                first_iamge_delete_button.setVisibility(View.INVISIBLE);
                first_iamge.setImageResource(R.drawable.ic_image_add);
            }
        });
        second_iamge_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageArray.delete(2);
                second_iamge_delete_button.setVisibility(View.INVISIBLE);
                second_iamge.setImageResource(R.drawable.ic_image_add);
            }
        });
        third_iamge_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageArray.delete(3);
                third_iamge_delete_button.setVisibility(View.INVISIBLE);
                third_iamge.setImageResource(R.drawable.ic_image_add);
            }
        });
        forth_iamge_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageArray.delete(4);
                forth_iamge_delete_button.setVisibility(View.INVISIBLE);
                forth_iamge.setImageResource(R.drawable.ic_image_add);
            }
        });
        fifth_iamge_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageArray.delete(5);
                fifth_iamge_delete_button.setVisibility(View.INVISIBLE);
                fifth_iamge.setImageResource(R.drawable.ic_image_add);
            }
        });

        if (imageArray == null) {
            imageArray = new SparseArrayCompat<Uri>();
        }

        Intent data = getIntent();
        if (data != null) {
            Uri uri = data.getParcelableExtra("imagePath");
            int imageSeq = data.getIntExtra("imageSeq", 1);
            imagePathList = data.getStringArrayListExtra("imagePathList");
            searchEntity = (MoldeSearchMapInfoEntity) data.getSerializableExtra("mapInfo");
            historyEntity = (MoldeSearchMapHistoryEntity) data.getSerializableExtra("mapHistoryInfo");
            if (uri != null && imageSeq != 0) {
                Log.e("d", uri + "," + imageSeq);
                if (imageArray.get(imageSeq) == null) {
                    imageArray.append(imageSeq, uri);
                } else {
                    imageArray.put(imageSeq, uri);
                }
            } else if (imagePathList != null) {
                int count = 0;
                int bringImgListSize = imagePathList.size();
                for (int i = 1; i <= 5; i++) {
                    if (bringImgListSize == 0) {
                        break;
                    }
                    if (imageArray.get(i) == null) {
                        imageArray.append(i, Uri.parse(imagePathList.get(count)));
                        count++;
                        bringImgListSize--;
                    }
                }
            } else if (searchEntity != null || historyEntity != null) {
                if (searchEntity != null) {
                    search_loc_input.setText(searchEntity.getMainAddress() + "\n" + searchEntity.getName());
                } else if (historyEntity != null) {
                    search_loc_input.setText(historyEntity.getMainAddress() + "\n" + historyEntity.getName());
                }
            }
        }

        final ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, R.array.email_select, android.R.layout.simple_spinner_item);
        reply_email_select.setAdapter(arrayAdapter);
        reply_email_select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    reply_email_select.setSelected(false);
                    return;
                }
                if (position == arrayAdapter.getCount() - 1) {
                    Log.e("d", "직접선택");
                    reply_email_select.setVisibility(View.GONE);
                    reply_email_select.setClickable(false);

                    reply_email_self.setClickable(true);
                    reply_email_self.setVisibility(View.VISIBLE);
                    self_close_button.setVisibility(View.VISIBLE);
                    self_close_button.bringToFront();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        self_close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reply_email_self.setText("");
                inputMethodManager.hideSoftInputFromWindow(reply_email_select.getWindowToken(), 0);
                reply_email_select.setVisibility(View.VISIBLE);
                reply_email_select.bringToFront();
                reply_email_select.setSelection(0);
            }
        });

        find_map_loc_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MoldeSearchMapInfoActivity.class);
                intent.putExtra("activity", "Report");
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

    public void takePictureForAdd(final int imageSeq) {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MoldeReportCameraActivity.class);
                intent.putExtra("imageSeq", imageSeq);
                intent.putExtra("imageArraySize", imageArray.size());
                startActivityForResult(intent, TAKE_PICTURE_FOR_ADD_IMAGE);
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(getApplicationContext(), "권한 거부\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.with(MoldeReportActivity.this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("권한얻는 데 실패했습니다.\n\n[설정] -> [어플리케이션] -> 해당앱에 들어가 권한을 켜주세요.")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();
    }

    public void applyImageArray() {
        for (int i = 1; i <= 5; i++) {
            if (imageArray.get(i) != null) {
                switch (i) {
                    case 1:
                        first_iamge.setImageURI(imageArray.get(i));
                        first_iamge_delete_button.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        second_iamge.setImageURI(imageArray.get(i));
                        second_iamge_delete_button.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        third_iamge.setImageURI(imageArray.get(i));
                        third_iamge_delete_button.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        forth_iamge.setImageURI(imageArray.get(i));
                        forth_iamge_delete_button.setVisibility(View.VISIBLE);
                        break;
                    case 5:
                        fifth_iamge.setImageURI(imageArray.get(i));
                        fifth_iamge_delete_button.setVisibility(View.VISIBLE);
                        break;
                    default:
                        finish();
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        applyImageArray();
    }

    @Override
    public void onBackPressed() {
        MoldeSearchMapInfoActivity.checkBackPressed = true;
        imageArray.clear();
        imageArray = null;
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}

package com.example.supsup;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class map_bottom_dialog extends BottomSheetDialogFragment {
    Context context;
    String title;
    public map_bottom_dialog(Context context)
    {
        this.context = context;
    }

    public map_bottom_dialog() {

    }

    @Override
    public int getTheme() {
        return R.style.CustomBottomSheetDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.map_bottom_dialog, container, false);
        Button btntitle = view.findViewById(R.id.title);
        if (getArguments() != null)
        {
            title = getArguments().getString("Title"); // 프래그먼트1에서 받아온 값 넣기
            btntitle.setText(title);

        }
        btntitle.setText(title);
        btntitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               btntitle.setText("확인");
                dismiss();
            }
        });

        return view;
    }
    public void setTitle(String title){
        this.title = title;
    }
}

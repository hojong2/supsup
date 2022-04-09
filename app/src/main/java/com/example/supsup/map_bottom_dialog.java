package com.example.supsup;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class map_bottom_dialog extends BottomSheetDialogFragment {
    Context context;
    String location, title, writer;
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
        TextView textlocation = view.findViewById(R.id.TextLocation);
        TextView textwriter = view.findViewById(R.id.TextWriter);
        Button btntitle = view.findViewById(R.id.btnTitle);
        ImageView imageView = view.findViewById(R.id.image);

        imageView.setImageDrawable(getResources().getDrawable(R.drawable.logo));

        textlocation.setText(location);
        btntitle.setText(title);
        btntitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast("글 클릭시 예시");
                dismiss();
            }
        });

        return view;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setLocation(String location){
        this.location = location;

    }public void setWriter(String writer){
        this.writer = writer;
    }

    public  void Toast(String str){
        Toast myToast = Toast.makeText(getActivity().getApplicationContext(),str, Toast.LENGTH_SHORT);
        myToast.show();
    }
}

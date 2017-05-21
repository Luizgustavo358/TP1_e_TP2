package com.luizgbraganca.tp2comfragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TirarFoto extends Fragment
{
    private ImageView imageView;
    private FloatingActionButton btnCamera;
    private final int TIRAR_FOTO = 123;

    public TirarFoto()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_tirar_foto, container, false);

        btnCamera = (FloatingActionButton) view.findViewById(R.id.btnTirarFoto);
        imageView = (ImageView) view.findViewById(R.id.imageView2);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tiraFoto( );
            }
        });
        return(view);
    }

    public void tiraFoto( )
    {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        if(intent.resolveActivity(getPackageManager()) != null)
//        {
//            startActivityForResult(intent, TIRAR_FOTO);
//        }// end if
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == TIRAR_FOTO && resultCode == RESULT_OK)
//        {
//            Bundle extras = data.getExtras();
//
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            imageView.setImageBitmap(imageBitmap);
//        }// end if
//    }// end onActivityResult( )
}

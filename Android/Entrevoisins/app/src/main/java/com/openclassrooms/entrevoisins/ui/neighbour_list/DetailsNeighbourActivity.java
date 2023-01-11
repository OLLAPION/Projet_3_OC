package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsNeighbourActivity extends AppCompatActivity {


    private NeighbourApiService mApiService;
    private int mPosition;
    @BindView(R.id.buttonBack)
    Button mButtonBack;
    @BindView(R.id.addFavourite)
    FloatingActionButton mButtonAddFavoris;
    @BindView(R.id.prenom1)
    TextView mPrenom1;
    @BindView(R.id.prenom2)
    TextView mPrenom2;
    @BindView(R.id.adresse)
    TextView mAdresse;
    @BindView(R.id.telephone)
    TextView mTelephone;
    @BindView(R.id.aPropos)
    TextView mAPropos;
    @BindView(R.id.avatarDetail)
    ImageView mAvatar;
    @BindView(R.id.lienFacebook)
    TextView mLienFacebook;
    @BindView(R.id.aProposDeMoi)
    TextView mAProposDeMoi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_neighbour);
        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();

        Bundle b = getIntent().getExtras();
        String neighbourName = null;
        String neighbourAvatar = null;
        String neighbourAdresse = null;
        String neighbourTelephone = null;
        String neighbourAPropos = null;
        if(b != null) {
            neighbourName = b.getString("neighbourName");
            neighbourAvatar = b.getString("neighbourAvatar");
            neighbourAdresse = b.getString("neighbourAddress");
            neighbourTelephone = b.getString("neighbourPhoneNumber");
            neighbourAPropos = b.getString("neighbourAboutMe");
        }

        mPosition = b.getInt("neighbourPosition");
        mPrenom1.setText(neighbourName);
        mPrenom2.setText(neighbourName);
        mAdresse.setText(neighbourAdresse);
        mTelephone.setText(neighbourTelephone);
        mAPropos.setText(neighbourAPropos);
        mLienFacebook.setText("www.facebook.fr/");
        mAProposDeMoi.setText("A propos de moi");


        Glide.with(mAvatar.getContext())
                .load(neighbourAvatar)
                .override(1300, 750)
                .centerCrop()
                .into(mAvatar);


        //----- Au demarrage du détail affiche l'étoile en conséquence ----

        List<Neighbour> mNeighbourList = mApiService.getNeighbours();

        if (mApiService.isInFavourite(mNeighbourList.get(mPosition))) {
            mButtonAddFavoris.setImageResource(R.drawable.ic_star_white_24dp);
        } else {
            mButtonAddFavoris.setImageResource(R.drawable.ic_star_border_white_24dp);
        }


        mButtonAddFavoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Neighbour> mNeighbourList = mApiService.getNeighbours();

                if (!mApiService.isInFavourite(mNeighbourList.get(mPosition))) {
                    mApiService.addFavouriteNeighbour(mNeighbourList.get(mPosition));
                    mButtonAddFavoris.setImageResource(R.drawable.ic_star_white_24dp);
                } else {
                    mApiService.deleteFavouriteNeighbour(mNeighbourList.get(mPosition));
                    mButtonAddFavoris.setImageResource(R.drawable.ic_star_border_white_24dp);
                }
            }
        });


        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

}
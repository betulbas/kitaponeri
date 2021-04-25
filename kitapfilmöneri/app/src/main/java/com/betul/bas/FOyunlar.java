package com.betul.bas;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FOyunlar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FOyunlar extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FOyunlar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FOyunlar.
     */
    // TODO: Rename and change types and number of parameters
    public static FOyunlar newInstance(String param1, String param2) {
        FOyunlar fragment = new FOyunlar();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    ArrayList<OneriList> oneriList = new ArrayList<>();
    OneriAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_f_oyunlar, container, false);
        final RecyclerView recyclerView = v.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Database database = new Database(getContext());
        try{
            oneriList.clear();
            Cursor cr = database.query("Select * from OnerilerTablosu where Turu='Oyun'");
            while (cr.moveToNext()) {

                int idd = cr.getInt(0);
                String turu = cr.getString(1);
                String ismi = cr.getString(2);
                String yayinYili = cr.getString(3);
                String sayfaSayisi = cr.getString(4);
                String imdbPuani = cr.getString(5);
                String not = cr.getString(6);
                oneriList.add(new OneriList(idd,turu,ismi,yayinYili,sayfaSayisi,imdbPuani,not));
            }
            adapter = new OneriAdapter(getContext(),oneriList,detay,deleteUpdate);
            recyclerView.setAdapter(adapter);
        }catch (Exception ex){

        }

        return  v;
    }
    OneriAdapter.DeleteUpdate deleteUpdate = new OneriAdapter.DeleteUpdate() {
        @Override
        public void delUpdate(int id, String turu, String ismi, String yayinYili, String sayfaSayisi, String imdbPuani, String not) {
            FOyunlar.oneriSilGuncelle o = new FOyunlar.oneriSilGuncelle(getActivity(),id,turu,ismi,yayinYili,sayfaSayisi, imdbPuani,not);
            o.show();
        }
    };
    OneriAdapter.Detay detay = new OneriAdapter.Detay() {
        @Override
        public void onItemClick(int position) {
            Intent intent = new Intent(getContext(), OneriDetayi.class);
            intent.putExtra("turu",oneriList.get(position).getTuru());
            intent.putExtra("ismi",oneriList.get(position).getIsmi());
            intent.putExtra("yayinYili",oneriList.get(position).getYayinYili());
            intent.putExtra("sayfaSayisi",oneriList.get(position).getSayfaSayisi());
            intent.putExtra("imdbPuani",oneriList.get(position).getImdbPuani());
            intent.putExtra("not",oneriList.get(position).getNot());
            startActivity(intent);
        }
    };
    public class oneriSilGuncelle extends Dialog implements
            View.OnClickListener {

        public Activity c;
        public Dialog d;
        public Button sil, guncelle;
        String turu,ismi,yayinYili,sayfaSayisi,imdbPuani,not;
        int id;
        public ImageButton iptal;
        public oneriSilGuncelle(Activity a,int id,String turu, String ismi, String yayinYili,
                                String sayfaSayisi, String imdbPuani, String not) {
            super(a);
            this.c = a;
            this.id = id;
            this.turu = turu;
            this.ismi = ismi;
            this.yayinYili = yayinYili;
            this.sayfaSayisi = sayfaSayisi;
            this.imdbPuani = imdbPuani;
            this.not = not;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.silguncelledialog);
            sil = (Button) findViewById(R.id.sil);
            guncelle = (Button) findViewById(R.id.guncelle);
            sil.setOnClickListener(this);
            guncelle.setOnClickListener(this);
            iptal = findViewById(R.id.iptal);
            iptal.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            int i = v.getId();
            if (i == R.id.sil) {
                Database database = new Database(c);
                database.oneriDelete(id);
                c.startActivity(new Intent(getContext(), MainActivity.class));
                c.finish();
            }
            else if (i == R.id.guncelle) {
                Intent intent = new Intent(c, OneriBilgileriniGuncelle.class);
                intent.putExtra("id",id);
                intent.putExtra("ismi",ismi);
                intent.putExtra("yayinYili",yayinYili);
                intent.putExtra("sayfaSayisi",sayfaSayisi);
                intent.putExtra("imdbPuani",imdbPuani);
                intent.putExtra("not",not);
                startActivity(intent);
                c.finish();
            }
            else if(i == R.id.iptal)
                dismiss();
            dismiss();
        }
    }
}

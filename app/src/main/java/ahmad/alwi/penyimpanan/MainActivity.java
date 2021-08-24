package ahmad.alwi.penyimpanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;


public class MainActivity extends AppCompatActivity{

    private Button tambah,hapus,ubah,lihat;
    public static final String FILENAME = "namafile.txt";
    private TextView tampil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);
        tambah = findViewById(R.id.btnTambah);
        ubah = findViewById(R.id.btnUbah);
        lihat = findViewById(R.id.btnLihat);
        hapus = findViewById(R.id.btnHapus);
        tampil = findViewById(R.id.txtTampil);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isiFile = "Coba Isi Data File Text";
                File file = new File(getFilesDir(), FILENAME);

                FileOutputStream outputStream = null;
                try {
                    file.createNewFile();
                    outputStream = new FileOutputStream(file, true);
                    outputStream.write(isiFile.getBytes());
                    outputStream.flush();
                    outputStream.close();
                    Toast.makeText(getApplicationContext(), "Data Disimpan di Internal", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ubah = "Update isi data file text";
                File file = new File(getFilesDir(), FILENAME);

                FileOutputStream outputStream = null;
                try {
                    file.createNewFile();
                    outputStream = new FileOutputStream(file, false);
                    outputStream.write(ubah.getBytes());
                    outputStream.flush();
                    outputStream.close();
                    Toast.makeText(getApplicationContext(), "Data Diubah di Internal", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File sdcard = getFilesDir();
                File file = new File(sdcard, FILENAME);

                if (file.exists()) {
                    StringBuilder text = new StringBuilder();
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String line = br.readLine();
                        while (line != null) {
                            text.append(line);
                            line = br.readLine();
                        }
                        br.close();
                    } catch (IOException e) {
                        System.out.println("Error" + e.getMessage());
                    }
                    tampil.setText(text.toString());
                }
            }
        });
        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file = new File(getFilesDir(), FILENAME);
                if (file.exists()) {
                    file.delete();
                    Toast.makeText(getApplicationContext(), "Data Dihapus dari Internal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
package com.example.vmatchu;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class EnterPropertyDetailActivity extends AppCompatActivity {

    private Button btn;
    int PICK_IMAGE_MULTIPLE = 1;
    String imageEncoded;
    List<String> imagesEncodedList;
    private GridView gvGallery;
    private GalleryAdapter galleryAdapter;
    Spinner spin;
    String spin_val;
    String[] proType = { "None","Agriculture/Dairy","Apartment/Flat","Banglow/House","Commercial Plot","Commercial Portion/Office Area","Farm House","Hotel","Industrial land","Industrial Plot" ,"Land","Penthouse","Plot","Plot File","Residential Lower Portion","Residential Upper Portion","Restuarent","Shop/Showroom","villa"};//array of strings used to populate the spinner
    String[] country = {"Pakistan","India","Dubai","Iran","Iraq","Afghanistan"};

    ArrayList<String> items=new ArrayList<>();
    SpinnerDialog spinnnerDialogue;

//    String[] city = {"None\n" ,
//            "Abbottabad\n" ,
//            "Abdul Hakim\n" ,
//            "Ahmedpur East\n" ,
//            "Alipur\n" ,
//            "Arifwala\n" ,
//            "Astore\n" ,
//            "Attock\n" ,
//            "Awaran\n" ,
//            "Badin\n" ,
//            "Bagh\n" ,
//            "Bahawalnagar\n" ,
//            "Bahawalpur\n" ,
//            "Bannu\n" ,
//            "Bhakkar\n" ,
//            "Bhalwal\n" ,
//            "Bhimber\n" ,
//            "Buner\n" ,
//            "Burewala\n" ,
//            "Chaghi\n" ,
//            "Chakwal\n" ,
//            "Charsadda\n" ,
//            "Chichawatni\n" ,
//            "Chiniot\n" ,
//            "Chishtian Sharif\n" ,
//            "Chitral\n" ,
//            "Chunian\n" ,
//            "Dadu\n" ,
//            "Daharki\n" ,
//            "Daska\n" ,
//            "Depalpur\n" ,
//            "Dera Ghazi Khan\n" ,
//            "Dera Ismail Khan\n" ,
//            "Dijkot\n" ,
//            "Dina\n" ,
//            "Duniya Pur\n" ,
//            "Faisalabad\n" ,
//            "FATA\n" ,
//            "Fateh Jang\n" ,
//            "Gaarho\n" ,
//            "Gadoon\n" ,
//            "Galyat\n" ,
//            "Ghakhar\n" ,
//            "Gharo\n" ,
//            "Ghotki\n" ,
//            "Gilgit\n" ,
//            "Gojra\n" ,
//            "Gujar Khan\n" ,
//            "Gujranwala\n" ,
//            "Gujrat\n" ,
//            "Gwadar\n" ,
//            "Hafizabad\n" ,
//            "Hangu\n" ,
//            "Harappa\n" ,
//            "Haripur\n" ,
//            "Haroonabad\n" ,
//            "Hasilpur\n" ,
//            "Hassan Abdal\n" ,
//            "Haveli Lakha\n" ,
//            "Hazro\n" ,
//            "Hub (Hub Chowki)\n" ,
//            "Hunza\n" ,
//            "Hyderabad\n" ,
//            "Islamabad\n" ,
//            "Jacobabad\n" ,
//            "Jahanian\n" ,
//            "Jalalpur Jattan\n" ,
//            "Jampur\n" ,
//            "Jamshoro\n" ,
//            "Jauharabad\n" ,
//            "Jhang\n" ,
//            "Jhelum\n" ,
//            "Kaghan\n" ,
//            "Kahror Pakka\n" ,
//            "Kalat\n" ,
//            "Kamalia\n" ,
//            "Kamoki\n" ,
//            "Karachi\n" ,
//            "Karak\n" ,
//            "Kasur\n" ,
//            "Khairpur\n" ,
//            "Khanewal\n" ,
//            "Khanpur\n" ,
//            "Kharian\n" ,
//            "Khipro\n" ,
//            "Khushab\n" ,
//            "Khuzdar\n" ,
//            "Kohat\n" ,
//            "Kot Addu\n" ,
//            "Kotli\n" ,
//            "Kotri\n" ,
//            "Lahore\n" ,
//            "Lakki Marwat\n" ,
//            "Lalamusa\n" ,
//            "Larkana\n" ,
//            "Lasbela\n" ,
//            "Layyah\n" ,
//            "Liaquatpur\n" ,
//            "Lodhran\n" ,
//            "Loralai\n" ,
//            "Lower Dir\n" ,
//            "Mailsi\n" ,
//            "Makran\n" ,
//            "Malakand\n" ,
//            "Mandi Bahauddin\n" ,
//            "Mansehra\n" ,
//            "Mardan\n" ,
//            "Matiari\n" ,
//            "Mian Channu\n" ,
//            "Mianwali\n" ,
//            "Mirpur\n" ,
//            "Mirpur Khas\n" ,
//            "Mirpur Sakro\n" ,
//            "Multan\n" ,
//            "Muridke\n" ,
//            "Murree\n" ,
//            "Muzaffarabad\n" ,
//            "Muzaffargarh\n" ,
//            "Nankana Sahib\n" ,
//            "Naran\n" ,
//            "Narowal\n" ,
//            "Nasirabad\n" ,
//            "Naushahro Feroze\n" ,
//            "Nawabshah\n" ,
//            "Neelum\n" ,
//            "Nowshera\n" ,
//            "Okara\n" ,
//            "Others\n" ,
//            "Others Azad Kashmir\n" ,
//            "Others Balochistan\n" ,
//            "Others Gilgit Baltistan\n" ,
//            "Others Khyber Pakhtunkhwa\n" ,
//            "Others Punjab\n" ,
//            "Others Sindh\n" ,
//            "Pakpattan\n" ,
//            "Peshawar\n" ,
//            "Pind Dadan Khan\n" ,
//            "Pindi Bhattian\n" ,
//            "Pir Mahal\n" ,
//            "Quetta\n" ,
//            "Rahim Yar Khan\n" ,
//            "Rajanpur\n" ,
//            "Ratwal\n" ,
//            "Rawalkot\n" ,
//            "Rohri\n" ,
//            "Sadiqabad\n" ,
//            "Sahiwal\n" ,
//            "Samundri Sanghar\n" ,
//            "Sarai Alamgir\n" ,
//            "Sargodha\n" ,
//            "Sehwan\n" ,
//            "Shabqadar\n" ,
//            "Shahdadpur\n" ,
//            "Shahkot\n" ,
//            "Shakargarh\n" ,
//            "Sheikhupura\n" ,
//            "Shikarpur\n" ,
//            "Shorkot\n" ,
//            "Sialkot\n" ,
//            "Sibi\n" ,
//            "Skardu\n" ,
//            "Sudhnoti\n" ,
//            "Sukkur\n" ,
//            "Swabi\n" ,
//            "Swat\n" ,
//            "Talagang\n" ,
//            "Tando Adam\n" ,
//            "Tando Allahyar\n" ,
//            "Tando Bago\n" ,
//            "Tando Muhammad Khan\n" ,
//            "Taxila\n" ,
//            "Tharparkar\n" ,
//            "Thatta\n" ,
//            "Toba Tek Singh\n" ,
//            "Turbat\n" ,
//            "Vehari\n" ,
//            "Wah\n" ,
//            "Wazirabad\n" ,
//            "Waziristan\n" ,
//            "Yazman\n" ,
//            "Zhob"};
//    String[] area = {"Abdullah Haroon Road\n" ,
//            "Bahria Town\n" ,
//            "Defense Housing Authority\n" ,
//            "Federal B Area\n" ,
//            "Gulishan e Iqbal\n" ,
//            "Gulistan e Johar\n" ,
//            "North Nazimabad\n" ,
//            "Scheme 1\n" ,
//            "Scheme 24\n" ,
//            "Scheme 33\n" ,
//            "Scheme 45"};
//    String[] subArea = {"\n" ,
//            "125sqyds unballoted commercial\n" ,
//            "200 sqyds unballoted commercial\n" ,
//            "Abdullah Shah Ghazi Town\n" ,
//            "Abul Hasan Isphani Road\n" ,
//            "Abuzar Society\n" ,
//            "Ahsan Garden & Ahsan Grand City\n" ,
//            "Ahsanabad\n" ,
//            "Ajmer Moinia Society\n" ,
//            "Al Habib Society\n" ,
//            "Al muslim society\n" ,
//            "AL-Mehran Society\n" ,
//            "Al-noor Multipurpose Cooperating housing society\n" ,
//            "ALIG town\n" ,
//            "Allama Usmania Society\n" ,
//            "Amna Cooperative Society\n" ,
//            "Andaleeb Co-operative Housing Society\n" ,
//            "Areesha Society\n" ,
//            "ASF Housing Society\n" ,
//            "Atomic energy Society\n" ,
//            "Attawa Society\n" ,
//            "AYUB goth\n" ,
//            "Azeemabad\n" ,
//            "Bagh-e-Malir\n" ,
//            "Bahria Apartment\n" ,
//            "Bahria Green Valley\n" ,
//            "Bahria Paradise\n" ,
//            "Bahria Sports city\n" ,
//            "Bahria Town Commercial\n" ,
//            "Bahria Town Old\n" ,
//            "Bahria Town Villa\n" ,
//            "bahria-sport-city-precinct-36\n" ,
//            "bahria-sport-city-precinct-38\n" ,
//            "bahria-sport-city-precinct-40\n" ,
//            "Bantva Memon Cooperative Housing Society\n" ,
//            "Billys Green Wood residency\n" ,
//            "Capital Society\n" ,
//            "Chappal Town\n" ,
//            "CORNICHE CHS\n" ,
//            "Cotton Export CHS\n" ,
//            "Custom CHS\n" ,
//            "Dehli Mercentaile Society\n" ,
//            "Dehli Riyan Cooperating housing society\n" ,
//            "Dehli Sodagran Society\n" ,
//            "DHA - Phase 1\n" ,
//            "DHA - Phase 2\n" ,
//            "DHA - Phase 2 Extension\n" ,
//            "DHA - Phase 3\n" ,
//            "DHA - Phase 4\n" ,
//            "DHA - Phase 5\n" ,
//            "DHA - Phase 6\n" ,
//            "DHA - Phase 7\n" ,
//            "DHA - Phase 7 Extension\n" ,
//            "DHA - Phase 8\n" ,
//            "DHA - Phase 9\n" ,
//            "Faridi Niazi Society\n" ,
//            "Fatima Down town\n" ,
//            "Federal B Area - Block 1\n" ,
//            "Federal B Area - Block 10\n" ,
//            "Federal B Area - Block 11\n" ,
//            "Federal B Area - Block 12\n" ,
//            "Federal B Area - Block 13\n" ,
//            "Federal B Area - Block 14\n" ,
//            "Federal B Area - Block 15\n" ,
//            "Federal B Area - Block 16\n" ,
//            "Federal B Area - Block 17\n" ,
//            "Federal B Area - Block 18\n" ,
//            "Federal B Area - Block 19\n" ,
//            "Federal B Area - Block 2\n" ,
//            "Federal B Area - Block 20\n" ,
//            "Federal B Area - Block 21\n" ,
//            "Federal B Area - Block 22\n" ,
//            "Federal B Area - Block 3\n" ,
//            "Federal B Area - Block 4\n",
//            "Federal B Area - Block 5\n" ,
//            "Federal B Area - Block 6\n" ,
//            "Federal B Area - Block 7\n" ,
//            "Federal B Area - Block 8\n" ,
//            "Federal B Area - Block 9\n" ,
//            "Firdous Society\n" ,
//            "Frere Excellency\n" ,
//            "Gandhara Society\n" ,
//            "Garden City\n" ,
//            "Gawliar Cooperating housing society\n" ,
//            "Ghausia Society\n" ,
//            "Gulistan Society\n" ,
//            "Gulistan-e-Johar - Block 1\n" ,
//            "Gulistan-e-Johar - Block 1 A\n" ,
//            "Gulistan-e-Johar - Block 10\n" ,
//            "Gulistan-e-Johar - Block 11\n" ,
//            "Gulistan-e-Johar - Block 12\n" ,
//            "Gulistan-e-Johar - Block 13\n" ,
//            "Gulistan-e-Johar - Block 13 B\n" ,
//            "Gulistan-e-Johar - Block 14\n" ,
//            "Gulistan-e-Johar - Block 15\n" ,
//            "Gulistan-e-Johar - Block 16\n" ,
//            "Gulistan-e-Johar - Block 17\n" ,
//            "Gulistan-e-Johar - Block 18\n" ,
//            "Gulistan-e-Johar - Block 19\n" ,
//            "Gulistan-e-Johar - Block 2\n",
//            "Gulistan-e-Johar - Block 20\n" ,
//            "Gulistan-e-Johar - Block 3\n" ,
//            "Gulistan-e-Johar - Block 3 A\n" ,
//            "Gulistan-e-Johar - Block 4\n" ,
//            "Gulistan-e-Johar - Block 5\n" ,
//            "Gulistan-e-Johar - Block 6\n" ,
//            "Gulistan-e-Johar - Block 7\n" ,
//            "Gulistan-e-Johar - Block 8\n" ,
//            "Gulistan-e-Johar - Block 9\n" ,
//            "Gulistan-e-Johar - Block 9 A\n" ,
//            "Gulistan-e-Johar - Block 9 B\n" ,
//            "Gulshan e Mehran\n" ,
//            "Gulshan Muhammad\n" ,
//            "Gulshan Society\n" ,
//            "Gulshan-e Rehman\n" ,
//            "Gulshan-e-Areesha\n" ,
//            "Gulshan-e-Azeem\n" ,
//            "Gulshan-e-Dozan\n" ,
//            "Gulshan-e-Elahi\n" ,
//            "Gulshan-e-Ghazian\n" ,
//            "Gulshan-e-Iqbal - Block 1\n" ,
//            "Gulshan-e-Iqbal - Block 10\n" ,
//            "Gulshan-e-Iqbal - Block 10 A\n" ,
//            "Gulshan-e-Iqbal - Block 11\n" ,
//            "Gulshan-e-Iqbal - Block 12\n" ,
//            "Gulshan-e-Iqbal - Block 13\n" ,
//            "Gulshan-e-Iqbal - Block 13 A\n" ,
//            "Gulshan-e-Iqbal - Block 13 B\n" ,
//            "Gulshan-e-Iqbal - Block 13 C\n" ,
//            "Gulshan-e-Iqbal - Block 13 D\n" ,
//            "Gulshan-e-Iqbal - Block 13 D-1\n" ,
//            "Gulshan-e-Iqbal - Block 13 D-2\n" ,
//            "Gulshan-e-Iqbal - Block 13 D-3\n" ,
//            "Gulshan-e-Iqbal - Block 13 E\n" ,
//            "Gulshan-e-Iqbal - Block 13 G\n" ,
//            "Gulshan-e-Iqbal - Block 14\n" ,
//            "Gulshan-e-Iqbal - Block 15\n" ,
//            "Gulshan-e-Iqbal - Block 16\n" ,
//            "Gulshan-e-Iqbal - Block 17\n" ,
//            "Gulshan-e-Iqbal - Block 18\n" ,
//            "Gulshan-e-Iqbal - Block 19\n" ,
//            "Gulshan-e-Iqbal - Block 2\n" ,
//            "Gulshan-e-Iqbal - Block 3\n" ,
//            "Gulshan-e-Iqbal - Block 4\n" ,
//            "Gulshan-e-Iqbal - Block 4 A\n" ,
//            "Gulshan-e-Iqbal - Block 5\n" ,
//            "Gulshan-e-Iqbal - Block 6\n" ,
//            "Gulshan-e-Iqbal - Block 7\n" ,
//            "Gulshan-e-Iqbal - Block 9\n" ,
//            "Gulshan-e-Iqbal – Block 8\n" ,
//            "Gulshan-e-Jami\n" ,
//            "Gulshan-e-Kaneez fatima society\n" ,
//            "Gulshan-e-Maymar\n" ,
//            "Gulshan-e-Mehmood Ul Haq CHS\n" ,
//            "Gulshan-e-Roomi\n" ,
//            "Gulshan-e-Sachal\n" ,
//            "Gulshan-e-Sakina Fatima Society\n" ,
//            "Gulshan-e-Shameem\n" ,
//            "Gulshan-e-Umair\n" ,
//            "Gulshan-e-Usman\n" ,
//            "Hadiabad Society\n" ,
//            "Hakeem villas\n" ,
//            "Halar Nagar Society\n" ,
//            "Hansa Society\n" ,
//            "Hashamabad\n" ,
//            "High ourt society\n" ,
//            "Inara Garden\n" ,
//            "Incholi Cooperating Housing society\n" ,
//            "Income Tax Employees Cooperating Housing society\n" ,
//            "K.D.A employees society\n" ,
//            "K.E.S.C society\n" ,
//            "Kaghan Society\n" ,
//            "Karachi Bar Cooperating Housing society\n" ,
//            "Karachi central information\n" ,
//            "Karachi memon CHS\n" ,
//            "Karachi revenue judicial CHS\n" ,
//            "Karachi University Society\n" ,
//            "Karim Bhai Society\n" ,
//            "Khaskheli Goth\n" ,
//            "Khorasan CHS\n" ,
//            "Madina colony\n" ,
//            "Madras Society\n" ,
//            "Main jinnah avenue commercial\n" ,
//            "Meerut Society\n" ,
//            "Meharan Banglows\n" ,
//            "Memon Nagar\n" ,
//            "Midway commercial\n" ,
//            "Muhammad Khan Colony\n" ,
//            "Musalman-e-Punjab-Cooperating-Society\n" ,
//            "Muslim city\n" ,
//            "National housing society\n" ,
//            "NEK society\n" ,
//            "New Okhai Memon Welfare Society\n" ,
//            "NIPA society\n" ,
//            "North Nazimabad - Block 1\n" ,
//            "North Nazimabad - Block 2\n" ,
//            "North Nazimabad - Block 3\n" ,
//            "North Nazimabad - Block 4\n" ,
//            "North Nazimabad - Block 5\n" ,
//            "North Nazimabad - Block 6\n" ,
//            "North Nazimabad - Block 7\n" ,
//            "North Nazimabad - Block 8\n" ,
//            "North Nazimabad - Block 9\n" ,
//            "North Nazimabad - Block A\n" ,
//            "North Nazimabad - Block B\n" ,
//            "North Nazimabad - Block C\n" ,
//            "North Nazimabad - Block D\n" ,
//            "North Nazimabad - Block E\n" ,
//            "North Nazimabad - Block F\n" ,
//            "North Nazimabad - Block G\n" ,
//            "North Nazimabad - Block H\n" ,
//            "North Nazimabad - Block I\n" ,
//            "North Nazimabad - Block J\n" ,
//            "North Nazimabad - Block K\n" ,
//            "North Nazimabad - Block L\n" ,
//            "North Nazimabad - Block M\n" ,
//            "North Nazimabad - Block N\n" ,
//            "North Nazimabad - Block O\n" ,
//            "North Nazimabad - Block P\n" ,
//            "North Nazimabad - Block Q\n" ,
//            "North Nazimabad - Block R\n" ,
//            "North Nazimabad - Block S\n" ,
//            "North Nazimabad - Block T\n" ,
//            "North Nazimabad - Block U\n" ,
//            "ODA Rajput Muhalla\n" ,
//            "PAEC society\n" ,
//            "PAF housing Scheme\n" ,
//            "PAK Punjum society\n" ,
//            "Pakistan Audit Department CHS\n" ,
//            "Pakistan Navy Society\n" ,
//            "Pakistan Scientist Society\n" ,
//            "PCSIR society\n" ,
//            "Petal Avenue\n" ,
//            "PIA Society\n" ,
//            "PIDC Society\n" ,
//            "Pilibhiet CHS\n" ,
//            "Pir ahmed zaman town\n" ,
//            "Pir Gul Hassan Town\n" ,
//            "Police Society\n" ,
//            "Post office Society\n" ,
//            "Precinct 10 commercial\n" ,
//            "Prince town\n" ,
//            "Punjabi Saudagran Society\n" ,
//            "Qaimabad City\n" ,
//            "Quetta Town\n" ,
//            "Qureshi Society\n" ,
//            "Rab Razi Society\n" ,
//            "Radio Pakistan Society\n" ,
//            "Rafiq Center\n" ,
//            "Rashid Minhas Road\n" ,
//            "Rehman villas\n" ,
//            "Rimjhim tower\n" ,
//            "Rimjhim villas\n" ,
//            "ROK Cooperating Housing Society\n" ,
//            "Rufi global city\n" ,
//            "Rufi Merry land\n" ,
//            "Saadi garden\n" ,
//            "Saadi town\n" ,
//            "Sachal Sarmast Society\n" ,
//            "Sadaat-e-Amroha Society\n" ,
//            "SADAF CHS\n" ,
//            "Saddabad housing society\n" ,
//            "Safari enclave\n" ,
//            "Safari Palm City\n" ,
//            "Sahafee Society\n" ,
//            "Saharanpur Cooperating Society\n" ,
//            "Saima jinnah Avenue\n" ,
//            "SBP society\n" ,
//            "Shaheen Enclave\n" ,
//            "Shahwilayat Cooperative Housing Society\n" ,
//            "Sindh Bagh Society\n" ,
//            "Sohrab Gabol Goth\n" ,
//            "Soomra society\n" ,
//            "Staff Town\n" ,
//            "State Bank of Pakistan Housing Society\n" ,
//            "Sumaira Banglows\n" ,
//            "Sumaira Sky Towers\n" ,
//            "Surti Muslim Society\n" ,
//            "Teachers Society\n" ,
//            "Telephone Employees CHS\n" ,
//            "Theme Park Commercial\n" ,
//            "Tipu Sultan CHS\n" ,
//            "Usmani town\n" ,
//            "Vanthali Memon Society\n" ,
//            "Writers Guild Society\n" ,
//            "Yar Muhammad goth\n" ,
//            "Zakaria goth\n" ,
//            "Zeenatabad Society"};
//    String[] sector = {"10th Commercial Street - Phase 4\n" +
//            "9th Commercial Street - Phase 4\n" +
//            "A.one Comforts\n" +
//            "Abbas Residency\n" +
//            "Abdullah Ahmed Road\n" +
//            "Abdullah Appartment\n" +
//            "Abdullah heights\n" +
//            "Abdullah Terrace\n" +
//            "Abid plaza\n" +
//            "Abid town\n" +
//            "Abuzar Society - Block 1\n" +
//            "Abuzar Society - Block 2\n" +
//            "Abuzar Society - Sector 53-A\n" +
//            "Adnan Shan Square - Block 13 C\n" +
//            "Aenan Arcade\n" +
//            "Afnan residency - Block 3\n" +
//            "Afnan residency - Block 3 A\n" +
//            "Ahmad Comforts\n" +
//            "Ahmad residency\n" +
//            "Ahsanabad - Sector 1\n" +
//            "Ahsanabad - Sector 2\n" +
//            "Ahsanabad - Sector 3\n" +
//            "Ahsanabad - Sector 4\n" +
//            "Ahsanabad - Sector 5\n" +
//            "Ahsanabad - Sector 6\n" +
//            "Ajmer pride\n" +
//            "Al ahmed Heights\n" +
//            "Al Azmat\n" +
//            "Al khizra heights\n" +
//            "Al Minal Tower\n" +
//            "Al Murtaza Commercial Area - Phase 8\n" +
//            "Al muslim society - Sector 34-A\n" +
//            "Al Quuddose - Block 13 C\n" +
//            "Al Salman - Block 13 C\n" +
//            "Al Unique Centre\n" +
//            "Al-Ahad pride\n" +
//            "Al-Ahram - Block 13 B\n" +
//            "Al-Azam - Block 13 B\n" +
//            "Al-Azmat Appartment\n" +
//            "Al-Azmat Plaza\n" +
//            "Al-Hassan Appartment\n" +
//            "Al-Manzar Appartment\n" +
//            "Al-Masood heights\n" +
//            "Al-Mustafa Appartment\n" +
//            "Al-noor housing society - Sector 54-A\n" +
//            "Al-Rizwan\n" +
//            "Al-Shams Complex\n" +
//            "Al-Shams Plaza\n" +
//            "Al-Syed Arcade\n" +
//            "Al-syed heights\n" +
//            "Alam nagar\n" +
//            "Alamgir Pride\n" +
//            "Aleem Centre\n" +
//            "Ali Appartment\n" +
//            "Allah noor Apartments\n" +
//            "Alliance Arcade\n" +
//            "Alpine plaza\n" +
//            "Alpine tower\n" +
//            "Ambreen Apartment\n" +
//            "Ameer Complex\n" +
//            "Ancholi Cooperating Housing society - Sector 24 A\n" +
//            "Apsara Appartment\n" +
//            "Aramish Banglows\n" +
//            "Areesha Society - Sector 6-A\n" +
//            "Arif luxury Appartments\n" +
//            "Arif terrace\n" +
//            "Ashraf square\n" +
//            "Ayaz town\n" +
//            "Ayubi Commercial Area - Phase 7 ext\n" +
//            "Aziz Square\n" +
//            "Bab-e-Rehmat - Block 13 B\n" +
//            "Badar Commercial Area - Phase 5\n" +
//            "Bagh-e-Malir - Block A\n" +
//            "Bahria Apartment\n" +
//            "Bahria Apartment - 2 bed\n" +
//            "Bahria Apartment - 3 bed\n" +
//            "Bahria Apartment - 4 bed\n" +
//            "Bahria Green Valley - Block A\n" +
//            "Bahria Green Valley - Block B\n" +
//            "Bahria Green Valley - Block C\n" +
//            "Bahria Green Valley - Block D\n" +
//            "Bahria Heights - Tower 4\n" +
//            "Bahria Heights - Tower 7\n" +
//            "Bahria Heights - Tower A\n" +
//            "Bahria Heights - Tower B\n" +
//            "Bahria Heights - Tower C\n" +
//            "Bahria Heights - Tower D\n" +
//            "Bahria Heights - Tower E\n" +
//            "Bahria Heights - Tower F\n" +
//            "Bahria Heights - Tower G\n" +
//            "Bahria Heights - Tower H\n" +
//            "Bahria Heights - Tower I\n" +
//            "Bahria Heights - Tower J\n" +
//            "Bahria Heights - Tower K\n" +
//            "Bahria Heights - Tower L\n" +
//            "Bahria Sport city - Precinct 34\n" +
//            "Bahria Sport city - Precinct 35\n" +
//            "Bahria Sport city - Precinct 36\n" +
//            "Bahria Sport city - Precinct 37\n" +
//            "Bahria Sport city - Precinct 38\n" +
//            "Bahria Sport city - Precinct 39\n" +
//            "Bahria Sport city - Precinct 40\n" +
//            "Bahria Sport city - Precinct 41\n" +
//            "Bahria Sport city - Precinct 42\n" +
//            "Bahria Sport city - Precinct 43\n" +
//            "Bahria Sport city - Precinct 44\n" +
//            "Bahria Sport city - Precinct 45\n" +
//            "Bahria Town Karachi - Precinct 1\n" +
//            "Bahria Town Karachi - Precinct 10\n" +
//            "Bahria Town Karachi - Precinct 10 A\n" +
//            "Bahria Town Karachi - Precinct 10 B\n" +
//            "Bahria Town Karachi - Precinct 11\n" +
//            "Bahria Town Karachi - Precinct 11 A\n" +
//            "Bahria Town Karachi - Precinct 11 B\n" +
//            "Bahria Town Karachi - Precinct 12\n" +
//            "Bahria Town Karachi - Precinct 13\n" +
//            "Bahria Town Karachi - Precinct 14\n" +
//            "Bahria Town Karachi - Precinct 15\n" +
//            "Bahria Town Karachi - Precinct 15 B\n" +
//            "Bahria Town Karachi - Precinct 16\n" +
//            "Bahria Town Karachi - Precinct 17\n" +
//            "Bahria Town Karachi - Precinct 18\n" +
//            "Bahria Town Karachi - Precinct 19\n" +
//            "Bahria Town Karachi - Precinct 2\n" +
//            "Bahria Town Karachi - Precinct 20\n" +
//            "Bahria Town Karachi - Precinct 21\n" +
//            "Bahria Town Karachi - Precinct 22\n" +
//            "Bahria Town Karachi - Precinct 23\n" +
//            "Bahria Town Karachi - Precinct 24\n" +
//            "Bahria Town Karachi - Precinct 25\n" +
//            "Bahria Town Karachi - Precinct 25 A\n" +
//            "Bahria Town Karachi - Precinct 26\n" +
//            "Bahria Town Karachi - Precinct 26 A\n" +
//            "Bahria Town Karachi - Precinct 27\n" +
//            "Bahria Town Karachi - Precinct 27 A\n" +
//            "Bahria Town Karachi - Precinct 28\n" +
//            "Bahria Town Karachi - Precinct 29\n" +
//            "Bahria Town Karachi - Precinct 29 A\n" +
//            "Bahria Town Karachi - Precinct 29 B\n" +
//            "Bahria Town Karachi - Precinct 29 C\n" +
//            "Bahria Town Karachi - Precinct 29 D\n" +
//            "Bahria Town Karachi - Precinct 3\n" +
//            "Bahria Town Karachi - Precinct 30\n" +
//            "Bahria Town Karachi - Precinct 30 A\n" +
//            "Bahria Town Karachi - Precinct 30 B\n" +
//            "Bahria Town Karachi - Precinct 30 C\n" +
//            "Bahria Town Karachi - Precinct 30 D\n" +
//            "Bahria Town Karachi - Precinct 31\n" +
//            "Bahria Town Karachi - Precinct 31 A\n" +
//            "Bahria Town Karachi - Precinct 31 B\n" +
//            "Bahria Town Karachi - Precinct 31 C\n" +
//            "Bahria Town Karachi - Precinct 31 D\n" +
//            "Bahria Town Karachi - Precinct 32\n" +
//            "Bahria Town Karachi - Precinct 32 A\n" +
//            "Bahria Town Karachi - Precinct 32 B\n" +
//            "Bahria Town Karachi - Precinct 32 C\n" +
//            "Bahria Town Karachi - Precinct 32 D\n" +
//            "Bahria Town Karachi - Precinct 33\n" +
//            "Bahria Town Karachi - Precinct 33 A\n" +
//            "Bahria Town Karachi - Precinct 33 B\n" +
//            "Bahria Town Karachi - Precinct 33 C\n" +
//            "Bahria Town Karachi - Precinct 33 D\n" +
//            "Bahria Town Karachi - Precinct 4\n" +
//            "Bahria Town Karachi - Precinct 5\n" +
//            "Bahria Town Karachi - Precinct 6\n" +
//            "Bahria Town Karachi - Precinct 7\n" +
//            "Bahria Town Karachi - Precinct 8\n" +
//            "Bahria Town Karachi - Precinct 9\n" +
//            "Bahria Town Karachi – Precinct 15 A\n" +
//            "Bahria Town Paradise - Precinct 46\n" +
//            "Bahria Town Paradise - Precinct 47\n" +
//            "Bahria Town Paradise - Precinct 48\n" +
//            "Bahria Town Paradise - Precinct 49\n" +
//            "Bahria Town Paradise - Precinct 50\n" +
//            "Bahria Town Paradise - Precinct 51\n" +
//            "Bahria Town Paradise - Precinct 52\n" +
//            "Bahria Town Paradise - Precinct 53\n" +
//            "Bahria Town Paradise - Precinct 54\n" +
//            "Bahria Town Paradise - Precinct 55\n" +
//            "Bahria Town Paradise - Precinct 56\n" +
//            "Bahria Town Paradise - Precinct 57\n" +
//            "Bait-ul-Hina Appartments\n" +
//            "Baseera Appartment\n" +
//            "Beely View\n" +
//            "Bell centre\n" +
//            "Billy's Heights\n" +
//            "Billy's Paradise phase 1\n" +
//            "Billy's Paradise phase 2\n" +
//            "Billy's Shopping galleria\n" +
//            "Billys Tower\n" +
//            "Bisma Avenue\n" +
//            "Bisma Garden\n" +
//            "Bisma residency\n" +
//            "Block 1 - Ahsan Garden\n" +
//            "Block 34 - Phase 5\n" +
//            "Block A - Ahsan Garden\n" +
//            "Block A - Midway commercial\n" +
//            "Block B - Ahsan Garden\n" +
//            "Block B - Midway commercial\n" +
//            "Bukhari Appartment\n" +
//            "Bukhari Commercial Area - Phase 6\n" +
//            "Capital Society - Sector 35-A\n" +
//            "Carseen Complex\n" +
//            "Castle Datari\n" +
//            "Central government Cooperating H.S\n" +
//            "Chappal Luxury\n" +
//            "City tower\n" +
//            "Classic\n" +
//            "Classic Paradise\n" +
//            "Commissioner CHS - Phase 7 ext\n" +
//            "Corniche Comforts\n" +
//            "Cosy House - Block 13 B\n" +
//            "Cotton Export CHS - Block B\n" +
//            "Cotton Export CHS - Sector 51-A\n" +
//            "Creek Vista - Zone B\n" +
//            "Cresent view\n" +
//            "Crisan Comlaint\n" +
//            "Crisan square\n" +
//            "Crystal Homes\n" +
//            "Crystal view - Block 1\n" +
//            "Crystal view - Block 1 A\n" +
//            "Darakshan Villas - Phase 6\n" +
//            "Darul Islam Pride\n" +
//            "Decent Appartments\n" +
//            "Decent Arcade\n" +
//            "Decent Garden\n" +
//            "Decent tower\n" +
//            "Decent view\n" +
//            "Defense Hosuing Authority - Phase 6\n" +
//            "Delhi Riyan CHS - Sector 51-A\n" +
//            "Diamond Arcade - Block 13 D-1\n" +
//            "Eastern Pride\n" +
//            "Elite Residency - Block 13 D-2\n" +
//            "Emaar Crescent Bay - Phase 8\n" +
//            "Empire center\n" +
//            "Empire centre\n" +
//            "Erum Appartments\n" +
//            "Erum Center - Block 16\n" +
//            "Erum heights\n" +
//            "Erum Shopping mall\n" +
//            "Erum Square\n" +
//            "Erum Villas\n" +
//            "Essa Nagri\n" +
//            "Euro heights\n" +
//            "Ever shine - Block 10\n" +
//            "Ever shine - Block 10 A\n" +
//            "Evique Trust C.H.S\n" +
//            "F.I.A Police\n" +
//            "Faaran centre - Block 3\n" +
//            "Faaran centre - Block 3 A\n" +
//            "Falaknaz\n" +
//            "Faraz Avenue\n" +
//            "Faraz View\n" +
//            "Fareeda Square\n" +
//            "Farhan Classic\n" +
//            "Farhan dreamland\n" +
//            "Farhan heaven\n" +
//            "Farhan Paradise\n" +
//            "Farhan Towers\n" +
//            "Five star centre\n" +
//            "Five star Complex\n" +
//            "Friends tower\n" +
//            "Gallant court\n" +
//            "Gallant Summits\n" +
//            "Garden City - Block A\n" +
//            "Garden City - Block B\n" +
//            "Garden City - Block C\n" +
//            "Garden City - Block D\n" +
//            "Garden City - Block E\n" +
//            "Garden City - Block F\n" +
//            "Garden City - Block G\n" +
//            "Garden City - Block H\n" +
//            "Garden City - Block I\n" +
//            "Garden City - Block J\n" +
//            "Ghazian Arcade - Block 1\n" +
//            "Glamour tower - Block 13 D-2\n" +
//            "Glamour view 1 - Block 1\n" +
//            "Glamour view 1 - Block 1 A\n" +
//            "Glamour view 2 - Block 1\n" +
//            "Glamour view 2 - Block 1 A\n" +
//            "Gohar pride\n" +
//            "Golden Heights\n" +
//            "GoodFrath Blessing - Block 4\n" +
//            "GoodFrath Blessing - Block 4 A\n" +
//            "Goth Manu\n" +
//            "Goth Noor Muhammad\n" +
//            "Grace tower\n" +
//            "Gray garden\n" +
//            "Gray Heights\n" +
//            "Gulberg Square\n" +
//            "Gulistan Society - Phase 1\n" +
//            "Gulistan Society - Phase 2\n" +
//            "Gulshan Appartment\n" +
//            "Gulshan banglows\n" +
//            "Gulshan complex\n" +
//            "Gulshan Complex 1\n" +
//            "Gulshan Complex 2\n" +
//            "Gulshan Luxury Homes - Block 13 B\n" +
//            "Gulshan Plaza - Block 13 B\n" +
//            "Gulshan Society - Block 13\n" +
//            "Gulshan-e- Mehran Block 1-A\n" +
//            "Gulshan-e- Mehran Block 1-A/1\n" +
//            "Gulshan-e- Mehran Block 1-B\n" +
//            "Gulshan-e- Mehran Block 1-C\n" +
//            "Gulshan-e- Mehran Block 1-D\n" +
//            "Gulshan-e- Mehran Block 2-A\n" +
//            "Gulshan-e- Mehran Block 2-B\n" +
//            "Gulshan-e- Mehran Block 2-C\n" +
//            "Gulshan-e- Mehran Block 2-D\n" +
//            "Gulshan-e- Mehran Block 3-A\n" +
//            "Gulshan-e- Mehran Block 3-B\n" +
//            "Gulshan-e- Mehran Block 3-C\n" +
//            "Gulshan-e- Mehran Block 3-D\n" +
//            "Gulshan-e- Mehran Block 4-A\n" +
//            "Gulshan-e- Mehran Block 4-B\n" +
//            "Gulshan-e- Mehran Block 4-C\n" +
//            "Gulshan-e- Mehran Block 4-D\n" +
//            "Gulshan-e-Aisha Appartments\n" +
//            "Gulshan-e-Amin tower\n" +
//            "Gulshan-e-Hyder Appartments\n" +
//            "Gulshan-e-Kaneez fatima society - Block 1\n" +
//            "Gulshan-e-Kaneez fatima society - Block 4\n" +
//            "Gulshan-e-Maymar - Sector Y2\n" +
//            "Gulshan-e-Mehmood Ul Haq CHS - Block 21\n" +
//            "Gulshan-e-Shameem - Block 9\n" +
//            "Habib blessing - Block 13 D-2\n" +
//            "Halar Nagar Society - Block A\n" +
//            "Hamid square\n" +
//            "Harmain Royal appartment\n" +
//            "Harmain Towers\n" +
//            "Haroon royal city\n" +
//            "Hasan Appartment - Block 13 B\n" +
//            "Hassan Square - Block 13 B\n" +
//            "Hijra Manzil\n" +
//            "Hill view\n" +
//            "Hill view KDA flats\n" +
//            "Hina Banglows\n" +
//            "Hina Garden\n" +
//            "Hinna Comm\n" +
//            "HMH Square\n" +
//            "Hoor Paliace\n" +
//            "Huma arcade\n" +
//            "Hunaid City\n" +
//            "Hyder plaza\n" +
//            "Imtiaz Square\n" +
//            "Iqbal villas\n" +
//            "Iqra Complex\n" +
//            "Islamic Appartment\n" +
//            "Ittehad Arcade\n" +
//            "Ittehad Commercial Area - Phase 6\n" +
//            "Jabl-e-rehmat tower\n" +
//            "Jami Staff Lanes - Phase 2 ext\n" +
//            "Jami Staff Lanes 1 - Phase 2 ext\n" +
//            "Jami Staff Lanes 2 - Phase 2 ext\n" +
//            "Jauhar Belle Appartment\n" +
//            "Jauhar Heights\n" +
//            "Jauhar view\n" +
//            "Javed arcade\n" +
//            "Javed hill view\n" +
//            "Jouhar Square\n" +
//            "Junaid Plaza\n" +
//            "K.D.A flats\n" +
//            "K.D.A flats - Block 13 C\n" +
//            "K.D.A red flats\n" +
//            "K.D.A staff Quarters\n" +
//            "Kamran Market\n" +
//            "Karachi Appartment\n" +
//            "Karachi University Society - Block B\n" +
//            "Kareem Plaza\n" +
//            "Kashana Appartment\n" +
//            "KDA employees society - Sector 15-A\n" +
//            "KDA flats\n" +
//            "Kehkashan Terrace\n" +
//            "Khalid Commercial Area - Phase 7 ext\n" +
//            "Khayaban e Sadi - Phase 7\n" +
//            "Khayaban-e-Muhafiz - Phase 6\n" +
//            "Khteban-e-Erum - Block 13 B\n" +
//            "Kings cottages\n" +
//            "Kings Residency\n" +
//            "Kings tower\n" +
//            "Kings trade centre - Block 3\n" +
//            "Kings trade centre - Block 3 A\n" +
//            "Komal view\n" +
//            "La'mour View\n" +
//            "Lateef Plaza\n" +
//            "Latif cooperating housing society\n" +
//            "Layreb garden\n" +
//            "Liberty Terrace\n" +
//            "Luncky Centre - Block 4\n" +
//            "Luncky Centre - Block 4 A\n" +
//            "Madina Comforts\n" +
//            "Madras Society - Sector 17/A\n" +
//            "Maheen Lake city\n" +
//            "Manan Arcade\n" +
//            "Manila Centre - Block 13 D-3\n" +
//            "Maymar Arcade - Block 13 B\n" +
//            "Maymar drive - Block 10\n" +
//            "Maymar drive - Block 10 A\n" +
//            "Maymar Garden - Block 13 B\n" +
//            "Maymar Heights\n" +
//            "Maymar plaza Phase1\n" +
//            "Maymar Square\n" +
//            "Maymar terrace\n" +
//            "Maymar View\n" +
//            "Meerut Society - Sector 8-A\n" +
//            "Mehran Market - Block 13 D-1\n" +
//            "Memon cottage - Block 1\n" +
//            "Memon cottage - Block 1 A\n" +
//            "Memon Nagar - Sector 13-A\n" +
//            "Memon Plaza\n" +
//            "Momin Square\n" +
//            "Munir Arcade\n" +
//            "Munir bridge view\n" +
//            "Munir fountains\n" +
//            "Munir Garden\n" +
//            "Munir heaven\n" +
//            "Munir mega mall and residency\n" +
//            "Munir resorts\n" +
//            "Mushaid\n" +
//            "Muskan Appartment - Block 4\n" +
//            "Muskan Appartment - Block 4 A\n" +
//            "Muslim Commercial Area - Phase 6\n" +
//            "Muslman-e-Punjab CHS - Sector 20-A\n" +
//            "Nadeem blessing - Block 3\n" +
//            "Nadeem blessing - Block 3 A\n" +
//            "Nadeem Residency\n" +
//            "Nargis tower\n" +
//            "Naseeb Plaza\n" +
//            "Naseer Tower - Block 1\n" +
//            "Naseer Tower - Block 1 A\n" +
//            "National Cement Society\n" +
//            "National Complex\n" +
//            "Naveed Luxury banglows and cottages\n" +
//            "Nazia Appartment\n" +
//            "New Dohraji Appartment - Block 4\n" +
//            "New Dohraji Appartment - Block 4 A\n" +
//            "Nipa Banglows\n" +
//            "Nishat Commercial Area - Phase 6\n" +
//            "Noman Avenue\n" +
//            "Noman centre\n" +
//            "Noman Complex - Block 13 D-3\n" +
//            "Noman Grand city\n" +
//            "Noman Heaven\n" +
//            "Noman Plaza\n" +
//            "Noman royal city 1\n" +
//            "Noman royal city 2\n" +
//            "Noman royal city 3\n" +
//            "Noman terrace Phase 1\n" +
//            "Noman terrace Phase 2\n" +
//            "Obaid heights\n" +
//            "Osama arcade\n" +
//            "Owais arcade\n" +
//            "Owais Homes\n" +
//            "Own Heights - Block 13 D-3\n" +
//            "Own pride\n" +
//            "Pakistan Tameer Flats\n" +
//            "Palace view 2\n" +
//            "Palm residency - Block 3\n" +
//            "Palm residency - Block 3 A\n" +
//            "Panama Centre - Block 13 D-3\n" +
//            "Paraz view\n" +
//            "Park view appartment - Block 10\n" +
//            "Park view appartment - Block 10 A\n" +
//            "PCSIR Society - Sector 24-A\n" +
//            "Pehlwan Goth - Block 9\n" +
//            "Pehlwan Goth - Block 9 A\n" +
//            "Pehlwan Goth - Block 9 B\n" +
//            "Peninsula Commercial Area - Phase 8\n" +
//            "PHA housing scheme\n" +
//            "PIA employees co-op H.S - Block 9\n" +
//            "PIA employees co-op H.S - Block 9 A\n" +
//            "PIA employees co-op H.S - Block 9 B\n" +
//            "PIA scout society\n" +
//            "Pioneer center\n" +
//            "Piprani comforts - Block 9\n" +
//            "Piprani comforts - Block 9 A\n" +
//            "Piprani comforts - Block 9 B\n" +
//            "Pir ahmed zaman town - Block 1\n" +
//            "Pir ahmed zaman town - Block 2\n" +
//            "Pir ahmed zaman town - Block 3\n" +
//            "Pir ahmed zaman town - Block 4\n" +
//            "Pir Gul Hassan Town - Phase 1\n" +
//            "Pir Gul Hassan Town - Phase 2\n" +
//            "Precinct 10 villas\n" +
//            "Precinct 23A villas\n" +
//            "Precinct 27 villas\n" +
//            "Precinct 31 villas\n" +
//            "Prime Tower\n" +
//            "Punjabi Saudagran Society - Phase 1\n" +
//            "Punjabi Saudagran Society - Phase 2\n" +
//            "Qasim Complex\n" +
//            "Quaid villas\n" +
//            "Quetta Town - Sector 3 A\n" +
//            "Rabia City Flats\n" +
//            "Rabia Garden\n" +
//            "Rabia heaven\n" +
//            "RADO appartments\n" +
//            "Rahat Arcade\n" +
//            "Rahat Commercial Area - Phase 6\n" +
//            "Rao & Israr Heights\n" +
//            "Rao Zebaish\n" +
//            "Raza Square - Block 10\n" +
//            "Raza Square - Block 10 A\n" +
//            "Rigancy Appartments 1 - Block 4\n" +
//            "Rigancy Appartments 1 - Block 4 A\n" +
//            "Rigancy Appartments 2 - Block 4\n" +
//            "Rigancy Appartments 2 - Block 4 A\n" +
//            "Rigancy Heights - Block 4\n" +
//            "Rigancy Heights - Block 4 A\n" +
//            "Rimjhim - Tower A\n" +
//            "Rimjhim - Tower B\n" +
//            "Rimjhim - Tower C\n" +
//            "Rimjhim - Tower D\n" +
//            "Rizwan town\n" +
//            "Rk cottages - Block 13 D-1\n" +
//            "Rose Garden\n" +
//            "Royal Residency\n" +
//            "Royal residency - 13 D-2\n" +
//            "Rufi Centre - Block 13 D-2\n" +
//            "Rufi Flats\n" +
//            "Rufi Green city\n" +
//            "Rufi Heaven - Block 13 D-2\n" +
//            "Rufi heights - Block 13 D-2\n" +
//            "Rufi heights phase 1\n" +
//            "Rufi heights phase 2\n" +
//            "Rufi Lake Drive\n" +
//            "Rufi Palm City\n" +
//            "Rufi Paradise\n" +
//            "Rufi shopping mall\n" +
//            "Saad view\n" +
//            "Saad view - Block 1\n" +
//            "Saadabad Cooperative Housing Society\n" +
//            "Saadi garden - Block 1\n" +
//            "Saadi garden - Block 2\n" +
//            "Saadi garden - Block 3\n" +
//            "Saadi garden - Block 4\n" +
//            "Saadi garden - Block 5\n" +
//            "Saadi garden - Block 7\n" +
//            "Saadi town - Block 1\n" +
//            "Saadi town - Block 2\n" +
//            "Saadi town - Block 3\n" +
//            "Saadi town - Block 4\n" +
//            "Saadi town - Block 5\n" +
//            "Saadi town - Block 6\n" +
//            "Saadi town - Block 7\n" +
//            "Saadi town - Block 7 extension\n" +
//            "Saba Commercial Area - Phase 5\n" +
//            "Sachal Sarmast Society - Block F\n" +
//            "Sadaat-e-Amroha Society - Block D\n" +
//            "Sadaat-e-Amroha Society - Sector A\n" +
//            "Sadaat-e-Amroha Society - Sector B\n" +
//            "Sadaat-e-Amroha Society - Sector C\n" +
//            "Safari Avenue\n" +
//            "Safari blessing\n" +
//            "Safari Boulevard\n" +
//            "Safari Comforts\n" +
//            "Safari Heights\n" +
//            "Safari Palm City - Phase 3\n" +
//            "Safari Palm City Residency - Phase 3\n" +
//            "Safari view Appartment\n" +
//            "Saffron Appartments\n" +
//            "Saima Pride\n" +
//            "Saleem - Block 13 C\n" +
//            "Saleema Square\n" +
//            "Samama Corner\n" +
//            "Samarah arcade - Block 3\n" +
//            "Samarah arcade - Block 3 A\n" +
//            "Samrina corner\n" +
//            "Samrine Appartment - Block 13 B\n" +
//            "Sana Avenue\n" +
//            "Sana Bumbia Arcade\n" +
//            "Sana comforts - Block 13 D-2\n" +
//            "Sapphire Apartment - Phase 7\n" +
//            "Sardar complex\n" +
//            "Sawana city - Block 13 D-3\n" +
//            "Sector 5/L - Phase 1\n" +
//            "Seher Appartment - Block 4\n" +
//            "Seher Appartment - Block 4 A\n" +
//            "Seher Commercial Area - Phase 7\n" +
//            "Shaes residency - Block 3\n" +
//            "Shaes residency - Block 3 A\n" +
//            "Shahbaz Commercial Area - Phase 6\n" +
//            "Shaheen Enclave - Block A\n" +
//            "Shahid Heights\n" +
//            "Shamail arcade\n" +
//            "Shamail garden\n" +
//            "Shamail heaven\n" +
//            "Sharafaat - Block 13 B\n" +
//            "Shumaila Appartment\n" +
//            "Sindh Balochistan Cooperating H.S\n" +
//            "Snaisia Appartment - Block 13 B\n" +
//            "Sobia Appt - Block 1\n" +
//            "Sobia Appt - Block 1 A\n" +
//            "Solar luxury Appartment\n" +
//            "Sonery Appartments\n" +
//            "Sportcity villas\n" +
//            "Stadium Commercial Area - Phase 5\n" +
//            "Star Arcade\n" +
//            "Star Blessing\n" +
//            "Star Luxury Appartment\n" +
//            "Star Shelter\n" +
//            "State Bank of Pakistan - Sector 17-A\n" +
//            "Suleman Plaza - Block 10\n" +
//            "Suleman Plaza - Block 10 A\n" +
//            "Sultan hides\n" +
//            "Sumair Tower\n" +
//            "Sumaira avenue\n" +
//            "Sumaira Pride - Block 1\n" +
//            "Sumaira Pride - Block 1 A\n" +
//            "Sunbeam Appartments - Block 9\n" +
//            "Sunbeam Appartments - Block 9 A\n" +
//            "Sunbeam Appartments - Block 9 B\n" +
//            "Sunbeam pride\n" +
//            "Sunny arcade\n" +
//            "Sunny castle\n" +
//            "Sunny Heights\n" +
//            "Sunny Pride\n" +
//            "Sunny Terrace\n" +
//            "Sunny view appartment\n" +
//            "Sunset Commercial Area - Phase 4\n" +
//            "Sunset Lane - Phase 2\n" +
//            "Supream Shopping mall\n" +
//            "Supreme Castle\n" +
//            "Surti Centre\n" +
//            "Tariq Commercial Area - Phase 7 ext\n" +
//            "Tauheed Commercial Area - Phase 5\n" +
//            "Teacher Society - Sector 19-A\n" +
//            "Teachers Society - Sector 16-A\n" +
//            "Telephone Employees CHS - Sector 10\n" +
//            "Telephone Employees CHS - Sector 26-A\n" +
//            "Tipu Sultan CHS - Sector A-97\n" +
//            "Ubaid Arcade\n" +
//            "Union Classic\n" +
//            "Unique Classic\n" +
//            "Unique Copmlex\n" +
//            "Universal Heights\n" +
//            "Usman garden\n" +
//            "Usman Plaza\n" +
//            "Usman Plaza - Block 13 B\n" +
//            "Waqar Appartments\n" +
//            "Waqar shopping complex - Block 3\n" +
//            "Waqar shopping complex - Block 3 A\n" +
//            "Waqas Heights - Block 13 D-1\n" +
//            "Waseer Society - Phase 5\n" +
//            "Yasir Appartment\n" +
//            "Yousuf heights\n" +
//            "Zafreen corner\n" +
//            "Zafreen park view\n" +
//            "Zakaria goth\n" +
//            "Zeeshan Super Luxary\n" +
//            "Zohra Square\n" +
//            "Zone A - Phase 8\n" +
//            "Zone B - Phase 8\n" +
//            "Zone C - Phase 8\n" +
//            "Zone D - Phase 8\n" +
//            "Zone E - Phase 8\n" +
//            "Zulfi crecent" +
//            "Zulfiqar Commercial Area - Phase 8"};
   String[] proStatus = { "For Rent","For Purchase" };//array of strings used to populate the spinner

    AutoCompleteTextView title,countrytxt,citytxt,areatxt,subareatxt,sectortxt;


    String[] tit={""};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_property_detail);
        title=(AutoCompleteTextView)findViewById(R.id.pro_title_ed) ;
        countrytxt=(AutoCompleteTextView)findViewById(R.id.Country_ed) ;
        citytxt=(AutoCompleteTextView)findViewById(R.id.City_ed) ;
        areatxt=(AutoCompleteTextView)findViewById(R.id.Area_ed) ;
        subareatxt=(AutoCompleteTextView)findViewById(R.id.Subarea_ed) ;
        sectortxt=(AutoCompleteTextView)findViewById(R.id.sector_ed) ;

        title.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,tit));
        countrytxt.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,country));
//        citytxt.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,city));
//        areatxt.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,area));
//        subareatxt.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,subArea));
//        sectortxt.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,sector));

        btn = findViewById(R.id.btnImage);
        gvGallery = (GridView)findViewById(R.id.gv);
        spin = (Spinner) findViewById(R.id.type);//fetching view's id
        //Register a callback to be invoked when an item in this AdapterView has been selected
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long id) {

                spin_val = proType[position];//saving the value selected


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
        //setting array adaptors to spinners
        //ArrayAdapter is a BaseAdapter that is backed by an array of arbitrary objects
               //ArrayAdapter<String> spin_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, proType);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,proType
        );
        // setting adapteers to spinners
        spin.setAdapter(spinnerArrayAdapter);
        spin = (Spinner) findViewById(R.id.status);//fetching view's id
        //Register a callback to be invoked when an item in this AdapterView has been selected
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long id) {

                spin_val = proStatus[position];//saving the value selected


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
        //setting array adaptors to spinners
        //ArrayAdapter is a BaseAdapter that is backed by an array of arbitrary objects
     //   ArrayAdapter<String> spin_adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, proStatus);
        ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(
                this,R.layout.spinner_item,proStatus
        );
        // setting adapteers to spinners
        spin.setAdapter(spinnerArrayAdapter1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_MULTIPLE);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            // When an Image is picked
            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                imagesEncodedList = new ArrayList<String>();
                if(data.getData()!=null){

                    Uri mImageUri=data.getData();

                    // Get the cursor
                    Cursor cursor = getContentResolver().query(mImageUri,
                            filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imageEncoded  = cursor.getString(columnIndex);
                    cursor.close();

                    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                    mArrayUri.add(mImageUri);
                    galleryAdapter = new GalleryAdapter(getApplicationContext(),mArrayUri);
                    gvGallery.setAdapter(galleryAdapter);
                    gvGallery.setVerticalSpacing(gvGallery.getHorizontalSpacing());
                    ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) gvGallery
                            .getLayoutParams();
                    mlp.setMargins(0, gvGallery.getHorizontalSpacing(), 0, 0);

                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            mArrayUri.add(uri);
                            // Get the cursor
                            Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                            // Move to first row
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            imageEncoded  = cursor.getString(columnIndex);
                            imagesEncodedList.add(imageEncoded);
                            cursor.close();

                            galleryAdapter = new GalleryAdapter(getApplicationContext(),mArrayUri);
                            gvGallery.setAdapter(galleryAdapter);
                            gvGallery.setVerticalSpacing(gvGallery.getHorizontalSpacing());
                            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) gvGallery
                                    .getLayoutParams();
                            mlp.setMargins(0, gvGallery.getHorizontalSpacing(), 0, 0);

                        }
                        Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
                    }
                }
            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 //PERHATIAN!!!!! Jika ingin menggunkan 2 html maka yang di komen harus di hapus dan yang tidak di komen harus di komen

package ws.b.IDCARD;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author priza
 */
@Controller
public class myController {
    @RequestMapping(value = "/sendData", method = RequestMethod.POST)
    @ResponseBody
    public String getData(@RequestParam("myname") String getName,
            @RequestParam("myimage") MultipartFile Image,
            @RequestParam("tanggal") @DateTimeFormat(pattern = "yyyy-MM-dd") Date getDate,
            Model model) throws IOException {

        /*
         * byte[] image = Image.getBytes();
         * String base64Image = Base64.encodeBase64String(image);
         * String fotolink = "data:image/png;base64,".concat(base64Image);
         */

        String fotolink = Base64.encodeBase64String(Image.getBytes());

        SimpleDateFormat tanggal = new SimpleDateFormat(", dd - MMMM - yyyy");

        String newTanggal = tanggal.format(getDate);
        String dataDate = getHari(newTanggal);
        String hariIni = /* "Hari, Tanggal Lahir : " + */ dataDate + newTanggal;
        // String getDtNama = "Nama : " + getName;

        /*
         * model.addAttribute("nama", getDtNama);
         * model.addAttribute("date", hariIni);
         * model.addAttribute("image", fotolink);
         */

        // return "sendData";

        return "<head><title>Selamat Datang</title></head>"
                +
                "<body><div class='container'>"
                +
                "<br><center>Selamat Datang</center><hr>"
                +
                "<p>Nama          : " + getName + "</p>"
                +
                "<p>Tanggal Lahir : " + hariIni + "</p>"
                +
                "<br><img src='data:image/jpeg;Base64," + fotolink
                + "' alt='Photo confirmation' width='300' id='display-image'/></img</div>"
                +
                "<style>"
                +
                "body {background-color: black;}"
                +
                ".container {position: absolute; left: 50%; top: 50%; transform: translate(-50%, -50%); height: 400px; width: 600px; background: white; overflow: hidden; border-radius: 20px; box-shadow: 0 0 20px 8px #d0d0d0; background-image: url('https://www.kibrispdr.org/data/10/background-e-ktp-44.jpg'); background-size: cover; background-repeat: no-repeat;}"
                +
                "p { font-weight: 900; text-align: left; width: 100%; direction: rtl; padding: 0 10px;}"
                +
                "center {font-weight: 900; text-align: center;}"
                +
                "hr {border: 1px solid black;}"
                +
                "img {padding: 0 280px;}"
                +
                "</style>"
                +
                "</body>";
    }

    public String getHari(String Tanggal) {

        String[] result = Tanggal.split("-");

        switch (result[0]) {
            case "Mon":
                result[0] = "Senin";
                break;
            case "Tue":
                result[0] = "Selasa";
                break;
            case "Wed":
                result[0] = "Rabu";
                break;
            case "Thu":
                result[0] = "Kamis";
                break;
            case "Fri":
                result[0] = "Jum'at";
                break;
            case "Sat":
                result[0] = "Sabtu";
                break;
            default:
                result[0] = "Minggu";
                break;
        }

        return result[0];
    }
}

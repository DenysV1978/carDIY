package org.launchcode.carDIY.models;




import com.mysql.cj.jdbc.Blob;
import org.launchcode.carDIY.data.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;

import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.sql.rowset.serial.SerialBlob;
import java.util.Base64;
import java.util.Date;



@Entity
public class Image extends AbstractEntity{


    private String name;


    private String description;


    private String filename;


    @Lob
    private byte[] content;



    @ManyToOne
    private ManufacturersFSM manufacturersFSM;

    //constructors

    public Image(String name, String description, String filename, byte[] content, ManufacturersFSM manufacturersFSM) {
        this.name = name;
        this.description = description;
        this.filename = filename;
        this.content = content;
        this.manufacturersFSM = manufacturersFSM;
    }

    public Image() {

    }


    //getters and setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }



    public ManufacturersFSM getManufacturersFSM() {
        return manufacturersFSM;
    }

    public void setManufacturersFSM(ManufacturersFSM manufacturersFSM) {
        this.manufacturersFSM = manufacturersFSM;
    }

    //create Base 64 returner....

    public String returnBase64() {

        String base64 = Base64.getEncoder().encodeToString(this.content);
        return base64;
    }
}

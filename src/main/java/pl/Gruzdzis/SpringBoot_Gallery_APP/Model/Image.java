package pl.Gruzdzis.SpringBoot_Gallery_APP.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Image {

    @Id
    @GeneratedValue(generator="inc")
    @GenericGenerator(name="inc", strategy = "increment")
    private Long id;
    private String urlAddress;

    public Image(Long id, String urlAddress) {
        this.id = id;
        this.urlAddress = urlAddress;
    }

    public Image(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    public Image() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlAddress() {
        return urlAddress;
    }

    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
    }
}

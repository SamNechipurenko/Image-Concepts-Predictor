package com.image.predictor.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "image")
@XmlRootElement
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "img_id")
    private Integer imgId;

    @Basic(optional = false)
    @Lob
    @Column(name = "img_url")
    private String imgUrl;

    @Basic(optional = false)
    @Column(name = "img_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date imgDate = new java.sql.Date(new java.util.Date().getTime());;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Prediction.class)
    @JoinColumn(name ="img_id",referencedColumnName = "img_id")
    private List<Prediction> predictionCollection;

    public Image() {
    }

    public Image(Integer imgId) {
        this.imgId = imgId;
    }

    public Image(Integer imgId, String imgUrl, Date imgDate) {
        this.imgId = imgId;
        this.imgUrl = imgUrl;
        this.imgDate = imgDate;
    }

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Date getImgDate() {
        return imgDate;
    }

    public void setImgDate(Date imgDate) {
        this.imgDate = imgDate;
    }

    @XmlTransient
    public List<Prediction> getPredictionCollection() {
        return predictionCollection;
    }

    public void setPredictionCollection(List<Prediction> predictionCollection) {
        this.predictionCollection = predictionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (imgId != null ? imgId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Image)) {
            return false;
        }
        Image other = (Image) object;
        if ((this.imgId == null && other.imgId != null) || (this.imgId != null && !this.imgId.equals(other.imgId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "timedate.Image[ imgId=" + imgId + " ]";
    }

}


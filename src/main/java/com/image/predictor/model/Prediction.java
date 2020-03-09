package com.image.predictor.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "prediction")
@XmlRootElement
public class Prediction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prediction_id")
    private Integer predictionId;

    @Basic(optional = false)
    @Column(name = "prediction_name")
    private String predictionName;

    @Basic(optional = false)
    @Column(name = "prediction_value")
    private Float predictionValue;

    @JoinColumn(name = "img_id", referencedColumnName = "img_id")
    @ManyToOne(optional = false)
    private Image imgId;

    public Prediction() {
    }

    public Prediction(Integer predictionId) {
        this.predictionId = predictionId;
    }

    public Prediction(Integer predictionId, String predictionName, Float predictionValue) {
        this.predictionId = predictionId;
        this.predictionName = predictionName;
        this.predictionValue = predictionValue;
    }

    public Integer getPredictionId() {
        return predictionId;
    }

    public void setPredictionId(Integer predictionId) {
        this.predictionId = predictionId;
    }

    public String getPredictionName() {
        return predictionName;
    }

    public void setPredictionName(String predictionName) {
        this.predictionName = predictionName;
    }

    public Float getPredictionValue() {
        return predictionValue;
    }

    public void setPredictionValue(Float predictionValue) {
        this.predictionValue = predictionValue;
    }

    public Image getImgId() {
        return imgId;
    }

    public void setImgId(Image imgId) {
        this.imgId = imgId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (predictionId != null ? predictionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prediction)) {
            return false;
        }
        Prediction other = (Prediction) object;
        if ((this.predictionId == null && other.predictionId != null) || (this.predictionId != null && !this.predictionId.equals(other.predictionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "timedate.Prediction[ predictionId=" + predictionId + " ]";
    }

}

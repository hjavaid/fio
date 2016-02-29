package com.fio.prs.model;

import javax.persistence.*;

/**
 * Created by haris on 26/02/16.
 */
@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String issuer;

    @Column(nullable = false,unique = true)
    private String externalId;

   // @OneToOne(mappedBy = "document")
   // private DemographicInfo demographicInfo;

    public Document(){
        super();
    }

   /* public DemographicInfo getDemographicInfo() {
        return demographicInfo;
    }

    public void setDemographicInfo(DemographicInfo demographicInfo) {
        this.demographicInfo = demographicInfo;
    }
*/

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}

package cr.pernix.dashboard.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Metric", schema = "public")
@XmlRootElement
public class Metric implements java.io.Serializable {

    private static final long serialVersionUID = -362011388091201000L;
    private int id;
    private String name;
    private String description;
    private boolean active;
    private Set<Answer> customerSatisfaction = new HashSet<Answer>(0);
    private Survey survey;
    
    public Metric() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "active")
    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customerSatisfaction")
    public Set<Answer> customerSatisfaction() {
        return this.customerSatisfaction;
    }

    public void setUsers(Set<Answer> customerSatisfaction) {
        this.customerSatisfaction = customerSatisfaction;
    }
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "survey_id", nullable = false)
    public Survey getSurvey() {
        return this.survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Metric metric = (Metric) o;
        if (id != metric.id)
            return false;
        return true;
    }

}

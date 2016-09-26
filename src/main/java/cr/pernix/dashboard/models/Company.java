package cr.pernix.dashboard.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Company", schema = "public")
@XmlRootElement
public class Company implements java.io.Serializable {
    
    private int id;
    private String name;
    private Set<Manager> manager = new HashSet<Manager>(0);

    public Company() {
    }

    public Company(int id) {
        setId(id);
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
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "manager")
    public Set<Manager> manager() {
        return this.manager;
    }

    public void setManager(Set<Manager> manager) {
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Company company = (Company) o;
        if (id != company.id)
            return false;
        return true;
    }

}
